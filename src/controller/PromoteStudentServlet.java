package controller;

import Dao.BasicDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MyUtils;

public class PromoteStudentServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("html/text");
        PrintWriter out = response.getWriter();
        String list = request.getParameter("selectedStudents");
        if(list!=null) {
            String collegecode = request.getSession().getAttribute("collegecode").toString();
            if(collegecode!=null) {
                Connection con = MyUtils.getStoredConnection(request);
                int rows = BasicDao.promoteSelectedStudent(con, list, collegecode);
                if(rows>0)
                    out.print("success");
                else
                    out.print("something went wrong");
            }
            else
                out.print("college not valid");
        }
        else {
            out.print("please select students to promote");
        }
    }
}
