package com.sleightholme.jeetut.util;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import org.apache.jasper.servlet.JspServlet;

/**
 *
 * @author jonathan
 */
public class ExtendedJspServlet extends JspServlet {
    
    protected String title = "";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    @Override
    public void service(HttpServletRequest request, 
    			HttpServletResponse response)
                throws ServletException, IOException {
        super.service(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public void header(JspWriter out) throws IOException {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

    public void footer(JspWriter out) throws IOException {
        out.println("</body></html>");
    }

    
}
