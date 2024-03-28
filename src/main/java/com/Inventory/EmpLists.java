package com.Inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * @Author vishalbala_here 
 * 9079018358
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to fill the data on the table view control "Employee Field"
 *  
 */
public class EmpLists {
	private StringProperty sn;

	private StringProperty name;

	private StringProperty ID;

	private StringProperty post;

	private StringProperty salary;

	private StringProperty contact;
	
	private StringProperty address;

	public StringProperty getSn() {
		return sn;
	}

	public StringProperty getName() {
		return name;
	}

	public StringProperty getID() {
		return ID;
	}

	public StringProperty getPost() {
		return post;
	}

	public StringProperty getSalary() {
		return salary;
	}

	public StringProperty getContact() {
		return contact;
	}

	public StringProperty getAddress() {
		return address;
	}

	public EmpLists(String sn, String name, String iD, String post,
			String salary, String contact, String address) {
		super();
		this.sn = new SimpleStringProperty(sn);
		this.name = new SimpleStringProperty(name);
		ID = new SimpleStringProperty(iD);
		this.post = new SimpleStringProperty(post);
		this.salary = new SimpleStringProperty(salary);
		this.contact = new SimpleStringProperty(contact);
		this.address = new SimpleStringProperty(address);
	}
	

	

}
