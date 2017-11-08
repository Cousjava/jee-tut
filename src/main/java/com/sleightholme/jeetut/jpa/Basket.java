package com.sleightholme.jeetut.jpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Basket implements Serializable {

	@Id @GeneratedValue
	private int id;
        
        public int getID(){
            return id;
        }
	
}
