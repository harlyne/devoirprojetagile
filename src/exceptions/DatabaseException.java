package exceptions;

/**
 * Exception métier pour les erreurs de base de données (SRP)
 */
public class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }

}