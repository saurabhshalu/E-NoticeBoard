<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.BasicDao"%>
<div class="row">
    <div class="col">
        <h1>Admin panel</h1>
        <div class="table-responsive">
            <table class="table table-condensed table-hover">
                <tbody>
                    <%
                        String logintype1 = session.getAttribute("logintype").toString();
                        ResultSet noticelist = null;

                        if(logintype1.equals("admin"))
                        {
                            noticelist = BasicDao.getListOfPrimeRequest();
                            while(noticelist.next()) {
                                out.println("<tr>");
                                out.println("<td>" + noticelist.getString(2) + " (college: " + noticelist.getString(3) + ")</td>");
                                out.println("<td><button data-id=" + noticelist.getInt(1) + " style='height: 25px; margin: 0px; padding: 0px;' class='btn btn-success btnapprove'>APPROVE</button>");
                                out.println("<button data-id=" + noticelist.getInt(1) + " style='height: 25px; margin: 0px; padding: 0px;' class='btn btn-danger btnreject'>REJECT</button></td>");
                                out.println("</tr>");
                            }
                            try { noticelist.close(); } catch(Exception e) { }
                        }
                    %>
                </tbody>
            </table>
        </div> 
    </div>
</div>