package services;

import exceptions.EmailException;

class SMTPEmailService {
    public void sendEmail(String email, String subject, String message) throws EmailException {
        System.out.println("Envoie de Mail  a: " + email + ", Objet: " + subject);
    }
}
