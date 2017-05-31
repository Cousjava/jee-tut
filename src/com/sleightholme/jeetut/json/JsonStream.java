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
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		header();
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
			
			JsonObject currObj;
			JsonArray curarr;
			
			int indents = 0;
			out.print("<div>");
			switch (event){
			case START_ARRAY:
				//strarr = arrayBuilder; break;
			case START_OBJECT:
				//strobj = objectBuilder.build();
				indents++;
				break;
			default:
				throw new JsonException(filepath);	
			}
			
			
			while(read.hasNext()){
				event = read.next();
				indent(indents);
				switch(event) {
				case START_ARRAY:
					
					curarr = arrayBuilder.build(); 
					out.println("[</br>");
					indents++;
					break;
			      case END_ARRAY:
			    	  if (strobj != null){
			    		
			    		  //TODO
			    	  }
			    	  indents--;
			    	  out.println("],</br>");
			    	  break;
			      case START_OBJECT:
			    	  indents++;
			    	  out.println("{</br>");break;
			      case END_OBJECT:
			    	  currObj = objectBuilder.build();
			    	  out.println("},</br>");
			    	  indents--;
			    	  break;
			      case VALUE_FALSE:
			    	  out.println("false,</br>");
			    	  break;
			      case VALUE_NULL:
			    	  out.println("null,</br>");
			    	  break;
			      case VALUE_TRUE:
			         out.println("true,</br>");
			         break;
			      case KEY_NAME:
			         out.print(read.getString() + " : ");
			         break;
			      case VALUE_STRING:
			      case VALUE_NUMBER:
			         out.println(read.getString() + ",</br>");
			         break;
				
				}
			}
			out.print("</div>");
			out.println("Succesfully read data...");
		} catch (Exception e) {
			out.println("<p>Error reading from file. See log for details.</p>");
			e.printStackTrace();
		}
		
		
	}
	
	private void indent(int amount){
		for (int i = 0; i < amount; i++){
			out.print("&emsp;&emsp;");
		}
	}
	

}
