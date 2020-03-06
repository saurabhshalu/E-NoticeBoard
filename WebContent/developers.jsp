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
                <div align="center" class="cards">
                    <section class="card">
                        <img class="card_img" src="${pageContext.request.contextPath}/img/saurabh.jpg" alt="Saurabh" style="width:100%">
                        <h1 class="cardblack">Saurabh Verma</h1>
                        <p class="cardtitle">CEO &amp; Founder, Developer</p>
                        <p class="cardblack">Google</p>
                        <p><button class="cardbutton" onclick="window.open('https://github.com/saurabhshalu','_blank')">Contact</button></p>
                    </section>
                </div>
            </div>
        </div> 
        <%@include file="WEB-INF/footer.jsp"%>  
    </body>
</html>