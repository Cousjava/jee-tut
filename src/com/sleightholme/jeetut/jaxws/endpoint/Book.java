package com.sleightholme.jeetut.jaxws.endpoint;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;

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
	
	public String getTitle(){
		return title;
	}
	
	@Oneway
	@WebMethod
	public String setTitle(@WebParam(name="title", mode = Mode.IN) String title){
		this.title = title;
		return SUCCESS;
	}
	
	@WebMethod
	public String sayHello(String name){
		return "Hello " + name + "!";
	}
	
}
