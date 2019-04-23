<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.BasicDao"%>
<div class="table-responsive">
    <table class="table table-condensed table-hover">
        <tbody>
            <%
                int collegecode = -9999;
                String uniqueid = null;
                int semester = -999;
                int branchcode = -999;
                String logintype = request.getParameter("logintype");
                String view = request.getParameter("view");
                if(view==null)
                    view = "none";
                
                if(logintype.equals("student")) {
                    semester = Integer.parseInt(session.getAttribute("semester").toString());
                    branchcode = Integer.parseInt(session.getAttribute("branchcode").toString());
                }
                
                collegecode = Integer.parseInt((String)session.getAttribute("collegecode").toString());
                uniqueid = (String)session.getAttribute("uniqueid").toString();
                
                ResultSet noticelist = null;
                
                if(logintype.equals("student"))
                    noticelist = BasicDao.getCurrentStudentNotice(collegecode,semester,branchcode);
                else
                    noticelist = BasicDao.getCurrentTeacherNotice(collegecode,uniqueid,view);
                
                while(noticelist.next()) {
                    out.println("<tr data-id=" + noticelist.getInt(1) + " class='preview_data_id_notice'>");
                    //out.println("<td>" + noticelist.getString(2) + " | <span style='color: red;'>" + noticelist.getDate(8) + " to " + noticelist.getString(9) + "</span> | " + "</td>");
                    out.println("<td>" + noticelist.getString(2) + "</td>");
                    out.println("</tr>");
                }
                try { noticelist.close(); } catch(Exception e) { }
            %>
        </tbody>
    </table>
</div>