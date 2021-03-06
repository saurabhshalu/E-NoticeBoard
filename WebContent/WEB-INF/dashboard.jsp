<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>E-Notice Board</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="header.jsp"%>
	
	<div id="mainContainerCss">
		<div id="mainContentCss" class="container text-light">
			<br />
			<%
                    if(session.getAttribute("logintype")!=null && session.getAttribute("logintype").toString().equals("admin")) {
                %>
				<%@include file="adminpanel.jsp"%>		
			<%        
                    } else {
                %>
			<div class="col">
				<%@include file="addnotice.jsp"%>
			</div>
			<% 
                    } 
                %>
		</div>
	</div>
		<%@include file="footer.jsp"%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
</body>
</html>
