package Dao;

import Beans.NoticeBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicDao {
    public static ResultSet getBranchList(Connection con) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from tblbranch");
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            //try { rs.close(); } catch(Exception e) { }
            //try { s.close(); } catch(Exception e) { }
            return rs;
        }    
    }
    public static ResultSet getCollegeList(Connection con) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from tblcollege");
            
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            //try { rs.close(); } catch(Exception e) { }
            //try { s.close(); } catch(Exception e) { }
            return rs;
        }
    }
    
    public static boolean isValidSemester(String n) {
        try {
            int sem = Integer.parseInt(n);
            return sem>=1 && sem<=8;
        } catch(NumberFormatException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static boolean isValidDate(String x) {
        try { 
            Date date = convertStringToDate(x);      
            Date current = getDateWithoutTime(new Date()); //return Date with 00:00:00.00      
            int diff = date.compareTo(current);
            return diff>=0;   
        }
        catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    public static Date convertStringToDate(String input) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            dateFormat.setLenient(false);
            date = dateFormat.parse(input); 
        }
        catch(ParseException ex) {
            System.out.println(ex);
        }
        return date;
    }
    private static Date getDateWithoutTime(Date input) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(input);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        return cal.getTime();
    }
    public static boolean isValidDateRange(String low, String high) {
        try {
            Date d1 = convertStringToDate(low);
            Date d2 = convertStringToDate(high);
            int diff = d2.compareTo(d1);
            return diff>=0;          
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public static boolean isValidBranch(Connection con, String n) {
        Statement s = null;
        ResultSet rs = null;
        boolean valid = false;
        try {
          s = con.createStatement();
          rs = s.executeQuery("select branchcode from tblbranch where branchcode=" + Integer.parseInt(n));
          valid = rs.next();
        } catch(NumberFormatException | SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
            valid = false;
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { s.close(); } catch(Exception e) { }
            return valid;
        }
    }
    public static boolean isValidCollege(Connection con, String n) {
        Statement s = null;
        ResultSet rs = null;
        boolean valid = false;
        try {
          s = con.createStatement();
          rs = s.executeQuery("select collegecode from tblcollege where collegecode=" + Integer.parseInt(n));
          valid = rs.next();
        } catch(NumberFormatException | SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
            valid = false;
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { s.close(); } catch(Exception e) { }
            return valid;
        }
    }
    
    public static ResultSet getLoggedInUserData(Connection con,String logintype,String id) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            String tblname = "tbl" + logintype;
            rs = s.executeQuery("select * from " + tblname + " where uniqueid='"+id+"'");
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            //try { s.close(); } catch(Exception e) { }
            //try { con.close(); } catch(Exception e) { }
            return rs;
        }
    }
    
    public static List<NoticeBean> getCurrentTeacherNotice(Connection con, int collegecode, String uniqueid, String view) {
        Statement s = null;
        ResultSet rs = null;
        List<NoticeBean> list = new ArrayList<>();
        try {
            s = con.createStatement();
            if(view.equals("all"))
                rs = s.executeQuery("select * from tblnotice where collegecode='"+collegecode+"' order by id desc");
            else
                rs = s.executeQuery("select * from tblnotice where collegecode='"+collegecode+"' and professorcode='" + uniqueid +"' order by id desc");
  
            while(rs.next()) {
                NoticeBean n = new NoticeBean();
                n.setId(rs.getLong(1));
                n.setTitle(rs.getString(2));
                n.setBody(rs.getString(3));
                n.setAttachment(rs.getString(4));
                n.setSemester(rs.getString(5));
                n.setBranch(rs.getString(6));
                n.setCollege(rs.getString(7));
                n.setStartDate(rs.getString(8));
                n.setEndDate(rs.getString(9));
                n.setProfessorCode(rs.getString(10));
                list.add(n);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { s.close(); } catch(Exception e) { }
            return list;
        }
    }
    public static List<NoticeBean> getCurrentStudentNotice(Connection con, int collegecode, int semester, int branchcode) {
        Statement s = null;
        ResultSet rs = null;
        List<NoticeBean> list = new ArrayList<>();
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from tblnotice where collegecode='"+collegecode+"' and branchcode='" + branchcode +"' and (semester='" + semester + "' or semester='0') order by id desc");
            while(rs.next()) {
                NoticeBean n = new NoticeBean();
                n.setId(rs.getLong(1));
                n.setTitle(rs.getString(2));
                n.setBody(rs.getString(3));
                n.setAttachment(rs.getString(4));
                n.setSemester(rs.getString(5));
                n.setBranch(rs.getString(6));
                n.setCollege(rs.getString(7));
                n.setStartDate(rs.getString(8));
                n.setEndDate(rs.getString(9));
                n.setProfessorCode(rs.getString(10));
                list.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { s.close(); } catch(Exception e) { }
            return list;
        }
    }
    
    public static NoticeBean previewSelectedNotice(Connection con, int noticeid,int collegecode, String logintype, int semester, int branchcode) {
        Statement s = null;
        ResultSet rs = null;
        NoticeBean current = null;
        try {
            s = con.createStatement();
            
            if(logintype.equals("student"))
                rs = s.executeQuery("select * from tblnotice where id='" + noticeid + "' and collegecode='"+collegecode+"'  and branchcode='" + branchcode +"' and (semester='" + semester + "' or semester='0')");
            else
                rs = s.executeQuery("select * from tblnotice where id='" + noticeid + "' and collegecode='"+collegecode+"'");
            
            while(rs.next()) {
                current = new NoticeBean();
                current.setId(rs.getLong(1));
                current.setTitle(rs.getString(2));
                current.setBody(rs.getString(3));
                current.setAttachment(rs.getString(4));
                current.setSemester(rs.getString(5));
                current.setBranch(rs.getString(6));
                current.setCollege(rs.getString(7));
                current.setStartDate(rs.getString(8));
                current.setEndDate(rs.getString(9));
                current.setProfessorCode(rs.getString(10));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { s.close(); } catch(Exception e) { }
            return current;
        }
    }
    
    public static String getFileName(Connection con, String filename, int collegeid, String userid) {
        Statement s = null;
        ResultSet rs = null;
        String result = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from tblnotice ORDER BY id DESC LIMIT 1");
            if(rs.next())  
                result = userid + collegeid + rs.getInt(1) + filename.substring(filename.lastIndexOf("."));
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try { rs.close(); } catch(Exception e) { }
            try { s.close(); } catch(Exception e) { }
            return result;
        }
    }
    public static String approvePrimeMember(Connection con, String code) { 
        try {
            Statement s = con.createStatement();
            int i = s.executeUpdate("update tblspecial set status='1' where id='" + code + "'");
            if(i==1)
                return "success";
            else
                return "error";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error fatal";
    }
    public static String rejectPrimeMember(Connection con, String code) {
        try {
            Statement s = con.createStatement();
            int i = s.executeUpdate("update tblspecial set status='-1' where id='" + code + "'");
            if(i==1)
                return "success";
            else
                return "error";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error fatal";
    }
    public static String requestSpecialPermission(Connection con, String uniqueid, int collegecode) {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from tblspecial where uniqueid='" + uniqueid + "' and collegecode='" + collegecode + "'");
            if(rs.next()) {
                int status = rs.getInt(4);
                try { rs.close(); } catch(Exception e) { }
                try { s.close(); } catch(Exception e) { }
                if(status==1)
                    return "you are already a prime user (success)";
                else if(status==-1)
                    return "request rejected by admin, please contact admin for solution";
                else
                    return "you have already requested";
            }
            else {
                int i = s.executeUpdate("insert into tblspecial(uniqueid,collegecode,status) values('" + uniqueid + "', '" + collegecode + "', '0')");
                try { rs.close(); } catch(Exception e) { }
                try { s.close(); } catch(Exception e) { }
                if(i==1) {
                    return "your request has been registered";
                }
                else{
                    return "something bad happened, please take screenshot and report to admin";
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "error";
    }
    
    public static ResultSet getListOfPrimeRequest(Connection con) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from tblspecial where status='0'");
        } catch (SQLException ex) {
            Logger.getLogger(BasicDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            //try { s.close(); } catch(Exception e) { }
            //try { con.close(); } catch(Exception e) { }
            return rs;
        }
    }
}


