package services.interfaces;

import exceptions.EmailException;

/**
 * Contrat pour les services d'envoi d'emails (OCP)
 */
public interface EmailService {
    void sendEmail(String to, String subject, String body) throws EmailException;
}