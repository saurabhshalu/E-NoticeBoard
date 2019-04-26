package utils;

import java.sql.Connection;
import javax.servlet.ServletRequest;

public class MyUtils {
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    public static void storeConnection(ServletRequest request, Connection con) {
        System.out.println(con);
        request.setAttribute(ATT_NAME_CONNECTION, con);
    }
    public static Connection getStoredConnection(ServletRequest request) {
        Connection con = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return con;
    } 
}
