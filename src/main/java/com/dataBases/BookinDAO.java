package com.dataBases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * @Author vishalbala_here 
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to Create and determine the Booking table in database
 * 
 * 
 * */

@Entity
public class BookinDAO {
	
	public BookinDAO() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String numberOfPersons;
	private String cost;
	private String payment;
	private String date;
	private String mob;
	private String totalPrice;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNumberOfPersons() {
		return numberOfPersons;
	}


	public void setNumberOfPersons(String numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}


	public String getCost() {
		return cost;
	}


	public void setCost(String cost) {
		this.cost = cost;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getMob() {
		return mob;
	}


	public void setMob(String mob) {
		this.mob = mob;
	}


	public BookinDAO(String name, String numberOfPersons, String cost, String payment, String date, String mob
			,String totalPrice) {
		super();
		this.name = name;
		this.numberOfPersons = numberOfPersons;
		this.cost = cost;
		this.payment = payment;
		this.date = date;
		this.mob = mob;
		this.totalPrice = totalPrice;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
