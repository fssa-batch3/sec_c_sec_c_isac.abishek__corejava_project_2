package com.fssa.charitytrust.model;


 
public class User {
 
	private int id;
	private String username;
	private String email;
	private String password;
	private String Address;
	private String contactNumber;
	private UserRole role;
	private boolean accessblity;


	public boolean isAccessblity() {
		return accessblity;
	}

	public void setAccessblity(boolean accessblity) {
		this.accessblity = accessblity;
	}

	
 
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}




 
	public User(int id, String username, String email, String password, String address, String contactNumber,
			 UserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.Address = address;
		this.contactNumber = contactNumber;
		this.role = role;
	}
	public User( String username, String email, String password, String address, String contactNumber,
			UserRole role) {
		super();
		
		this.username = username;
		this.email = email;
		this.password = password;
		Address = address;
		this.contactNumber = contactNumber;
		this.role = role;
	}
	
	public User() {
	}
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
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
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}


}
