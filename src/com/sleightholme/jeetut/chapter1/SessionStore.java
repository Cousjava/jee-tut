package com.sleightholme.jeetut.chapter1;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class SessionStore extends Store{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	@Override
	public void setup(){
		super.setup();
		strings.add("This is the session store");
		name = "session";
		
	}
	
	
}
