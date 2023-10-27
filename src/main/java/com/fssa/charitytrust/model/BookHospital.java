package com.fssa.charitytrust.model;

import java.sql.Date;
import java.time.LocalDate;

public class BookHospital {
	private int id;
	
	private String username;
	private String email;
	private String hospitalname;
	private String contactNumber;
	private LocalDate bookdate;
//	private Data bookDataSql;
	public BookHospital(){
		
	}

	public BookHospital(int id,String username, String email, String hospitalname, String contactNumber, LocalDate bookdate) {
		super();
		this.id=id;
		this.username = username;
		this.email = email;
		this.hospitalname = hospitalname;
		this.contactNumber = contactNumber;
		this.bookdate = bookdate;
	}
	public BookHospital(String username, String email, String hospitalname, String contactNumber, LocalDate bookdate) {
		super();
		this.username = username;
		this.email = email;
		this.hospitalname = hospitalname;
		this.contactNumber = contactNumber;
		this.bookdate = bookdate;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getBookdate() {
		return bookdate;
	}
	public void setBookdate(LocalDate date) {
		this.bookdate = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
}
