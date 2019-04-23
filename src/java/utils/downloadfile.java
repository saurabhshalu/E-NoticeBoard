package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class downloadfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        HttpSession session = request.getSession();
        if(session.getAttribute("uniqueid")!=null && session.getAttribute("attachment")!=null && session.getAttribute("noticetitle")!=null) {
            String filename = session.getAttribute("attachment").toString();
            String madeupName = session.getAttribute("noticetitle").toString() + filename.substring(filename.indexOf("."));
           // String fullpath = "C:\\VermaTechs\\noticefiles\\" + filename;
            
            String fullpath = request.getServletContext().getRealPath("/allfiles/") + filename;
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + madeupName + "\"");
            OutputStream out;
            try (FileInputStream in = new FileInputStream(fullpath)) {
                out = response.getOutputStream();
                byte[] buffer = new byte[8192];
                int count;
                while((count=in.read(buffer))>0) {
                    out.write(buffer,0,count);
                }
            }
            out.close();                
        }
        else {
            response.setHeader("Content-Type", "Text/Plain");
            response.getWriter().print("not authorized");
        }
    }
}
