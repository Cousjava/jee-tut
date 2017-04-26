package com.sleightholme.jeetut.jpa;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id @GeneratedValue
	private int ID;
	private String username;
	private String email;
	@OneToOne (fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "basket_fk")
	private Basket basket;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRegistered;
	
	public User(){
		
	}
	
	public User(String username, String email){
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
	
}
