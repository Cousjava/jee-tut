package com.sleightholme.jeetut.cdi;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationStore extends Store{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	@Override
	public void setup(){
		super.setup();
		strings.add("This is the application store");
		names.setName("application");
	}
	
}