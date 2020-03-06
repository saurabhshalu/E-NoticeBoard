<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.BasicDao"%>
<%@page import="utils.MyUtils"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col">
		<div class="table-responsive">
			<table class="table table-condensed table-hover">
				<tbody>
					<%
                        try {
                            Connection con = MyUtils.getStoredConnection(request);
                            String semester = request.getParameter("semester");
                            String branch = request.getParameter("branch");
                            String college = session.getAttribute("collegecode").toString();
                            ResultSet filteredList = BasicDao.FilterStudent(con, semester, branch, college);
                            while(filteredList.next()) {
                                out.println("<tr>");
                                out.println("<td>" + "<input type='checkbox' name='chkStudentSelect' value='" + filteredList.getString(2) + "'/>" + filteredList.getString(2) + "</td>");
                                out.println("</tr>");
                            }
                        } catch(Exception e) {
                            System.out.println(e);
                        }  
                    %>
					<tr>
						<td><input type="button" id="btnCheckUncheck"
							value="Select All" class="btn btn-primary" onclick="checkAll();" />
							<input type="button" id="btnPromoteStudents"
							value="Promote to next Semester" class="btn btn-danger"
							onclick="promoteToNextSemester();" />
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
