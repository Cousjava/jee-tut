package com.sleightholme.jeetut.mysql;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class mysqlexample
 */
@WebServlet({ "/mysqlexample", "/MySQl" })
public class mysqlexample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected PrintWriter out;
	
	@Inject
	Queries q;
	
	@Resource
	DataSource ds;
	
	HashMap<String, Integer> data;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mysqlexample() {
        super();
    }
    
    protected void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out = response.getWriter();
		header();
		if (ds == null){
			out.println("Data source in servlet is null");
		} else {
			out.println("Data source is valid!");
		}
		
		try {
			
			q.postConstruct();
			setupData();
			for (String rname : data.keySet()){
				q.insertData(rname, data.get(rname));
			}
			ResultSet rs = q.getData();
			if (rs == null){
				out.println("<p>Unable to retrive data</p>");
			} else {
				printData(rs);
			}
			
		} catch (Exception e) {
			out.println("<p>Error with database...</p>");
			e.printStackTrace();
		}
		
		
		
		out.println("<p>8</p>");
		out.append("Served at: ").append(request.getContextPath());
		
		footer();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		run(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		run(request, response);
	}

	private void header(){
		out.println("<html>");
		out.println("<head>");
		out.println("<title>MySQL Connection Example</title>");
		out.println("</head>");
		out.println("<body>");
	}
	
	private void footer(){
		out.println("</body>");
		out.println("</html>");
	}
	
	private void setupData(){
		data = new HashMap<String, Integer>();
		data.put("Amazon",3889);
		data.put("Madeira", 2020);
		data.put("Japura", 1995);
		data.put("Tocantins", 1640);
		data.put("Araguia", 1632);
		data.put("Jurua", 1500);
		data.put("Rio Negro", 1400);
		data.put("Tapajos", 1238);
		data.put("Xingu", 1230);
		data.put("Ucayali", 1200);
		data.put("Guapore", 1087);
		
	}
	
	private void printData(ResultSet rs){
		out.println("<table>");
		try {
			while (rs.next()){
				
				int id = rs.getInt(1);
				String rname = rs.getString(2);
				int length = rs.getInt(3);
				out.print("<tr><td>" + id + "</td>");
				out.print("<td>" + rname + "</td><td>" + length + "</td></tr>\n");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		out.println("</table>");
	}
	
}
