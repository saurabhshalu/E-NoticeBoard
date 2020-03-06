package controller;

import Beans.NoticeBean;
import Dao.BasicDao;
import Dao.NoticeDao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.MyUtils;
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = MyUtils.getStoredConnection(request);
        
        response.setHeader("Content-Type", "text/plain");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if(session.getAttribute("uniqueid")==null || session.getAttribute("logintype")==null || !session.getAttribute("logintype").equals("professor")) {
            out.print("not authorized");
            return;
        }
        try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> uploadItems = upload.parseRequest(request);
            
            String noticetitle = null;
            String noticebody = null;
            String branchcode = null;
            String semester = null;
            String start_date = null;
            String end_date = null;
            int collegecode = (Integer)session.getAttribute("collegecode");
            String professorcode = (String)session.getAttribute("uniqueid");
            String filename = null;
            boolean isUploaded = false;
            
            for(FileItem uploadItem : uploadItems) {
                if(uploadItem.isFormField()) {
                    switch(uploadItem.getFieldName()){
                        case "noticetitle":
                            noticetitle = uploadItem.getString();
                            break;
                        case "noticebody":
                            noticebody = uploadItem.getString();
                            break;
                        case "branchcode":
                            branchcode = uploadItem.getString();
                            break;
                        case "semester":
                            semester = uploadItem.getString();
                            break;
                        case "start_date":
                            start_date = uploadItem.getString();
                            break;
                        case "end_date":
                            end_date = uploadItem.getString();
                            break;
                        default:
                            System.out.println(uploadItem.getString());
                    }
                }
                else {
                    if(uploadItem.getSize()>5*1024*1024) {
                        out.print("file size must not exceed 5MB");
                        return;
                    }
                    filename = BasicDao.getFileName(con,uploadItem.getName(),collegecode,professorcode);
                    InputStream content = uploadItem.getInputStream();
                    
                    String fullpath = request.getServletContext().getRealPath("/allfiles/") + filename;

                    try (FileOutputStream fileOut = new FileOutputStream(fullpath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while((bytesRead = content.read(buffer))!=-1) {
                            fileOut.write(buffer,0,bytesRead);
                        }                  
                        isUploaded = true;
                    }
                }  
            }
            
            if(filename!=null && !isUploaded) {
                out.print("something went wrong while uploading file.");
                return;
            }
            
            if(noticetitle==null || noticebody==null || noticetitle.trim().equals("") || noticebody.trim().equals("")) {
                out.print("enter valid information");
                return;
            }
            if(semester==null) {
                out.print("semester is required");
                return;
            }
            if(!BasicDao.isValidSemester(semester) && !semester.equals("0")) {
                out.print("select valid semester");
                return;
            }
            if(branchcode==null || branchcode.trim().equals("") || !BasicDao.isValidBranch(con,branchcode)) {
                out.print("select valid branch");
                return;
            }
            if(start_date==null || !BasicDao.isValidDate(start_date)) {
                out.print("select valid start date");
                return;
            }
            if(end_date==null || !BasicDao.isValidDate(end_date)) {
                out.print("select valid end date");
                return;
            }
            if(!BasicDao.isValidDateRange(start_date,end_date)){
                out.print("select valid duration date");
                return;
            } 
            
            NoticeBean bean = new NoticeBean();
            bean.setTitle(noticetitle);
            bean.setBody(noticebody);
            if(filename==null)
                bean.setAttachment("");
            else
                bean.setAttachment(filename);
            bean.setSemester(semester);
            bean.setBranch(branchcode);
            bean.setCollege(String.valueOf(collegecode));
            bean.setStartDate(start_date);
            bean.setEndDate(end_date);
            bean.setProfessorCode(professorcode);
            
            NoticeDao dao = new NoticeDao();
            String status = dao.submitNotice(con,bean);
            
            out.print(status);
            
        } catch(FileUploadException | IOException e) {
            System.out.println(e);
            out.print("please enter valid data" + e.toString());
        }
    }
}