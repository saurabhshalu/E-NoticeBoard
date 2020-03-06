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

public class PromotionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("uniqueid")!=null) {
	        Connection con = MyUtils.getStoredConnection(request);
	        if(BasicDao.isSpecialUser(con,session.getAttribute("uniqueid").toString(), Integer.parseInt(session.getAttribute("collegecode").toString()))) 
	            request.getRequestDispatcher("./WEB-INF/promotefiles/searchbox.jsp").forward(request, response);
	        else
	            response.sendRedirect("./");
        }
        else
            response.sendRedirect("./");
    }
}
