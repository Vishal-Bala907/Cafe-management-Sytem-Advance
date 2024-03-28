package com.Inventory;

public class PrintBillItems {
	private  String itemName;
	private  String itemPrice;
	private  String itemQuantity;

	/*
	 * @Author vishalbala_here 
	 * 9079018358
	 * built in Java using JavaFX for the front-end and Hibernate for the Database
	 * This class is used to fill the data on the table view control "Bill Field"
	 *  
	 */
	public PrintBillItems() {
		super();
	}

	public  String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public  String getItemPrice() {
		return itemPrice;
	}

	public  void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public  String getItemQuantity() {
		return itemQuantity;
	}

	public  void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

}
