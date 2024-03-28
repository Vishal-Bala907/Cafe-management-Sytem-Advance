package com.dataBases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * @Author vishalbala_here 
 * built in Java using JavaFX for the front-end and Hibernate for the Database
 * This class is used to Create and determine the employee table in database
 * 
 * 
 * */
@Entity
public class EmpDAO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String empID;
	private String post;
	private String salary;
	private String contant;
	private String address;
	private String userName;
	private String passWord;

	public EmpDAO(String name, String empID, String post, String salary, String contant, String address,
			String userName, String passWord) {
		super();
		this.name = name;
		this.empID = empID;
		this.post = post;
		this.salary = salary;
		this.contant = contant;
		this.address = address;
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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

	public String getID() {
		return empID;
	}

	public void setID(String iD) {
		empID = iD;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getContant() {
		return contant;
	}

	public void setContant(String contant) {
		this.contant = contant;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public EmpDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
