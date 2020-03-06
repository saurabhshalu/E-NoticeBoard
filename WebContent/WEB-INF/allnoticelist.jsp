<%@page import="java.util.List"%>
<%@page import="Beans.NoticeBean"%>
<%@page import="utils.MyUtils"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Dao.BasicDao"%>
<div class="noticecontainer">
	<div class="table-responsive">
		<table class="table table-condensed table-hover">
			<tbody>
				<%
                int collegecode = -9999;
                String uniqueid = null;
                int semester = -999;
                int branchcode = -999;
                String ltype = session.getAttribute("logintype").toString();// request.getParameter("logintype");
                String view = request.getParameter("view");
                if(view==null)
                    view = "none";
                
                if(ltype.equals("student")) {
                    semester = Integer.parseInt(session.getAttribute("semester").toString());
                    branchcode = Integer.parseInt(session.getAttribute("branchcode").toString());
                }
                
                collegecode = Integer.parseInt((String)session.getAttribute("collegecode").toString());
                uniqueid = (String)session.getAttribute("uniqueid").toString();
                
                List<NoticeBean> noticelist = null;
                Connection con = MyUtils.getStoredConnection(request);
                if(ltype.equals("student"))
                    noticelist = BasicDao.getCurrentStudentNotice(con,collegecode,semester,branchcode);
                else
                    noticelist = BasicDao.getCurrentTeacherNotice(con,collegecode,uniqueid,view);
                
                for(NoticeBean item : noticelist) {
                    out.println("<tr data-id=" + item.getId() + " class='preview_data_id_notice'>");
                    //out.println("<td>" + noticelist.getString(2) + " | <span style='color: red;'>" + noticelist.getDate(8) + " to " + noticelist.getString(9) + "</span> | " + "</td>");
                    out.println("<td>" + item.getTitle() + "</td>");
                    out.println("</tr>");
                }
            %>
			</tbody>
		</table>
	</div>
</div>