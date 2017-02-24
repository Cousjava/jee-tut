package com.sleightholme.jeetut.chapter1;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestStore extends Store{

	@PostConstruct
	@Override
	public void setup(){
		super.setup();
		strings.add("This is the request scoped store");
		names.setName("request");
	}
	
}
