<%@page import="utils.MyUtils"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.BasicDao"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col">
	<div class="row">
		<div class="col">
			<form>
				<div class="input-group">
					<select id="branch" class="form-control">
						<option selected disabled hidden="hidden" value="0">Select branch</option>
						<%
                        Connection con = MyUtils.getStoredConnection(request);
                        ResultSet rs = BasicDao.getBranchList(con);

                        if(rs!=null) {
                            while(rs.next()) {
                            %>
						<option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>
						<%    
                            }
                        }
                        try { rs.close(); } catch(Exception e) { }
                    %>
					</select> <span class="input-group-addon">-</span> <select id="semester"
						class="form-control">
						<option selected disabled hidden="hidden" value="0">Select
							semester</option>
						<option value="1">Semester 1</option>
						<option value="2">Semester 2</option>
						<option value="3">Semester 3</option>
						<option value="4">Semester 4</option>
						<option value="5">Semester 5</option>
						<option value="6">Semester 6</option>
						<option value="7">Semester 7</option>
						<option value="8">Semester 8</option>
					</select> <input type="button" class="btn btn-info btn-block mt-4"
						name="btnFilter" id="btnFilter" value="Search"
						onclick="filterStudent();" />
				</div>
			</form>
		</div>
	</div>
	<br />
	<div id="promotionList"></div>
</div>