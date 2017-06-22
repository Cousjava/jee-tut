package com.sleightholme.jeetut;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        header();
        out.println("<h1>Java EE Examples</h1>");
        out.println("<p><a href=\"cdi\">CDI</a></p>");
        out.println("<p><a href=\"EJBExample\">EJBs</a></p>");
        out.println("<p><a href=\"book\">JAX-WS</a></p>");
        out.println("<p><a href=\"JobServlet\">JBatch</a></p>");
        out.println("<p><a href=\"JPA/Manager\">JPA</a></p>");
        out.println("<p><a href=\"Json/Index\">JSON</a></p>");
        out.println("<p><a href=\"Mysql\">MySQL</a></p>");
        footer();
    }

}
