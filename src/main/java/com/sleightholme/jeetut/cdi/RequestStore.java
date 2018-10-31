package com.sleightholme.jeetut.cdi;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import org.eclipse.microprofile.opentracing.Traced;

@RequestScoped
@Traced
public class RequestStore extends Store{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PostConstruct
	@Override
	public void setup(){
		super.setup();
		strings.add("This is the request scoped store");
		names.setName("request");
	}
	
}
