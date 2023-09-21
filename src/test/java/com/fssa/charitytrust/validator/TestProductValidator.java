package com.fssa.charitytrust.validator;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Product;
import com.fssa.charitytrust.validator.ProductValidator;

 class TestProductValidator {
	Product product = new Product("limbs", "ISI MARK LIMBS", "https://freeimage.host/i/HNRzLYJ.jpg", 2);
	Product testObj = new Product();
	 /**
     * Tests that the validate method returns true when passed a valid Product object.
     * 
     * @throws ValidatorInitializationException if the validator cannot be initialized.
     */
	@Test 
	void testValidateObject() throws ValidatorInitializationException {

		try {
			Assertions.assertTrue(ProductValidator.validate(product));
		} catch (ValidatorInitializationException e) {
			e.getMessage();
		}
	}
	  /**
     * Tests that the validate method throws a ValidatorInitializationException when passed a null Product object.
     * 
     * @throws ValidatorInitializationException if the validator cannot be initialized.
     */
	@Test
	void testNullObject() throws ValidatorInitializationException {
		try {
			ProductValidator.validate(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}
	   /**
     * Tests that the validateProductName method returns true when passed a valid product name.
     * 
     * @throws ValidatorInitializationException if the validator cannot be initialized.
     */
	@Test
	void testValidName() throws ValidatorInitializationException {
		testObj.setProductName("Artificial legs");
		String proName = testObj.getProductName();
		Assertions.assertTrue(ProductValidator.validateProductName(proName));
	}
	 /**
	   * Test that an invalid product name throws an exception.
	   *
	   * @throws ValidatorInitializationException if the product name is invalid
	   */
	@Test
	void testInvalidName() throws ValidatorInitializationException {
		testObj.setProductName("Gem123");
		String proName = testObj.getProductName();
		try {
			ProductValidator.validateProductName(proName);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
		}
	}
	  /**
	   * Test that a null product name throws an exception.
	   *
	   * @throws ValidatorInitializationException if the product name is null
	   */
	@Test
	void testInvalidNameNull() throws ValidatorInitializationException {
		try {
			ProductValidator.validateProductName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}
	 /**
	   * Test that an empty product name throws an exception.
	   *
	   * @throws ValidatorInitializationException if the product name is empty
	   */
	@Test
	void testInvalidNameEmpty() throws ValidatorInitializationException {
		try {
			ProductValidator.validateProductName("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
		}
	}
	/**
	   * Test that a valid product description is accepted.
	   *
	   * @throws ValidatorInitializationException if the validator cannot be initialized.
	   */
	@Test
	void testValidDescription() throws ValidatorInitializationException {
		testObj.setProductDescription("ISI Mark artificial legs");
		String proDes = testObj.getProductDescription();
		Assertions.assertTrue(ProductValidator.validateProductName(proDes));
	}
	/**
	   * Test that an invalid product description is rejected.
	   *
	   * @throws ValidatorInitializationException if the validator cannot be initialized.
	   */
	@Test
	void testInvalidDescription() throws ValidatorInitializationException {
		testObj.setProductDescription("ISI Mark artificial legs213242");
		String proDes = testObj.getProductName();
		try {
			ProductValidator.validateDescription(proDes);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
		}
	}
	  /**
	   * Test that a null product description is rejected.
	   *
	   * @throws ValidatorInitializationException if the validator cannot be initialized.
	   */
	@Test
	void testInvalidDescriptionNull() throws ValidatorInitializationException {
		try {
			ProductValidator.validateDescription(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
		}
	}
	  /**
	   * Test that an empty description throws an exception.
	   */
	@Test
	void testInvalidDescriptionEmpty() throws ValidatorInitializationException {
		try {
			ProductValidator.validateDescription("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION, e.getMessage());
		}
	}
	  /**
	   * Test that a valid URL does not throw an exception.
	   */
	@Test
	void testValidURL() throws ValidatorInitializationException, MalformedURLException {
		testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
		String URL = testObj.getImageUrl();
		Assertions.assertTrue(ProductValidator.validateURL(URL));
	}
	  /**
	   * Test that an invalid URL throws an exception.
	   */
	@Test
	void testInvalidURL() throws MalformedURLException, ValidatorInitializationException {
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
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
		}
	}
	/**
	 * Test that passing a null URL to the validateURL method throws a ValidatorInitializationException
	 * with the message INVALID_URL_NULL.
	 *
	 * @throws MalformedURLException if the URL is not valid
	 */
	@Test
	void testInvalidURLNull() throws MalformedURLException {
		try {
			ProductValidator.validateURL(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
		}
	}
	/**
	 * Validates the event id.
	 *
	 * @param eventId the event id to validate
	 * @throws ValidatorInitializationException if the event id is invalid
	 */
	@Test
	void testEventId() throws ValidatorInitializationException {
		testObj.setEventid(2);
		int proDes = testObj.getEventId();
		Assertions.assertTrue(ProductValidator.validateEventId(proDes));
	}
	/**
	 * Test that an invalid event ID is caught and the correct error message is returned.
	 *
	 * @throws ValidatorInitializationException if the validator is not initialized properly
	 */
	@Test
	void testInvalidEventId() throws ValidatorInitializationException {
		try {
			ProductValidator.validateEventId(0);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}
	}
	/**
	 * Test that an invalid event ID with a value less than 0 throws a {@link ValidatorInitializationException}.
	 *
	 * @throws ValidatorInitializationException if the event ID is invalid
	 */
	@Test
	void testInvalidEventIdLessValue() throws ValidatorInitializationException {
		try {
			ProductValidator.validateEventId(-1);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}
	}

}
