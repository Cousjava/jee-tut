package com.sleightholme.jeetut.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExtendedServlet
 */
@WebServlet("/ExtendedServlet")
public class ExtendedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected PrintWriter out;
	protected String title = "";
	protected String root;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExtendedServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		root = getServletContext().getContextPath();
		//header();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void header(){
		out.println("<html>");
		out.println("<head>");
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body>");
	}
	
	public void footer(){
		out.println("</body></html>");
	}

}
