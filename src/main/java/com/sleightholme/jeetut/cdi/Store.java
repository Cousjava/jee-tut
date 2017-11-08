package com.sleightholme.jeetut.cdi;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class Store implements Serializable{

	private static final long serialVersionUID = -3006823997028319420L;
	protected ArrayList<String> strings;
	protected String name = "";

	@Inject
	protected NameString names;
	
	public ArrayList<String> getStrings() {
		return strings;
	}

	public void setStrings(ArrayList<String> strings) {
		this.strings = strings;
	}
	
	public void addData(String data){
		strings.add(data);
	}
	
	@PostConstruct
	public void setup(){
		strings = new ArrayList<String>();
		strings.add("There is some data here!");
		names = new NameString();
	}
	
	public String getName(){
		return names.getName();
	}
	
}
