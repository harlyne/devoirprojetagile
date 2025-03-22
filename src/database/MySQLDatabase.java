package database;

import exceptions.DatabaseException;

public class MySQLDatabase {
     public void executeUpdate(String sql, Object... params) throws DatabaseException {
        // Implémentation de l'exécution SQL
        System.out.println("Executing SQL: " + sql);
      }
}
