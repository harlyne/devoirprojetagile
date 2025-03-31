import config.AppConfig;
import entity.Article;
import entity.ArticleCommande;
import entity.Commande;
import services.interfaces.OrderProcessor;

public class App {
    public static void main(String[] args) {
        OrderProcessor processor = AppConfig.getOrderProcessor();
        Commande commande = createSampleCommand();

        try {
            processor.processOrder(commande);
            displayOrderSummary(commande);
        } catch (Exception e) {
            System.err.println("Erreur de traitement : " + e.getMessage());
        }
    }

    private static Commande createSampleCommand() {
        Article laptop = new Article(1, "Laptop", "Electronics", 800, 5);
        Article phone = new Article(2, "Téléphone", "Electronics", 500, 10);

        Commande commande = new Commande(1001, 123, "client@example.com");
        commande.addItem(new ArticleCommande(laptop, 1));
        commande.addItem(new ArticleCommande(phone, 2));

        return commande;
    }

    private static void displayOrderSummary(Commande commande) {
        System.out.println("\nCommande traitée avec succès !");
        System.out.println("Détails de la commande :");
        System.out.println("Client ID: " + commande.getClientId());
        System.out.println("Email: " + commande.getClientEmail());
        System.out.printf("Total de la commande: %.1f FCFA%n", commande.getTotalPrice());

        System.out.println("\nArticles commandés :");
        for (ArticleCommande item : commande.getArticles()) {
            System.out.printf("- %s (x%d) : %.1f FCFA%n",
                    item.getArticle().getName(),
                    item.getQuantity(),
                    item.getArticle().getPrice() * item.getQuantity());
        }
    }
}