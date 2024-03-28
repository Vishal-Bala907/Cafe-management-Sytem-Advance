package com.dataBases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billing")
public class DataManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int billId;
	private String name;
	private String price;
	private String quantity;
	private String date;
	private String income;
	public DataManagement() {
		super(); 
		// TODO Auto-generated constructor stub
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public DataManagement( String name, String price, String quantity, String date, String income) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
		this.income = income;
	}
	@Override
	public String toString() {
		return "DataManagement [billId=" + billId + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", date=" + date + ", income=" + income + "]";
	}

	
	
}
