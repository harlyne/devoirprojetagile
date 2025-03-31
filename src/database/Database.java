package database;

import exceptions.DatabaseException;

/**
 * Contrat pour l'accès aux données (OCP)
 */
public interface Database {
    void executeUpdate(String sql, Object... params) throws DatabaseException;
}