package com.sleightholme.jeetut;

import com.sleightholme.jeetut.util.ExtendedServlet;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan coustick
 */
@WebServlet(name = "ViewHeaders", urlPatterns = {"/ViewHeaders"})
public class ViewHeaders extends ExtendedServlet {

    private static int called = 0;
    
    @Override
    public void init() {
        title = "View Headers";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        header();
        out.println("<h1>Request Headers:</h1>");
        printHeaders(request);
        out.println("<p>");
        
        out.println("Last Modified: " +  LocalDateTime.ofInstant(Instant.ofEpochMilli(lastModified), TimeZone.getTimeZone("GMT").toZoneId()).toString());
        called++;
        out.println("<p>Called times: " + called + "</p>");
        out.println("Cookies are: <p>");
        for (Cookie cookie: request.getCookies()){
            out.println(cookie.getName() + ":" + cookie.getValue() + cookie.toString());
        }
        out.println("Auth type is " + request.getAuthType() + "<p>");
        out.println("Principal is " + request.getUserPrincipal());
        newLine();
        footer();        
    }
    
    private void printHeaders(HttpServletRequest req) {
        Enumeration<String> headernames = request.getHeaderNames();
        while (headernames.hasMoreElements()) {
            String header = headernames.nextElement();
            out.println(header + " : " + req.getHeader(header) + "<br>");
        }
        
    }

}
