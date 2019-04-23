<%@page import="Dao.BasicDao"%>
<%@page import="java.sql.ResultSet"%>
<div class="noticecontainer">
    
    <%
        String logintype = session.getAttribute("logintype").toString();
        if(logintype.equals("professor")) {
    %>
    <div class="leftnotice">
        <form id="myNoticeForm" enctype="multipart/form-data" method="post">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Title" id="noticetitle" name="noticetitle" />
          </div>
          <div class="form-group">
                <textarea class="form-control" rows="5" placeholder="content here" id="noticebody" name="noticebody"></textarea> 
          </div>
          <div class="form-group">
            <input type="file" class="form-control" id="attachment" name="attachment" accept="application/pdf, application/msword, image/jpg, image/jpeg, image/png"/>
          </div>
          <div class="form-group">
               <div class="input-group">
                   <select id="semester" class="form-control">
                        <option selected disabled hidden value="-1">Select semester</option>
                        <option value="0">All</option>
                        <option value="1">Semester 1</option>
                        <option value="2">Semester 2</option>
                        <option value="3">Semester 3</option>
                        <option value="4">Semester 4</option>
                        <option value="5">Semester 5</option>
                        <option value="6">Semester 6</option>
                        <option value="7">Semester 7</option>
                        <option value="8">Semester 8</option>
                    </select>
                   <span class="input-group-addon">-</span>
                    <select id="branch" class="form-control">
                        <option selected disabled hidden value="0">Select branch</option>
                        <%
                            ResultSet rs = BasicDao.getBranchList();
                            if(rs!=null) {
                                while(rs.next()) {
                                %>  
                                <option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>
                                <%    
                                }
                            }
                            try { rs.close(); } catch(Exception e) { }
                        %>
                    </select>
               </div>
          </div>
            <div class="form-group">
               <div class="input-group">
                   <input type="text" class="form-control" id="start_date" name="start_date"/>
                   <span class="input-group-addon">-</span>
                   <input type="text" class="form-control" id="end_date" name="end_date" />
               </div>
            </div>
            <div class="form-group">
                <span id="error" style="color:red; display: none;" class="float-right">Invalid user credentials</span>
                <input type="submit" class="btn btn-info btn-block mt-4" name= "btnSubmitNotice" id="btnSubmitNotice" value="Submit"/>
            </div>
        </form>
    </div>
    <% 
        }
    %>
    <div class="rightnotice">  
        <jsp:include page="./WEB-INF/allnoticelist.jsp">
            <jsp:param name="logintype" value="<%=logintype%>"/>
        </jsp:include>
    </div>
</div>
<br/>