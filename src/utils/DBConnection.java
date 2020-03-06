package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection getMySQLConnection() {
    	String hostName = "localhost:3308";
        String dbName = "dbnotice";
        String userName = "root";
        String password = "";
        
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName + "?characterEncoding=latin1&useConfigs=maxPerformance";
            con = DriverManager.getConnection(connectionURL, userName, password);
        } catch (ClassNotFoundException | SQLException ex) {        	
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}