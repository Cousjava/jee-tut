package com.sleightholme.jeetut.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id @GeneratedValue
	private int ID;
	private String username;
	private String email;
	@OneToOne (fetch = FetchType.LAZY, orphanRemoval = true, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "basket_fk")
	private Basket basket;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRegistered;
        
        Runnable r = (Runnable & Serializable)() -> System.out.println("Serializable!");
	
	public User(){
		dateRegistered = new Date();
	}
	
	public User(String username, String email){
            this();
            this.username = username;
            this.email = email;
		
	}
	
	
	public Basket getBasket() {
		return basket;
	}

	public int getID() {
		return ID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
        
        public void setRegistrationDate(Date date){
            this.dateRegistered = date;
        }
	
	public Date getRegistrationDate(){
		return dateRegistered;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
        
        public Runnable getR(){
            return r;
        }
        
        public void setR(Runnable r){
            this.r = r;
        }
	
}
