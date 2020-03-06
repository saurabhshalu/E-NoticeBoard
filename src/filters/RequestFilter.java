package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class RequestFilter implements Filter {
   // private List<String> excludedUrls;
   // private String[] excludedStartWith;
    
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        //System.out.println("Before request!!");
        
        /*
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String path = req.getServletPath();
        
        boolean isExcludedStart = false;
        for (String excluded : excludedStartWith) {
            if (path.startsWith(excluded)) {
                isExcludedStart = true;
                break;
            }
        }
        
        if(!isExcludedStart & !excludedUrls.contains(path))
        {
            res.sendRedirect(req.getContextPath());
            return;
        }
        else if(path.equals("/dashboard")) {
            HttpSession session = req.getSession();
            if(session.getAttribute("username")!=null) {
                req.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(req,res);
                return;
            }
            else {
                res.sendRedirect("./");
                return;
            }
        }
        */
        
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        
        System.out.println(path);
        
        switch(path) {
            case "/index.jsp":
                if(session.getAttribute("uniqueid")!=null) {
                    res.sendRedirect("./dashboard");
                    return;
                }
                break;
            case "/dashboard":
                if(session.getAttribute("uniqueid")==null) {
                    res.sendRedirect("./");
                    return;
                }
                break;
        }
        
        /*
        if(path.equals("/index.jsp")) {
            if(session.getAttribute("uniqueid")!=null) {
                res.sendRedirect("./dashboard");
                return;
            }
        }
        
        if(path.equals("/dashboard")) {
            if(session.getAttribute("uniqueid")==null) {
                res.sendRedirect("./");
                return;
            }
        }
        */
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*
        String s1 = filterConfig.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(s1.split(","));
        String s2 = filterConfig.getInitParameter("excludedStartWith");
        excludedStartWith = s2.split(",");
        */
    }
    
}
