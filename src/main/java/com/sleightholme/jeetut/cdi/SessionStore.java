package com.sleightholme.jeetut.cdi;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class SessionStore extends Store {

	private static final long serialVersionUID = 10L;
	
	@PostConstruct
	@Override
	public void setup(){
		super.setup();
		strings.add("This is the session store");
		names.setName("session");
		
	}
	
	
}
