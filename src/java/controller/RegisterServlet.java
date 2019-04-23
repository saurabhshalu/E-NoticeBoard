package controller;

import Beans.RegisterBean;
import Dao.BasicDao;
import Dao.RegisterDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "text/plain");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");        
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String branchcode = request.getParameter("branchcode");
        String semester = request.getParameter("semester");
        String collegecode = request.getParameter("collegecode");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String registertype = request.getParameter("registertype");
        

        if(username==null || username.isEmpty())
            out.print("unique id is required");
        else if(fullname==null || fullname.trim().equals(""))
            out.print("name field is required");
        else if(password==null || password.equals(""))
            out.print("password field is required");  
        else if(password.length()<5)
            out.print("password length must be greater than 4");
        else if(password2==null || password2.equals(""))
            out.print("Retype password is required");
        else if(username.contains(" ") || password.contains(" "))
            out.print("username or password must not contain space");
        else if(!password.equals(password2))
            out.print("Password does not match");
        else if(registertype==null || registertype.isEmpty() || registertype.contains(" "))
            out.print("select valid register type");
        else if(registertype.equals("student") && (branchcode==null || branchcode.trim().equals("")))
            out.print("branch field is required");
        else if(registertype.equals("student") && (semester==null || semester.trim().equals("")))
            out.print("semester field is required");
        else if(collegecode==null || collegecode.trim().equals(""))
            out.print("college field is required");
        else if(phone==null || !phone.matches("[0-9]+") || phone.trim().length()!=10)
            out.print("please enter valid phone number");
        else if(email==null || email.trim().equals("") || email.trim().length()<=8 || !email.contains("@"))
            out.print("please enter valid email");
        else if(address==null || address.trim().equals(""))
            out.print("address field is required");
        else {
            if(branchcode==null || semester==null){  
                branchcode = "";
                semester = "";
            }
            if(!BasicDao.isValidCollege(collegecode))
                out.print("you will get nothing but failure v2.4");
            else {
                RegisterBean bean = new RegisterBean();
                bean.setUsername(username);
                bean.setFullname(fullname.trim());
                bean.setPassword(password);
                bean.setPassword2(password2);
                bean.setBranch(branchcode.trim());
                bean.setSemester(semester.trim());
                bean.setCollege(collegecode.trim());
                bean.setPhone(phone.trim());
                bean.setEmail(email.trim());
                bean.setAddress(address.trim());
                bean.setRegisterType(registertype);

                RegisterDao dao = new RegisterDao();
                String status;
                switch (registertype) {
                    case "student":
                        if(!BasicDao.isValidBranch(branchcode) || !BasicDao.isValidSemester(semester))
                        {
                            status="you will get nothing but failure v2.5";
                            break;
                        }
                        status = dao.registerStudent(bean);
                        break;
                    case "professor":
                        status = dao.registerProfessor(bean);
                        break;
                    default:
                        status = "you will get nothing but failure v1.2";
                        break;
                }
                
                if(status.equals("success")) {
                    ResultSet info = null;
                    try {
                        info = BasicDao.getLoggedInUserData(registertype, username);
                        if(info.next()) {
                            HttpSession session = request.getSession();
                            session.setAttribute("uniqueid",info.getString("uniqueid"));
                            session.setAttribute("logintype", registertype);
                            session.setAttribute("collegecode",info.getInt("collegecode"));
                            if(registertype.equals("student")) {
                                session.setAttribute("branchcode",info.getInt("branchcode"));
                                session.setAttribute("semester",info.getInt("semester"));
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        status = "something went worng 1.0.0.0.0.1";
                    }
                    finally {
                        try { info.close(); } catch(Exception e) { }
                    }
                }
                out.print(status);
            }
        }
    }
}
