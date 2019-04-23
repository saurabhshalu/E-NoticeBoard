package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;
import Beans.RegisterBean;

public class RegisterDao {
    public String registerStudent(RegisterBean registerbean) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            String enrollment = registerbean.getUsername();
            String fullname = registerbean.getFullname();
            String password = registerbean.getPassword();
            String password2 = registerbean.getPassword2();
            String branchcode = registerbean.getBranch();
            String semester = registerbean.getSemester();
            String phone = registerbean.getPhone();
            String email = registerbean.getEmail();
            String address = registerbean.getAddress();
            String tblName = registerbean.getRegisterType();
            String collegecode = registerbean.getCollege();
            
            con = DBConnection.createConnection();
            ps = con.prepareStatement("select * from " + tblName + " where uniqueid=?");
            
            ps.setString(1,enrollment);
            rs = ps.executeQuery();
            if(rs.next())
                return "account already exists!";
            
            if(!password.equals(password2))
                return "password must be same";
            
            ps1 = con.prepareStatement("insert into " + tblName + "(uniqueid,name,branchcode,semester,phone,email,address,password,collegecode) VALUES(?,?,?,?,?,?,?,?,?)");
            ps1.setString(1,enrollment);
            ps1.setString(2,fullname);
            ps1.setInt(3,Integer.parseInt(branchcode));
            ps1.setInt(4, Integer.parseInt(semester));
            ps1.setString(5,phone);
            ps1.setString(6,email);
            ps1.setString(7,address);
            ps1.setString(8,password);
            ps1.setInt(9,Integer.parseInt(collegecode));
            
            int n = ps1.executeUpdate();
            if(n==1)
                return "success";
            else
                return "error";
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);  
            return "you will get nothing but failure v1.0";
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { ps.close(); } catch(Exception e) { }
            try { ps1.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
    }
    public String registerProfessor(RegisterBean registerbean) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            String professorid = registerbean.getUsername();
            String fullname = registerbean.getFullname();
            String password = registerbean.getPassword();
            String password2 = registerbean.getPassword2();
            String phone = registerbean.getPhone();
            String email = registerbean.getEmail();
            String address = registerbean.getAddress();
            String tblName = registerbean.getRegisterType();
            String collegecode = registerbean.getCollege();

            con = DBConnection.createConnection();
            ps = con.prepareStatement("select * from " + tblName + " where uniqueid=?");
            ps.setString(1,professorid);
            rs = ps.executeQuery();
            if(rs.next())
                return "account already exists!";
            
            if(!password.equals(password2))
                return "password must be same";
            
            ps1 = con.prepareStatement("insert into " + tblName + "(uniqueid, name, phone, email, address, password, collegecode) VALUES(?,?,?,?,?,?,?)");
            ps1.setString(1,professorid);
            ps1.setString(2,fullname);
            ps1.setString(3,phone);
            ps1.setString(4,email);
            ps1.setString(5,address);
            ps1.setString(6,password);
            ps1.setInt(7,Integer.parseInt(collegecode));
            
            int n = ps1.executeUpdate();
            if(n==1)
                return "success";
            else
                return "error";
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);  
            return "you will get nothing but failure v1.1";
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { ps.close(); } catch(Exception e) { }
            try { ps1.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
    }
}
