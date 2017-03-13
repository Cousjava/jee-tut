package com.sleightholme.jeetut.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sleightholme.jeetut.util.ExtendedServlet;

/**
 * Servlet implementation class JsonStream
 */
@WebServlet("/Json/Stream")
public class JsonStream extends ExtendedServlet {
	private static final long serialVersionUID = 1L;
    
	private String title = "Json Stream Test";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonStream() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		readData("/test.json");
		
		footer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void readData(String filepath){		
		try (JsonParser read = Json.createParser(getServletContext().getResourceAsStream(filepath))){
			if (read != null){
				out.println("<p>File exists!</p>");	
			}
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			
			JsonObject strobj = null;
			JsonArray strarr = null;
			JsonParser.Event event = read.next();
			
			switch (event){
			case START_ARRAY:
				strarr = arrayBuilder.build(); break;
			case START_OBJECT:
				strobj = objectBuilder.build(); break;
			default:
				throw new JsonException(filepath);	
			}
			
			JsonObject currobj;
			JsonArray curarr;
			
			while(read.hasNext()){
				event = read.next();
				switch(event) {
				case START_ARRAY:
					curarr = arrayBuilder.build(); break;
			      case END_ARRAY:
			    	  if (strobj != null){
			    		  //TODO
			    	  }
			      case START_OBJECT:
			      case END_OBJECT:
			      case VALUE_FALSE:
			      case VALUE_NULL:
			      case VALUE_TRUE:
			         System.out.println(event.toString());
			         break;
			      case KEY_NAME:
			         System.out.print(event.toString() + " " +
			                          read.getString() + " - ");
			         break;
			      case VALUE_STRING:
			      case VALUE_NUMBER:
			         System.out.println(event.toString() + " " +
			                            read.getString());
			         break;
				
				}
			}
			out.println("Succesfully read data...");
		} catch (NullPointerException e) {
			out.println("<p>JSON file not found</p>");
			e.printStackTrace();
		}
		
		
	}
	

}
