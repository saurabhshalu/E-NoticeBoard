package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PreviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        try {
            String id = request.getParameter("id");
            if(id!=null && id.equals("all") && request.getSession().getAttribute("logintype").toString().equals("professor"))
                rd = request.getRequestDispatcher("./WEB-INF/allnoticetemplate.jsp");
            else
                rd = request.getRequestDispatcher("./WEB-INF/previewnotice.jsp?id=" + Integer.parseInt(id));
        } catch(Exception e) {
            rd = request.getRequestDispatcher("./WEB-INF/previewnotice.jsp?id=-999");
        }
        rd.forward(request, response);     
    }
}
