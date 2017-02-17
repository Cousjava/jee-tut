package com.sleightholme.jeetut.chapter1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class First
 */
@WebServlet({"/index","/"})
public class Index extends HttpServlet {
	private static final long serialVersionUID = 8L;
    
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;
	private String pageTitle;
	
	@Inject
	private ApplicationStore longStore;
	
	@Inject
	private SessionStore sessionsStore;
	
	@Inject
	private ConversationStore convsStore;
	
	@Inject
	private RequestStore requestStore;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out = response.getWriter();
		printHeader();	
		out.println("Served at the index of:" + request.getContextPath());
		
		this.request = request;
		this.response = response;
		printData(longStore);
		printData(sessionsStore);
		printData(convsStore);
		printData(requestStore);
		out.println("<p>Hello, World</p>");
		out.println("Version is " + serialVersionUID);
		printForm();
		
		printFooter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		this.request = request;
		this.response = response;
		printHeader();	
		out.println("Served at the index of:" + request.getContextPath());
		if (request.getParameter("adata")!= null){
			String data = request.getParameter("adata");
			out.println("<p>Added data of "+data+"</p>");
			longStore.addData(data);
		}
		if (request.getParameter("sdata")!= null){
			String data = request.getParameter("sdata");
			out.println("<p>Added data of "+data+"</p>");
			sessionsStore.addData(data);
		}
		if (request.getParameter("cdata")!= null){
			String data = request.getParameter("cdata");
			out.println("<p>Added data of "+data+"</p>");
			convsStore.addData(data);
		}
		if (request.getParameter("rdata")!= null){
			String data = request.getParameter("rdata");
			out.println("<p>Added data of "+data+"</p>");
			requestStore.addData(data);
		}
		if (request.getParameter("resetConv")!=null){
			out.println("<p>Reset conversation");
			convsStore.endConversation();
			convsStore = new ConversationStore();
		}
		printData(longStore);
		printData(sessionsStore);
		printData(convsStore);
		printData(requestStore);
		printForm();
		printFooter();
	}

	public void printHeader() throws ServletException, IOException{
		out.println("<!DOCTYPE html>");
		out.println("<html><head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		out.println("<title>CDI Test</title></head>");
		out.println("<body>");
		
		
	}
	
	public void printData(Store store) throws ServletException, IOException {
		ArrayList<String> data = store.getStrings();
		if (data.size() == 0){
			if (store instanceof ApplicationStore){
				out.println("There is no data stored in the application scope");
			} else if (store instanceof SessionStore){
				out.println("There is no data stored in the session scope");
			} else if (store instanceof RequestStore){
				out.println("There is no data stored in the request scope");
			} else if (store instanceof ConversationStore){
				out.println("There is no data stored in the conversation scope");
			}
		}
		
		out.println("<p>The data stored in the "+ store.getName() + " scope is:</p>");
		for (String bit : data){
			out.println(bit + "</br>");
		}
	}
	
	public void printForm() throws ServletException, IOException {
		out.println("<p>Store data forever in this form</p>");
		out.println("<form action=\"\" method = 'post'>");
		out.println("<input type=\"text\" name=\"adata\">");
		out.println("<input type=\"submit\" value=\"send\"></form>");
		
		out.println("<p>Store data for this session in this form</p>");
		out.println("<form action=\"\" method = 'post'>");
		out.println("<input type=\"text\" name=\"sdata\">");
		out.println("<input type=\"submit\" value=\"send\"></form>");
		
		out.println("<p>Store data for this conversation in this form</p>");
		out.println("<form action=\"\" method = 'post'>");
		out.println("<input type=\"text\" name=\"cdata\">");
		out.println("<input type=\"submit\" value=\"send\"></form>");
		out.println("<form action=\"\" method = 'post'>");
		out.println("<input type='hidden' value='resetConv'>");
		out.println("<input type='submit' value='Reset Conversation'></form>");
		
		
		out.println("<p>Store data for this request in this form</p>");
		out.println("<form action=\"\" method = 'post'>");
		out.println("<input type=\"text\" name=\"rdata\">");
		out.println("<input type=\"submit\" value=\"send\"></form>");
		
		

	}
	
	public void printFooter() throws ServletException, IOException{
		out.println("</body>");
		out.println("</html>");
	}
	
	
}
