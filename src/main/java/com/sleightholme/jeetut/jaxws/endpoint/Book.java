package com.sleightholme.jeetut.jaxws.endpoint;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/book")
@WebService
public class Book {

	private static final String SUCCESS = "SUCCESS";
	
	private String ISBN = "";
	private String title = "";
	
	public String getISBN(){
		return ISBN;
	}
	
	@WebMethod(exclude = true)
	public void setISBN(String ISBN){
		this.ISBN = ISBN;
	}
	
        @GET
	public String getTitle(){
		return title;
	}
	
        @POST
	@WebMethod
	public Response setTitle(@PathParam("title") @WebParam(name="title", mode = Mode.IN) String title){
		this.title = title;
		return Response.status(200).build();
	}
	
        @GET
	@WebMethod
	public String sayHello(@PathParam("name") String name){
		return "Hello " + name + "!";
	}
	
}
