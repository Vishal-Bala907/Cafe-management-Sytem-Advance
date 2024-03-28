package com.Inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShowBillItemsOnBill {
	private StringProperty itemName;
	private StringProperty itemPrice;
	private StringProperty itemQuantity;
	/*
	 * @Author vishalbala_here 
	 * built in Java using JavaFX for the front-end and Hibernate for the Database
	 * This class is used to fill the data on the table view control "Bill Field"
	 *  
	 */

	public ShowBillItemsOnBill(String itemName, String itemPrice, String itemQuantity) {
		super();
		this.itemName = new SimpleStringProperty(itemName);
		this.itemPrice = new SimpleStringProperty(itemPrice);
		this.itemQuantity = new SimpleStringProperty(itemQuantity);
	}

	public StringProperty getItemName() {
		return itemName;
	}

	public StringProperty getItemPrice() {
		return itemPrice;
	}

	public StringProperty getItemQuantity() {
		return itemQuantity;
	}

}
