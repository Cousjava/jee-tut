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
    
    @Override
    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;  

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void header() {
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

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
    }

    protected void newLine() {
        out.println("</br>");
    }

    @Override
    public String getServletInfo() {
        return title;
    }

}
