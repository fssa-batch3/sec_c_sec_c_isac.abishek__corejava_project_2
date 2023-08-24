package com.fssa.charitytrust.productvalidator;

import java.net.MalformedURLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.productexception.ProductValidateErrors;
import com.fssa.charitytrust.productexception.ValidatorIntializationException;
import com.fssa.charitytrust.productmodel.Product;
import com.fssa.charitytrust.productvalidator.ProductValidator;

 class TestProductValidator {
	Product product = new Product("limbs", "ISI MARK LIMBS", "https://freeimage.host/i/HNRzLYJ.jpg", 2);
	Product testObj = new Product();

	@Test
	void testValidateObject() throws ValidatorIntializationException {

		try {
			Assertions.assertTrue(ProductValidator.validate(product));
		} catch (ValidatorIntializationException e) {
			e.getMessage();
		}
	}

	@Test
	void testNullObject() throws ValidatorIntializationException {
		try {
			ProductValidator.validate(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidName() throws ValidatorIntializationException {
		testObj.setProductName("Artificial legs");
		String proName = testObj.getProductName();
		Assertions.assertTrue(ProductValidator.validateProductName(proName));
	}

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

	@Test
	void testInvalidNameNull() throws ValidatorIntializationException {
		try {
			ProductValidator.validateProductName(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidNameEmpty() throws ValidatorIntializationException {
		try {
			ProductValidator.validateProductName("");
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
		}
	}

	@Test
	void testValidDescription() throws ValidatorIntializationException {
		testObj.setProductDescription("ISI Mark artificial legs");
		String proDes = testObj.getProductDescription();
		Assertions.assertTrue(ProductValidator.validateProductName(proDes));
	}

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

	@Test
	void testInvalidDescriptionNull() throws ValidatorIntializationException {
		try {
			ProductValidator.validateDescription(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidDescriptionEmpty() throws ValidatorIntializationException {
		try {
			ProductValidator.validateDescription("");
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION, e.getMessage());
		}
	}

	@Test
	void testValidURL() throws ValidatorIntializationException, MalformedURLException {
		testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
		String URL = testObj.getImageUrl();
		Assertions.assertTrue(ProductValidator.validateURL(URL));
	}

	@Test
	void testInvalidURL() throws MalformedURLException, ValidatorIntializationException {
		try {
			ProductValidator.validateURL("NOTINTYPE");
		} catch (MalformedURLException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL, e.getMessage());
		}
	}

	@Test
	void testInvalidURLEmpty() throws MalformedURLException {
		try {
			ProductValidator.validateURL(" ");
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidURLNull() throws MalformedURLException {
		try {
			ProductValidator.validateURL(null);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
		}
	}

	@Test
	void testEventId() throws ValidatorIntializationException {
		testObj.setEventid(2);
		int proDes = testObj.getEventId();
		Assertions.assertTrue(ProductValidator.validateEventId(proDes));
	}

	@Test
	void testInvalidEventId() throws ValidatorIntializationException {
		try {
			ProductValidator.validateEventId(0);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}
	}

	@Test
	void testInvalidEventIdLessValue() throws ValidatorIntializationException {
		try {
			ProductValidator.validateEventId(-1);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}
	}

}