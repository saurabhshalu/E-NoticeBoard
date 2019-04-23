package Dao;

import Beans.NoticeBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBConnection;

public class NoticeDao {
     public String submitNotice(NoticeBean noticebean) {
        Connection con = null;
        PreparedStatement ps= null;
         
        try {
            String title = noticebean.getTitle();
            String body = noticebean.getBody();
            String attachment = noticebean.getAttachment();
            String semester = noticebean.getSemester();
            String branchcode = noticebean.getBranch();
            String collegecode = noticebean.getCollege();
            java.util.Date start_date = BasicDao.convertStringToDate(noticebean.getStartDate());
            java.util.Date end_date = BasicDao.convertStringToDate(noticebean.getEndDate());
            String professorcode = noticebean.getProfessorCode();
            Date s_date = new Date(start_date.getTime());
            Date e_date = new Date(end_date.getTime());
            
            con = DBConnection.createConnection();
            
            ps = con.prepareStatement("insert into tblnotice(title,body,attachment,semester,branchcode,collegecode,start_date,end_date,professorcode) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1,title);
            ps.setString(2,body);
            ps.setString(3,attachment);
            ps.setInt(4, Integer.parseInt(semester));
            ps.setInt(5,Integer.parseInt(branchcode));
            ps.setInt(6,Integer.parseInt(collegecode));
            ps.setDate(7,s_date);
            ps.setDate(8,e_date);
            ps.setString(9,professorcode);
            
            int n = ps.executeUpdate();
            if(n==1)
                return "success";
            else
                return "error";
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);      
        }
        finally {
            try { ps.close(); } catch(Exception e) { }
            try { con.close(); } catch(Exception e) { }
        }
        return "Error code (Dao:2.1)";
    }
}
