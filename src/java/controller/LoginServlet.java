package controller;

import Beans.LoginBean;
import Dao.BasicDao;
import Dao.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "text/plane");
        PrintWriter out = response.getWriter();
        out.print("unauthorized");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "text/plane");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logintype = request.getParameter("logintype");
        
        if(username==null || username.isEmpty())
            out.print("username is required");
        else if(password==null || password.isEmpty())
            out.print("password is required");
        else if(username.contains(" ") || password.contains(" "))
            out.print("username or password must not contain space");
        else if(logintype==null || logintype.isEmpty() || logintype.contains(" "))
            out.print("select valid login type");
        else {
            LoginBean bean = new LoginBean();
            bean.setUsername(username);
            bean.setPassword(password);
            bean.setLoginType(logintype);

            LoginDao dao = new LoginDao();
            String status = dao.authenticateUser(bean);

            if(status.equals("success"))
            {
                HttpSession session = request.getSession();
                if(logintype.equals("admin")) {
                    session.setAttribute("uniqueid",username);
                    session.setAttribute("logintype", logintype);
                    out.print("success");
                    return;
                }
                else {
                    ResultSet info = null;
                    try {
                        info = BasicDao.getLoggedInUserData(logintype, username);
                        if(info.next()) {
                            session.setAttribute("uniqueid",info.getString("uniqueid"));
                            session.setAttribute("logintype", logintype);
                            session.setAttribute("collegecode",info.getInt("collegecode"));
                            if(logintype.equals("student")) {
                                session.setAttribute("branchcode",info.getInt("branchcode"));
                                session.setAttribute("semester",info.getInt("semester"));
                            }
                            out.print("success");
                            try { info.close(); } catch(SQLException e) { }
                            return;
                        }
                        out.print("Something went wrong 1.0.--1");

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                    finally {
                        try { info.close(); } catch(Exception e) { }
                    }
                    out.print("Something went wrong 1.0.1.x.0"); 
                }         
            }
            else {
                out.print(status);
            }
        }
    }
}
