<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>E-Notice Board</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp"%>
        <div id="mainContainerCss">
            <div id="mainContentCss" class="container text-light">
                <% if(session.getAttribute("username")!=null) { request.getRequestDispatcher("dashboard").forward(request,response); }%>
                <% if(request.getParameter("mode")!=null) {%>
                    <% if(request.getParameter("mode").equals("login")) { %>
				        <%@include file="WEB-INF/login.jsp"%>
                    <%
                    }
                    else if(request.getParameter("mode").equals("register")) { %>
				        <%@include file="WEB-INF/register.jsp"%>
                    <%
                    }
                    else { %>
				        <%@include file="WEB-INF/landing.html"%>
                    <%
                    }
                    %>
                    <% } else { %>
                        <%@include file="WEB-INF/landing.html"%>
                <% } %> 
            </div>
        </div>     
        <%@include file="WEB-INF/footer.jsp"%>	
    </body>
</html>