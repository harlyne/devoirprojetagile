package services.impl;

import database.Database;
import entity.Article;
import entity.ArticleCommande;
import entity.Commande;
import exceptions.DatabaseException;
import exceptions.EmailException;
import services.interfaces.EmailService;
import services.interfaces.OrderProcessor;
import services.interfaces.PricingCalculator;

/**
 * Implémentation de OrderProcessor (SRP + OCP)
 * - Exactement la même logique métier que votre version originale
 * - Implémente désormais l'interface OrderProcessor
 */
public class CommandeService implements OrderProcessor {
    private final Database database;
    private final EmailService emailService;
    private final PricingCalculator pricingCalculator;

    public CommandeService(Database database,
                           EmailService emailService,
                           PricingCalculator pricingCalculator) {
        this.database = database;
        this.emailService = emailService;
        this.pricingCalculator = pricingCalculator;
    }

    @Override
    public void processOrder(Commande commande) throws DatabaseException, EmailException {
        // 1. Validation
        if (commande.getArticles().isEmpty()) {
            throw new IllegalArgumentException("La commande doit contenir au moins un article");
        }

        // 2. Calcul du prix (délégué au PricingCalculator)
        commande.setTotalPrice(pricingCalculator.calculateTotal(commande));

        // 3. Persistance
        persistOrder(commande);

        // 4. Notification
        sendEmailConfirmation(commande);

        // 5. Mise à jour du stock
        updateInventory(commande);
    }

    private void persistOrder(Commande commande) throws DatabaseException {
        String orderSql = "INSERT INTO commandes (customer_id, total_price) VALUES (?, ?)";
        database.executeUpdate(orderSql, commande.getClientId(), commande.getTotalPrice());

        String itemSql = "INSERT INTO commande_article (order_id, product_id, quantity) VALUES (?, ?, ?)";
        for (ArticleCommande item : commande.getArticles()) {
            database.executeUpdate(itemSql, commande.getId(),
                    item.getArticle().getId(),
                    item.getQuantity());
        }
    }

    private void sendEmailConfirmation(Commande commande) throws EmailException {
        String message = String.format(
                "Cher client, votre commande #%d a été traitée. Total: %.2f €",
                commande.getId(),
                commande.getTotalPrice()
        );
        emailService.sendEmail(
                commande.getClientEmail(),
                "Confirmation de commande",
                message
        );
    }

    private void updateInventory(Commande commande) throws DatabaseException {
        String updateSql = "UPDATE article SET stock = ? WHERE id = ?";
        for (ArticleCommande item : commande.getArticles()) {
            Article article = item.getArticle();
            int newStock = article.getStock() - item.getQuantity();
            article.setStock(newStock);
            database.executeUpdate(updateSql, newStock, article.getId());
        }
    }
}