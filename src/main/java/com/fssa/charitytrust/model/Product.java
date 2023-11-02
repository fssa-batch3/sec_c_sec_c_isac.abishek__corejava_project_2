package com.fssa.charitytrust.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author IsacDevaAbishek
 *  Model object for representing the Product in the appplication
 */
/**
 * Model object for representing the Product in the application.
 */
public class Product {

	// Member variables representing hospital properties
	private int productId;
	private String productName; 
	private String productDescription;
	private LocalDate productRegisteredDate;
	private String imageUrl;
	private int eventId;

	// hashCode() and equals() methods to support comparison based on productName
	// property
//	@Override
//	public int hashCode() {
//		return Objects.hash(productName);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null || getClass() != obj.getClass())
//			return false;
//		Product other = (Product) obj;
//		return Objects.equals(productName, other.productName);
//	}

	/**
	 * Parameterized constructor to initialize Product object with provided values.
	 *
	 * @param ProductId          The unique identifier for the Product.
	 * @param ProductName        The name of the Product.
	 * @param ProductDescription Product Descrption.
	 * @param imageUrl           The URL representing the Product's image.
	 * @param eventId
	 */
	public Product(String productName, String productDescription, String imageUrl, int eventId) {
		setProductRegisteredDate();

		this.productName = productName;
		this.productDescription = productDescription;
		this.imageUrl = imageUrl;
		this.eventId = eventId;
	}

	/**
	 * Default constructor for the Product class. Sets the Product registration date
	 * to the current date.
	 */
	public Product() {
		setProductRegisteredDate();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public LocalDate getProductRegisteredDate() {
		return productRegisteredDate;
	}

	/**
	 * Sets the Product registration date to the current date.
	 */
	public void setProductRegisteredDate() {
		this.productRegisteredDate = LocalDate.now();
	}

	/**
	 * Sets theProduct registration date based on a given SQL Date representation.
	 *
	 * @param date The SQL Date representing the hospital registration date.
	 */
	public void setProductRegisteredDate(Date date) {
	}

	public void setEventid(int eventId) {
		this.eventId = eventId;
	}

	public int getEventId() {
		return eventId;
	}

	public String toString() {

		return "productName : " + this.getProductName() + " Event Id : " + this.getEventId() + "\n";
	}
}
