package com.sleightholme.jeetut.json;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rs")
public class JsonConfig extends Application {

	// ======================================
	  // =             Attributes             =
	  // ======================================

	  private final Set<Class<?>> classes;

	  // ======================================
	  // =            Constructors            =
	  // ======================================

	  public JsonConfig() {
	    HashSet<Class<?>> c = new HashSet<>();
	    c.add(JsonRS.class);

	    classes = Collections.unmodifiableSet(c);
	  }

	  // ======================================
	  // =          Getters & Setters         =
	  // ======================================

	  @Override
	  public Set<Class<?>> getClasses() {
	    return classes;
	  }
	
}
