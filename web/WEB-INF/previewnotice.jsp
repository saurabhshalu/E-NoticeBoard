<%@page import="Beans.NoticeBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.BasicDao"%>
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
        <%@include file="header.jsp" %>
        <div id="mainContainerCss">
            <div id="mainContentCss" class="container text-light">
                <div align="center" class="cards">
                    <%
                        int id = Integer.parseInt(request.getParameter("id")==null?"-999":request.getParameter("id"));
                        boolean isValid = false;
                        NoticeBean currentnotice = null;
                        if(session.getAttribute("logintype")!=null)
                        {
                            isValid=true;
                            int collegecode = Integer.parseInt((String)session.getAttribute("collegecode").toString());
                            String logintype = session.getAttribute("logintype").toString();
                            int semester = -999;
                            int branchcode = -999;
                            if(logintype.equals("student")) {
                                semester = Integer.parseInt(session.getAttribute("semester").toString());
                                branchcode = Integer.parseInt(session.getAttribute("branchcode").toString());
                            }
                            Connection con = MyUtils.getStoredConnection(request);
                            currentnotice = BasicDao.previewSelectedNotice(con, id, collegecode, logintype, semester, branchcode);     
                        }
                        
                        if(isValid==true && currentnotice!=null) {
                    %>
                        <section class="card noticecard">
                            <h2 class="cardblack"><%=currentnotice.getTitle()%></h2>
                            <p class="cardtitle"><%=currentnotice.getBody()%></p>
                            <p class="cardblack">Semester: <%=Integer.parseInt(currentnotice.getSemester())==0?"ALL":currentnotice.getSemester()%> (branchcode: <%=String.format("%02d", Integer.parseInt(currentnotice.getBranch()))%>)</p>
                            <p class="cardtitle">(<%=new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(currentnotice.getStartDate()))%> to <%=new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(currentnotice.getEndDate()))%>)</p>
                            <%
                                if(!currentnotice.getAttachment().equals("")) {
                                    session.setAttribute("attachment", currentnotice.getAttachment());
                                    session.setAttribute("noticetitle", currentnotice.getTitle().trim());
                                %>
                                    <p class="cardtitle"><a href='downloadfile'>Download Attached File</a></p>
                                <%
                                }
                                if(session.getAttribute("uniqueid").toString().equals(currentnotice.getProfessorCode())) {
                            %>
                            <p><button class="cardbutton" onclick="window.history.go(-1); return false;">Delete</button></p>
                            <% } else { %>
                                <p class="cardtitle">submitted by (<%=currentnotice.getProfessorCode()%>)</p>
                            <%}%>
                            <p><button class="cardbutton" onclick="window.history.go(-1); return false;">Go Back</button></p>
                        </section>
                    <%
                        } else {
                    %>
                        <section class="card">
                            <img class="card_img" src="${pageContext.request.contextPath}/img/404_1.jpg" alt="404" style="width:100%">
                            <h2 class="cardblack">Awww...Don't cry</h2>
                            <p class="cardtitle">It's just a 404 error</p>
                            <p class="cardblack">What you are looking for may have been misplaced in Long term memory</p>
                            <p><button class="cardbutton" onclick="window.history.go(-1); return false;">Go Back</button></p>
                        </section>
                    <% 
                        }
                    %>
                </div>
            </div>
        </div>
     
        <%@include file="footer.jsp" %>     
    </body>
</html>