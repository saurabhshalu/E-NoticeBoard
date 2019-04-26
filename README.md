# E-NoticeBoard
E-Notice board is a web application to help college!

#Create a file "DBConnection.java" in package 'utils' and add following content with correct details
#----------------------------------------------------------------------------------------------------

package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection getMySQLConnection() {
        String hostName = "localhost:3306";
        String dbName = "dbnotice";
        String userName = "root";
        String password = "";
        return getMySQLConnection(hostName,dbName,userName,password);
    }
    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;
            con = DriverManager.getConnection(connectionURL, userName, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
#you are done!
