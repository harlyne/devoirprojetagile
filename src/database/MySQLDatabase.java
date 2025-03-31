package database;

import exceptions.DatabaseException;

/**
 * Implémentation MySQL (SRP)
 * Responsabilité : Exécuter des requêtes SQL sur MySQL
 */
public class MySQLDatabase implements Database {
    @Override
    public void executeUpdate(String sql, Object... params) throws DatabaseException {
        if (sql == null || sql.trim().isEmpty()) {
            throw new DatabaseException("Requête SQL invalide");
        }
        System.out.println("[MySQL] Exécution : " + sql);
    }
}
