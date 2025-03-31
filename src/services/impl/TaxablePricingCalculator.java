package services.impl;

import entity.ArticleCommande;
import entity.Commande;
import services.interfaces.PricingCalculator;

/**
 * Implémentation avec taxes (SRP)
 */
public class TaxablePricingCalculator implements PricingCalculator {
    @Override
    public double calculateTotal(Commande commande) {
        double total = 0;
        for (ArticleCommande item : commande.getArticles()) {
            double price = item.getArticle().getPrice();
            if ("Electronics".equals(item.getArticle().getCategory())) {
                price *= 1.2; // Taxe 20% pour l'électronique
            }
            total += price * item.getQuantity();
        }
        return total;
    }
}