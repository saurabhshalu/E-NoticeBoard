package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentFilterServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        if(session.getAttribute("logintype")==null) {
            out.print("not authorized");
            return;
        }
        else {
            if(!session.getAttribute("logintype").toString().equals("professor")) {
                out.print("not authorized!");
                return;
            }
        }
        String semester = request.getParameter("semester");
        String branch = request.getParameter("branch");
        if(semester!=null && branch!=null){
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/promotefiles/promotestudent.jsp");
            rd.forward(request, response);
        }
        else {
            out.print("please select valid semester or branch");
        }
    }
}
