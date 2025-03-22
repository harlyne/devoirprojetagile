package services;

import database.MySQLDatabase;
import entity.Article;
import entity.ArticleCommande;
import entity.Commande;
import exceptions.DatabaseException;
import exceptions.EmailException;

public class CommadeService {
     private MySQLDatabase database;
    private SMTPEmailService emailService;
    
    public CommadeService() {
        this.database = new MySQLDatabase();
        this.emailService = new SMTPEmailService();
    }
    
    public void processOrder(Commande commande) {
        // Validation de la commande
        if (commande.getArticles().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        
        // Calcul du prix total
        double totalPrice = 0;
        for (ArticleCommande item : commande.getArticles()) {
            if (item.getArticle().getCategory().equals("Electronics")) {
                totalPrice += item.getArticle().getPrice() * 1.2; // Taxe spéciale pour l'électronique
            } else {
                totalPrice += item.getArticle().getPrice();
            }
        }
        commande.setTotalPrice(totalPrice);
        
        // Sauvegarde dans la base de données
        String sql = "INSERT INTO commandes (customer_id, total_price) VALUES (?, ?)";
        try {
            database.executeUpdate(sql, commande.getClientId(), commande.getTotalPrice());
            
            for (ArticleCommande item : commande.getArticles()) {
                String itemSql = "INSERT INTO commande_article (order_id, product_id, quantity) VALUES (?, ?, ?)";
                database.executeUpdate(itemSql, commande.getId(), item.getArticle().getId(), item.getQuantity());
            }
        } catch (DatabaseException e) {
              System.err.println("Erreur enregistrement de la commande: " + e.getMessage());
             return;
        }
        
        // Envoi d'un email de confirmation
        String message = "Cher client, votre commande a ete traite. Total: $" + commande.getTotalPrice();
        try {
            emailService.sendEmail(commande.getClientEmail(), "Order Confirmation", message);
        } catch (EmailException e) {
            System.err.println("Erreur confirmation email: " + e.getMessage());
        }
        
        // Mise à jour de l'inventaire
        for (ArticleCommande item : commande.getArticles()) {
            Article article = item.getArticle();
            article.setStock(article.getStock() - item.getQuantity());
            
            String updateSql = "UPDATE article SET stock = ? WHERE id = ?";
            try {
                database.executeUpdate(updateSql, article.getStock(), article.getId());
            } catch (DatabaseException e) {
                System.err.println("Failed to update inventory: " + e.getMessage());
            }
        }
    }
}
