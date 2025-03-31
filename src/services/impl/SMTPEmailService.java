package services.impl;

import services.interfaces.EmailService;
import exceptions.EmailException;

/**
 * Implémentation SMTP (SRP)
 */
public class SMTPEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) throws EmailException {
        if (to == null || !to.contains("@")) {
            throw new EmailException("Email invalide: " + to);
        }
        System.out.printf("[SMTP] Envoi à %s | Sujet: %s%n", to, subject);
    }
}