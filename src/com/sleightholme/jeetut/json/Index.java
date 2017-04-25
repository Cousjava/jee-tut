package com.sleightholme.jeetut.json;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sleightholme.jeetut.util.ExtendedServlet;

/**
 * Servlet implementation class Index
 */
@WebServlet(name = "Json/Index", urlPatterns = { "/Json/Index"})
public class Index extends ExtendedServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		out.println("<a href=\"" + root +"/Json/Stream\">Stream API Example</a>&nbsp;");
		out.println("<a href=\"" + root +"/Json/Blob\">Object API Example</a>&nbsp;");
		out.println(" 1 <p>");
		Enumeration<String> attrs = request.getAttributeNames();
		while (attrs.hasMoreElements()) {
			String string = (String) attrs.nextElement();
			out.println(string + " : ");
			out.println(request.getAttribute(string) + "</br>");
		}
		out.println("</p>");
		footer();
	}

}
