package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return DBConnection.getMySQLConnection();
    }
    public static void closeQuietly(Connection con) {
        try {
            con.close();
        } catch (SQLException e) { }
    }
    public static void rollbackQuietly(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) { }
    }
}
