package com.fssa.charitytrust.productvalidator;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ValidatorIntializationException;
import com.fssa.charitytrust.model.Product;
import com.fssa.charitytrust.validator.ProductValidator;

 class TestProductValidator {
	Product product = new Product("limbs", "ISI MARK LIMBS", "https://freeimage.host/i/HNRzLYJ.jpg", 2);
	Product testObj = new Product();
	 /**
     * Tests that the validate method returns true when passed a valid Product object.
     * 
     * @throws ValidatorIntializationException if the validator cannot be initialized.
     */
	@Test 
	void testValidateObject() throws ValidatorIntializationException {

		try {
			Assertions.assertTrue(ProductValidator.validate(product));
		} catch (ValidatorIntializationException e) {
			e.getMessage();
		}
	}
	  /**
     * Tests that the validate method throws a ValidatorIntializationException when passed a null Product object.
     * 
     * @throws ValidatorIntializationException if the validator cannot be initialized.
     */
	@Test
	void testNullObject() throws ValidatorIntializationException {
		try {
			ProductValidator.validate(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}
	   /**
     * Tests that the validateProductName method returns true when passed a valid product name.
     * 
     * @throws ValidatorIntializationException if the validator cannot be initialized.
     */
	@Test
	void testValidName() throws ValidatorIntializationException {
		testObj.setProductName("Artificial legs");
		String proName = testObj.getProductName();
		Assertions.assertTrue(ProductValidator.validateProductName(proName));
	}
	 /**
	   * Test that an invalid product name throws an exception.
	   *
	   * @throws ValidatorIntializationException if the product name is invalid
	   */
	@Test
	void testInvalidName() throws ValidatorIntializationException {
		testObj.setProductName("Gem123");
		String proName = testObj.getProductName();
		try {
			ProductValidator.validateProductName(proName);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
		}
	}
	  /**
	   * Test that a null product name throws an exception.
	   *
	   * @throws ValidatorIntializationException if the product name is null
	   */
	@Test
	void testInvalidNameNull() throws ValidatorIntializationException {
		try {
			ProductValidator.validateProductName(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}
	 /**
	   * Test that an empty product name throws an exception.
	   *
	   * @throws ValidatorIntializationException if the product name is empty
	   */
	@Test
	void testInvalidNameEmpty() throws ValidatorIntializationException {
		try {
			ProductValidator.validateProductName("");
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
		}
	}
	/**
	   * Test that a valid product description is accepted.
	   *
	   * @throws ValidatorIntializationException if the validator cannot be initialized.
	   */
	@Test
	void testValidDescription() throws ValidatorIntializationException {
		testObj.setProductDescription("ISI Mark artificial legs");
		String proDes = testObj.getProductDescription();
		Assertions.assertTrue(ProductValidator.validateProductName(proDes));
	}
	/**
	   * Test that an invalid product description is rejected.
	   *
	   * @throws ValidatorIntializationException if the validator cannot be initialized.
	   */
	@Test
	void testInvalidDescription() throws ValidatorIntializationException {
		testObj.setProductDescription("ISI Mark artificial legs213242");
		String proDes = testObj.getProductName();
		try {
			ProductValidator.validateDescription(proDes);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
		}
	}
	  /**
	   * Test that a null product description is rejected.
	   *
	   * @throws ValidatorIntializationException if the validator cannot be initialized.
	   */
	@Test
	void testInvalidDescriptionNull() throws ValidatorIntializationException {
		try {
			ProductValidator.validateDescription(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
		}
	}
	  /**
	   * Test that an empty description throws an exception.
	   */
	@Test
	void testInvalidDescriptionEmpty() throws ValidatorIntializationException {
		try {
			ProductValidator.validateDescription("");
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION, e.getMessage());
		}
	}
	  /**
	   * Test that a valid URL does not throw an exception.
	   */
	@Test
	void testValidURL() throws ValidatorIntializationException, MalformedURLException {
		testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
		String URL = testObj.getImageUrl();
		Assertions.assertTrue(ProductValidator.validateURL(URL));
	}
	  /**
	   * Test that an invalid URL throws an exception.
	   */
	@Test
	void testInvalidURL() throws MalformedURLException, ValidatorIntializationException {
		try {
			ProductValidator.validateURL("NOTINTYPE");
		} catch (MalformedURLException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL, e.getMessage());
		}
	}
	 /**
	   * Test that an empty URL throws an exception.
	   */
	@Test
	void testInvalidURLEmpty() throws MalformedURLException {
		try {
			ProductValidator.validateURL(" ");
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
		}
	}
	/**
	 * Test that passing a null URL to the validateURL method throws a ValidatorIntializationException
	 * with the message INVALID_URL_NULL.
	 *
	 * @throws MalformedURLException if the URL is not valid
	 */
	@Test
	void testInvalidURLNull() throws MalformedURLException {
		try {
			ProductValidator.validateURL(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
		}
	}
	/**
	 * Validates the event id.
	 *
	 * @param eventId the event id to validate
	 * @throws ValidatorIntializationException if the event id is invalid
	 */
	@Test
	void testEventId() throws ValidatorIntializationException {
		testObj.setEventid(2);
		int proDes = testObj.getEventId();
		Assertions.assertTrue(ProductValidator.validateEventId(proDes));
	}
	/**
	 * Test that an invalid event ID is caught and the correct error message is returned.
	 *
	 * @throws ValidatorIntializationException if the validator is not initialized properly
	 */
	@Test
	void testInvalidEventId() throws ValidatorIntializationException {
		try {
			ProductValidator.validateEventId(0);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}
	}
	/**
	 * Test that an invalid event ID with a value less than 0 throws a {@link ValidatorIntializationException}.
	 *
	 * @throws ValidatorIntializationException if the event ID is invalid
	 */
	@Test
	void testInvalidEventIdLessValue() throws ValidatorIntializationException {
		try {
			ProductValidator.validateEventId(-1);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}
	}

}
