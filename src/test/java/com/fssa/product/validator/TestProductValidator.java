package com.fssa.product.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.event.exceptions.EventValidatorErrors;
import com.fssa.event.exceptions.ValidatorInitializationException;
import com.fssa.event.validator.EventValidator;
import com.fssa.product.exception.ProductValidateErrors;
import com.fssa.product.exception.ValidatorIntializationException;
import com.fssa.product.model.Product;

public class TestProductValidator {
	Product product = new Product( "limbs", "ISI MARK LIMBS","https://freeimage.host/i/HNRzLYJ.jpg",2);
	Product testObj = new Product();
	  @Test
	    public void testProductValidatorObject() {

	        try {
	            ProductValidator obj = new ProductValidator();
	        } catch (Exception e) {
	            e.getMessage();
	        }
	    }
	  
	  @Test
	    public void testValidateObject() throws ValidatorIntializationException {

	        try {
	            Assertions.assertTrue(ProductValidator.validate(product));
	        } catch (ValidatorIntializationException e) {
	            e.getMessage();
	        }
	    }
	  
	  @Test
	    public void testNullObject() throws ValidatorIntializationException {
	        try {
	            ProductValidator.validate(null);
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
	        }
	    }
	  
	  @Test
	    public void testValidName() throws  ValidatorIntializationException {
	        testObj.setProductName("Artificial legs");
	        String proName = testObj.getProductName();
	        Assertions.assertTrue(ProductValidator.validateProductName(proName));
	    }

	    @Test
	    public void testInvalidName() throws ValidatorIntializationException {
	        testObj.setProductName("Gem123");
	        String proName = testObj.getProductName();
	        try {
	        	ProductValidator.validateProductName(proName);
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidNameNull() throws ValidatorIntializationException {
	        try {
	            ProductValidator.validateProductName(null);
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidNameEmpty() throws ValidatorIntializationException {
	        try {
	            ProductValidator.validateProductName("");
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NAME, e.getMessage());
	        }
	    }
	    
	    @Test
	    public void testValidDescription() throws  ValidatorIntializationException {
	        testObj.setProductDescription("ISI Mark artificial legs");
	        String proDes = testObj.getProductDescription();
	        Assertions.assertTrue(ProductValidator.validateProductName(proDes));
	    }

	    @Test
	    public void testInvalidDescription() throws ValidatorIntializationException {
	        testObj.setProductDescription("ISI Mark artificial legs213242");
	        String proDes = testObj.getProductName();
	        try {
	        	ProductValidator.validateDescription(proDes);
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidDescriptionNull() throws ValidatorIntializationException {
	        try {
	            ProductValidator.validateDescription(null);
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL, e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidDescriptionEmpty() throws ValidatorIntializationException {
	        try {
	            ProductValidator.validateDescription("");
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION, e.getMessage());
	        }
	    }
	    
	    @Test
	    public void testValidURL() throws ValidatorIntializationException {
	        testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
	        String URL = testObj.getImageUrl();
	        Assertions.assertTrue(ProductValidator.validateURL(URL));
	    }

	    @Test
	    public void testInvalidURL() {
	        try {
	        	ProductValidator.validateURL("NOTINTYPE");
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_URL, e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidURLEmpty() {
	        try {
	        	ProductValidator.validateURL(" ");
	        } catch (ValidatorIntializationException e) {
	            Assertions.assertEquals(ProductValidateErrors.INVALID_URL_NULL, e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidURLNull() {
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
	    }
	    catch(ValidatorIntializationException e) {
	    	Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
	    }
	    }
	    
	    @Test
	    void testInvalidEventIdLessValue() throws ValidatorIntializationException {
	    try {
	    	ProductValidator.validateEventId(-1);
	    }
	    catch(ValidatorIntializationException e) {
	    	Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
	    }
	    }
 
}