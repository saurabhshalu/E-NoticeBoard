package controller;

import Dao.BasicDao;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.MyUtils;

public class SpecialUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("not authorized");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection con = MyUtils.getStoredConnection(request);
        
        HttpSession session = request.getSession(); 
        response.setContentType("text/plain");
        if(session.getAttribute("uniqueid")!=null) {
            switch (session.getAttribute("logintype").toString()) {
                case "admin":
                    String approve = request.getParameter("approve");
                    String reject = request.getParameter("reject");
                    if(request.getParameter("approve")!=null) {
                        String result = BasicDao.approvePrimeMember(con,approve);
                        response.getWriter().print(result);
                    }
                    else if(request.getParameter("reject")!=null) {
                        String result = BasicDao.rejectPrimeMember(con, reject);
                        response.getWriter().print(result);
                    }
                    else {
                        response.getWriter().print("not authorized");
                    }   break;
                case "professor":
                    String result = BasicDao.requestSpecialPermission(con,session.getAttribute("uniqueid").toString(), Integer.parseInt(session.getAttribute("collegecode").toString()));
                    response.getWriter().print(result);
                    break;
                default:
                    response.getWriter().print("not authorized");
            }
        }
        else {
            response.getWriter().print("please login to continue");
        }
    }
}
