package Dao;
import Beans.LoginBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginDao {
    public String authenticateUser(Connection con, LoginBean loginbean) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String username = loginbean.getUsername();
            String password = loginbean.getPassword();
            String tblName = loginbean.getLoginType();
            ps = con.prepareStatement("select * from " + tblName + " where uniqueid=? and password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next())
                return "success";
            else
                return "invalid user credentials";
        } catch (SQLException ex) {
            System.out.println(ex); 
            return ("you will get nothing but failure");        
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { ps.close(); } catch(Exception e) { }
        }
    }
}