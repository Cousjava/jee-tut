package com.sleightholme.jeetut.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Basket {

	@Id @GeneratedValue
	private int id;
	
}
