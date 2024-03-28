package com.dataBases;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @Author vishalbala_here (<- ig)
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to Create and determine the inventory table in database
 * 
 * 
 * */
@Entity
@Table(name = "cafe_management")
public class InventryDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String price;
	private String item_ID;
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;
	public InventryDAO(int id, String name, String price, String item_ID, byte[] image, int itemsSold) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.item_ID = item_ID;
		this.image = image;
		this.itemsSold = itemsSold;
	}



	private int itemsSold;

	public int getItemsSold() {
		return itemsSold;
	}

	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public String getItem_ID() {
		return item_ID;
	}

	public void setItem_ID(String item_ID) {
		this.item_ID = item_ID;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



	public InventryDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
