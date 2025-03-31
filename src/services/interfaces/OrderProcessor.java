package services.interfaces;

import entity.Commande;
import exceptions.DatabaseException;
import exceptions.EmailException;

/**
 * Contrat pour le traitement des commandes (OCP)
 */
public interface OrderProcessor {
    void processOrder(Commande commande) throws DatabaseException, EmailException;
}