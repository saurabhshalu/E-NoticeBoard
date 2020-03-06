<%@page import="utils.MyUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="Dao.BasicDao"%>
<%@page import="java.sql.ResultSet"%>
<div class="Register">
	<div class="container">
		<div class="row">
			<div class="col-md-8 m-auto">
				<h1 class="display-4 text-center">Registration Form</h1>
				<p class="lead text-center">Register yourself in E-Notice Board
					to access exciting features</p>
				<form>
					<div class="form-group">
						<input type="text" class="form-control"
							placeholder="Enrollment Number" id="username" name="username" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Full name"
							id="fullname" name="fullname" />
					</div>
					<div class="form-group">
						<div class="input-group">
							<input type="password" class="form-control"
								placeholder="Password" id="password" name="password" /> <span
								class="input-group-addon">-</span> <input type="password"
								class="form-control" placeholder="Retype password"
								id="password2" name="password2" />
						</div>
					</div>
					<div class="form-group" id="studentSection">
						<div class="input-group">
							<select id="branch" class="form-control">
								<option selected disabled hidden value="0">Select
									branch</option>
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
								<option selected disabled hidden value="0">Select
									semester</option>
								<option value="1">Semester 1</option>
								<option value="2">Semester 2</option>
								<option value="3">Semester 3</option>
								<option value="4">Semester 4</option>
								<option value="5">Semester 5</option>
								<option value="6">Semester 6</option>
								<option value="7">Semester 7</option>
								<option value="8">Semester 8</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<select id="college" class="form-control">
							<option selected disabled hidden value="0">Select
								College</option>
							<%
                        ResultSet rs1 = BasicDao.getCollegeList(con);
                        if(rs1!=null) {
                            while(rs1.next()) {
                            %>
							<option value="<%=rs1.getInt(1)%>"><%=rs1.getString(2)%></option>
							<%    
                            }
                        }
                        try { rs1.close(); } catch(Exception e) { }
                    %>
						</select>
					</div>
					<div class="form-group">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Phone number" id="phone" name="phone" /> <span
								class="input-group-addon">-</span> <input type="email"
								class="form-control" placeholder="Email address" id="email"
								name="email" />
						</div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Address"
							id="address" name="address" />
					</div>
					<div class="form-group">
						<label class="radio-inline"><input type="radio"
							name="registertype" value="student" checked
							onclick="document.getElementById('username').placeholder='Enrollment Number';document.getElementById('studentSection').style.display='block';$('#error').hide();">&nbsp;Student&nbsp;&nbsp;&nbsp;</label>
						<label class="radio-inline"><input type="radio"
							name="registertype" value="professor"
							onclick="document.getElementById('username').placeholder='Professor ID';document.getElementById('studentSection').style.display='none';$('#error').hide();">&nbsp;Professor</label>
						<span id="error" style="color: red; display: none;"
							class="float-right">Please enter valid data!</span>
					</div>
					<div class="form-group">
						<input type="button" class="btn btn-info btn-block mt-4"
							name="btnRegister" id="btnRegister" value="Register" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
