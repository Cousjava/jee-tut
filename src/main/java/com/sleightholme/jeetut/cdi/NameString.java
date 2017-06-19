package com.sleightholme.jeetut.cdi;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent
public class NameString implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
