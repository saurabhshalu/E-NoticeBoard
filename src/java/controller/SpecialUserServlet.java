package controller;

import Dao.BasicDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SpecialUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("not authorized");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); 
        response.setContentType("text/plain");
        if(session.getAttribute("uniqueid")!=null) {
            String approve = request.getParameter("approve");
            String reject = request.getParameter("reject");
            if(request.getParameter("approve")!=null) {
                String result = BasicDao.approvePrimeMember(approve);
                response.getWriter().print(result);
                return;
            }
            else if(request.getParameter("reject")!=null) {
                String result = BasicDao.rejectPrimeMember(reject);
                response.getWriter().print(result);
                return;
            }
            else {
                String result = BasicDao.requestSpecialPermission(session.getAttribute("uniqueid").toString(), Integer.parseInt(session.getAttribute("collegecode").toString()));
                response.getWriter().print(result);
            }   
        }
        else {
            response.getWriter().print("please login to continue");
        }
    }
}
