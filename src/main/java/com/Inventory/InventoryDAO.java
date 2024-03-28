package com.Inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * @Author vishalbala_here 
 * 9079018358
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to fill the data on the table view control "Inventory Field"
 *  
 */
public class InventoryDAO {
	private StringProperty id;
	private StringProperty name;
	private StringProperty price;
	private StringProperty item_ID;
	private StringProperty itemsSold;



	public InventoryDAO(String id, String name, String price, 
			String item_ID, String itemsSold) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleStringProperty(price);
		this.item_ID = new SimpleStringProperty( item_ID);
		this.itemsSold = new SimpleStringProperty(itemsSold);
		
	}

	public StringProperty getId() {
		return id;
	}

	public StringProperty getName() {
		return name;
	}

	public StringProperty getPrice() {
		return price;
	}


	public StringProperty getItem_ID() {
		return item_ID;
	}

	public StringProperty getItemsSold() {
		return itemsSold;
	}

}
