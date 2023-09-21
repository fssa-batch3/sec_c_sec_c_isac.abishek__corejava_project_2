package com.fssa.charitytrust.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;

public class TestProductRequestServiceLayer {
	ProductRequestValidator productRequestValidator =new ProductRequestValidator();
	
	
	@Test
	void TestInvalidRequestEventName() {
		Assertions.assertThrows(ValidatorInitializationException.class,()-> ProductRequestValidator.validateEventName("1234567"));
	}
	@Test
	void TestInvalidRequestEventNameNull() {
		Assertions.assertThrows(ValidatorInitializationException.class,()-> ProductRequestValidator.validateEventName(null));
	}
	@Test
	void TestInvalidRequestProductName() {
		Assertions.assertThrows(ValidatorInitializationException.class,()-> ProductRequestValidator.validateProductName("1234567"));
	}
	@Test
	void TestInvalidRequestProductNameNull() {
		Assertions.assertThrows(ValidatorInitializationException.class,()-> ProductRequestValidator.validateProductName(null));
	}
	@Test
	void TestContactInvalid() {
		Assertions.assertThrows(ValidatorInitializationException.class,()-> productRequestValidator.validateContactNo(123456789l));
	}
	@Test
	void TestBooleanIsActive() {
		Assertions.assertDoesNotThrow(()-> productRequestValidator.validateIsActive("Pending"));
	}
}
