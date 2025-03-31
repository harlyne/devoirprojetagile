package services.interfaces;

import entity.Commande;

/**
 * Contrat pour le calcul des prix (OCP)
 */
public interface PricingCalculator {
    double calculateTotal(Commande commande);
}