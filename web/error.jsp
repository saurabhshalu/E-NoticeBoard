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
        <%@include file="WEB-INF/header.jsp" %>
        
        <div id="mainContainerCss">
            <div id="mainContentCss" class="container text-light">
                <div align="center" class="cards">
                    <section class="card">
                        <img class="card_img" src="${pageContext.request.contextPath}/allfiles/404.jpg" alt="404" style="width:100%">
                        <h2 class="cardblack">Awww...Don't cry</h2>
                        <p class="cardtitle">It's just an another error</p>
                        <p class="cardblack">What you are looking for may have been misplaced in Long term memory</p>
                        <p><button class="cardbutton" onclick="window.history.go(-1); return false;">Go Back</button></p>
                    </section>
                </div>
            </div>
        </div>

        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>