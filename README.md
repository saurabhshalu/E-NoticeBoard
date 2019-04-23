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
    public static Connection createConnection() {

        String url = "jdbc:mysql://localhost:3306/databasename"; //MySQL URL and followed by the database name
        String username = "username"; //MySQL username
        String password = "password"; //MySQL password
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}

#you are done!
