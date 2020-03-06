package controller;

import Dao.BasicDao;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.MyUtils;

public class PreviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String deleteid = request.getParameter("del");
        
        if(deleteid!=null) {
            boolean result = false;
            try {
                Connection con = MyUtils.getStoredConnection(request);
                String collegecode = session.getAttribute("collegecode").toString();
                String logintype = session.getAttribute("logintype").toString();
                String professorcode = session.getAttribute("uniqueid").toString();
                result = BasicDao.deleteSelectedNotice(con, deleteid, collegecode, logintype, professorcode);
            } catch(Exception e) {
                System.out.println(e);
            }
            finally {
                if(result == true)
                    response.getWriter().print("success");
                else
                    response.getWriter().print("not authorized");
            }
        }
        else {
            try {
                if(id!=null && id.equals("all") && request.getSession().getAttribute("logintype").toString().equals("professor"))
                {
                	System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
                	
                    rd = request.getRequestDispatcher("/WEB-INF/allnoticetemplate.jsp");
                }
                else
                    rd = request.getRequestDispatcher("./WEB-INF/previewnotice.jsp?id=" + Integer.parseInt(id));
            } catch(Exception e) {
                rd = request.getRequestDispatcher("./WEB-INF/previewnotice.jsp?id=-999");
            }
            rd.forward(request, response);  
        }     
    }
}
