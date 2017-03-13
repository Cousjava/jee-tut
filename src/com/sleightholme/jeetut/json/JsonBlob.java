package com.sleightholme.jeetut.json;

import com.sleightholme.jeetut.util.ExtendedServlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.json.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JsonBlob
 */
@WebServlet("/Json/Blob")
public class JsonBlob extends ExtendedServlet {
	private static final long serialVersionUID = 1L;
    
	private String title = "JSON Reader Example";
	
	JsonArray obj;
	
    /**
     * @see ExtendedServlet#ExtendedServlet()
     */
    public JsonBlob() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		readData("/test.json");
		displayObject(obj);
		writeObject(obj, "json/out.json");
		footer();
	}
	
	
	private void readData(String filepath){
		JsonReader read;
		try {
			read = Json.createReader(getServletContext().getResourceAsStream(filepath));
			
			obj = read.readArray();
		} catch (NullPointerException e) {
			out.println("File not found");
			e.printStackTrace();
		}
		
		
	}
	
	private void displayObject(JsonArray obj){
		out.println(obj);
	}
	
	private void writeObject(JsonArray obj, String filepath){
		
	}

}
