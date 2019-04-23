package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection createConnection() {
        //Connection con = null;
        /*
        String url = "jdbc:mysql://localhost:3306/dbnotice"; //MySQL URL and followed by the database name
        String username = "root"; //MySQL username
        String password = ""; //MySQL password
        */
        Connection con = null;
        
        String url = "jdbc:mysql://remotemysql.com:3306/5xwctwX9Ui"; //MySQL URL and followed by the database name
        String username = "5xwctwX9Ui"; //MySQL username
        String password = "JAKaWduHIe"; //MySQL password
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
