package com.sleightholme.jeetut.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExtendedServlet
 */
public abstract class ExtendedServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected PrintWriter out;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String title = "";
    protected String root;
    protected long lastModified = -1;
    
    @Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;  

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Print out the HTML header and opens the body of the page
     */
    public void header() {
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

    /**
     * Prints out the end of html body and page
     */
    public void footer() {
        out.println("</body></html>");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        this.request = request;
        this.response = resp;
        out = response.getWriter();
        root = getServletContext().getContextPath();
        super.service(request, resp);
        lastModified = System.currentTimeMillis();
    }

    protected void newLine() {
        out.println("</br>");
    }

    @Override
    public String getServletInfo() {
        return title;
    }
    
    @Override
    protected long getLastModified(HttpServletRequest req){
        return lastModified;
    }  

}
