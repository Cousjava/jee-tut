package com.sleightholme.jeetut.json;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Json/rs")
public class JsonRS {

	JsonStructure obj;
	
	@Context ServletContext servletContext;
	
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getJson() throws FileNotFoundException{
		readData("/test.json");
		return Response.ok(obj, MediaType.APPLICATION_JSON).build();
		//return obj.toString();
		
	}
	
	private void readData(String filepath) throws FileNotFoundException{
		JsonReader read;
		try {			
			read = Json.createReader(servletContext.getResourceAsStream(filepath));
			
			obj = read.read();
		} catch (NullPointerException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
	}
	 
	
}
