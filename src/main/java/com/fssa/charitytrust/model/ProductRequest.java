package com.fssa.charitytrust.model;

import java.sql.Date;
import java.time.LocalDate;

public class ProductRequest {
	private int requestId;  
	
	private String eventName;
	private String productName;
	private LocalDate requestDate;
	private Date requestDateSQL; // This appears to be a SQL-specific date representation.
	private long mobileno;
	private boolean isActive;
	public ProductRequest( String eventName, String productName, 
			 long mobileno) {
		this.eventName = eventName;
		this.productName = productName;
		setRequestDate();
		this.mobileno = mobileno;
	}
	
	public ProductRequest(int requestId, String eventName, String productName, LocalDate requestDate,
			 long mobileno, boolean isActive) {
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
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
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	

}
