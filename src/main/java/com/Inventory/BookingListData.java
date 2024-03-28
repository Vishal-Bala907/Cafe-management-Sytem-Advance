package com.Inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * @Author vishalbala_here 
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to fill the data on the table view control "Booking Field"
 *  
 */
public class BookingListData {
	private StringProperty id;
	private StringProperty name;
	private StringProperty numberOfPersons;
	private StringProperty cost;
	private StringProperty payment;
	private StringProperty date;
	private StringProperty mob;
	private StringProperty total;
	public BookingListData(String id, String name, String numberOfPersons, String cost, String payment, String date,
			String mob,String totalPrice) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.numberOfPersons = new SimpleStringProperty(numberOfPersons);
		this.cost = new SimpleStringProperty(cost);
		this.payment = new SimpleStringProperty(payment);
		this.date = new SimpleStringProperty(date);
		this.mob = new SimpleStringProperty(mob);
		this.total = new SimpleStringProperty(totalPrice);
	}
	public StringProperty getTotal() {
		return total;
	}
	public void setTotal(StringProperty total) {
		this.total = total;
	}
	public StringProperty getId() {
		return id;
	}
	public void setId(StringProperty id) {
		this.id = id;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getNumberOfPersons() {
		return numberOfPersons;
	}
	public void setNumberOfPersons(StringProperty numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}
	public StringProperty getCost() {
		return cost;
	}
	public void setCost(StringProperty cost) {
		this.cost = cost;
	}
	public StringProperty getPayment() {
		return payment;
	}
	public void setPayment(StringProperty payment) {
		this.payment = payment;
	}
	public StringProperty getDate() {
		return date;
	}
	public void setDate(StringProperty date) {
		this.date = date;
	}
	public StringProperty getMob() {
		return mob;
	}
	public void setMob(StringProperty mob) {
		this.mob = mob;
	}
	
	
	
}
