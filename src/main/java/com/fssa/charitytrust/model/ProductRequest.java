package com.fssa.charitytrust.model;

import java.sql.Date;
import java.time.LocalDate;

public class ProductRequest {
	private int requestId;  
	
	@Override
	public String toString() {
		return "ProductRequest [eventName=" + eventName + ", productName=" + productName + ", requestDate="
				+ requestDate + ", requestDateSQL=" + requestDateSQL + ", mobileno=" + mobileno + "]";
	}
	private String eventName;
	private String productName;
	private LocalDate requestDate;
	private Date requestDateSQL; // This appears to be a SQL-specific date representation.
	private String mobileno;
	private String isActive;
	public ProductRequest( String eventName, String productName, 
			 String mobileno) {
		this.eventName = eventName;
		this.productName = productName;
		setRequestDate();
		this.mobileno = mobileno;
	}
	
	public ProductRequest(int requestId, String eventName, String productName, LocalDate requestDate,
			 String mobileno, String isActive) {
		super();
		this.requestId = requestId;
		this.eventName = eventName;
		this.productName = productName;
		this.requestDate = requestDate;
		this.mobileno = mobileno;
		this.isActive = isActive;
	}
	public ProductRequest() {
		
	}
	public String isActive() {
		return isActive;
	}
	public void setActive(String isActive) {
		this.isActive = isActive;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate() {
		this.requestDate = LocalDate.now();
	}
	public Date getRequestDateSQL() {
		return requestDateSQL;
	}
	public void setRequestDate(Date requestDateSQL) {
		this.requestDateSQL = requestDateSQL;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	

}
