<%@page import="utils.MyUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="Dao.BasicDao"%>
<head>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico">
</head>
<header>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/dashboard">E-Notice
				Board</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#mobile-nav">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="mobile-nav">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/developers">
							Developers </a></li>
				</ul>

				<ul class="navbar-nav ml-auto">
					<%
						if(!request.getRequestURI().contains("developer")){
					%>
					<li class="dropdown">
						<% if(session.getAttribute("uniqueid")!=null) { %> <a
						class="dropdown-toggle nav-link" data-toggle="dropdown" href="#">Welcome
							<%= session.getAttribute("uniqueid") %></a>
						<ul class="dropdown-menu bg-dark">
							<%
                        if(session.getAttribute("logintype").equals("professor")) {
                    %>
							<li><a class="nav-link" href="viewnotice?id=all">View all notice</a></li>
							<% 
                            Connection con = MyUtils.getStoredConnection(request);
                            if(!BasicDao.isSpecialUser(con,session.getAttribute("uniqueid").toString(), Integer.parseInt(session.getAttribute("collegecode").toString()))) {%>
							<li><a class="nav-link" href="#" id="requestprime">Request
									Rights</a></li>
							<% 
                            } else {
                            %>
							<li><a class="nav-link" href="#" id="menuPromote">Promote
									students</a></li>
							<% 
                                }
                            %>

							<%        
                        } else {
                    %>
							<li><a class="nav-link" href="#"
								onclick="showAlert('Professor will share google drive link on notice board, kindly upload your assignment there only!')">Submit
									Assignment</a></li>
							<%        
                        } 
                    %>
						</ul> <% } else { %> <a id="register" class="btnSignUp nav-link" href="#">Sign
							Up</a> <% }%>
					</li>
					<%
						}
					%>
					<li class="nav-item">
						<% if(session.getAttribute("uniqueid")!=null) { %> <a id="logout"
						class="nav-link" href="#">Logout</a> <% } else { %> <a id="login"
						class="btnLogin nav-link" href="#">Login</a> <% }%>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>
<!-- Including loading screen -->
<%@include file="loader.html"%>
