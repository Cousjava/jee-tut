package com.sleightholme.jeetut;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "Index", urlPatterns = {"/", "/Index", "/index"})
public class Index extends ExtendedServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        title = "Java EE Examples Index";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        out.println("<h1>Java EE Examples</h1>");
        out.println("<p><a href=\"" + root + "/cdi\">CDI</a></p>");
        out.println("<p><a href=\"" + root + "/EJBExample\">EJBs</a></p>");
        out.println("<p><a href=\"" + root + "/book\">JAX-WS</a></p>");
        out.println("<p><a href=\"" + root + "/JobServlet\">JBatch</a></p>");
        out.println("<p><a href=\"" + root + "/Jpa/Manager\">JPA</a></p>");
        out.println("<p><a href=\"" + root + "/Json/Index\">JSON</a></p>");
        out.println("<p><a href=\"" + root + "/Mysql\">MySQL</a></p>");
        out.println("<p><a href=\"" + root + "/Validation\">Bean Validation</a></p>");
        out.println("<p><a href=\"" + root + "/Concurrency/\">Concurrency</a></p>");
        out.println("<p><a href=\"" + root + "/JMSSender\">Java Messaging Service</a></p>");
        out.println("<p><a href=\"" + root + "/Login\">Soteria Security</a></p>");
        
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        out.println("The default MBeanServer is " + mBeanServer.getDefaultDomain() + "</br>");
        for (String server : mBeanServer.getDomains()){
            out.println("A MBeanServer is " + server + "</br>");
        }
        
        out.println("The JVM is " + System.getProperty("java.vm.name") + "</br>");
        
        footer();
    }

}