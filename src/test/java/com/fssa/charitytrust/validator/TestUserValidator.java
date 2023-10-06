package com.fssa.charitytrust.validator;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.UserValidatorError;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;

public class TestUserValidator {
	User user = new User("Isac", "Isac@gmail.com", "Isac@123","Noerth Chennai,royapuram","7305836758","1234 5678 9012", UserRole.ADMIN);
	public static User getValidUser() {
 
		User user = new User("Isac", "Isac@gmail.com", "Isac@123","Noerth Chennai,royapuram","7305836758","1234 5678 9012", UserRole.ADMIN);
 
		return user;
	}
 
	public static User getInvalidUser() {
 
		User user = new User("Go*#&*aj", "gokulgmail", "dfuffy123","Noerth Chennai,royapuram","0000836758","000000006758", null);
 
		return user;
	}
 
	@Test
	void testValidateName() {
 
		try {
			Assertions.assertTrue(UserValidator.validateName(getValidUser().getUsername()));
		} catch (IllegalArgumentException | ValidatorInitializationException e) {
 
			e.printStackTrace();
		}
	}
 
	@Test
	void testNullValidateName() {
		try {
			UserValidator.validateName(null);
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USERNAME_NULL, e.getMessage());
		}
	}
 
	@Test
	void testInvalidName() {
		try {
			UserValidator.validateName(getInvalidUser().getUsername());
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USERNAME_PATTERN, e.getMessage());
		}
	}
 
	@Test
	void testValidateEmail() {
 
		try {
			Assertions.assertTrue(UserValidator.validateEmail(getValidUser().getEmail()));
		} catch (IllegalArgumentException | ValidatorInitializationException e) {
 
			e.printStackTrace();
		}
	}
 
	@Test
	void testNullValidateEmail() {
		try {
			UserValidator.validateEmail(null);
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USEREMAIL_NULL, e.getMessage());
		}
	}
 
	@Test
	void testInvalidEmail() {
		try {
			UserValidator.validateEmail(getInvalidUser().getEmail());
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USEREMAIL_PATTERN, e.getMessage());
		}
	}
 
	@Test
	void testValidatePassword() {
 
		try {
			Assertions.assertTrue(UserValidator.validatePassword(getValidUser().getPassword()));
		} catch (IllegalArgumentException | ValidatorInitializationException e) {
 
			e.printStackTrace();
		}
	}
 
	@Test
	void testNullValidatePassword() {
		try {
			UserValidator.validatePassword(null);
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_PASSWORD_NULL, e.getMessage());
		}
	}
 
	@Test
	void testInvalidPassword() {
		try {
			UserValidator.validatePassword(getInvalidUser().getPassword());
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_PASSWORD_PATTERN, e.getMessage());
		}
	}
 
	@Test
	void testValidateUserRole() {
 
		try {
			Assertions.assertTrue(UserValidator.validateUserRole(getValidUser().getRole()));
		} catch (IllegalArgumentException | ValidatorInitializationException e) {
 
			e.printStackTrace();
		}
	}
 
	@Test
	void testNullValidateUserRole() {
		try {
			UserValidator.validateUserRole(null);
			Assertions.fail("Test case failed");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USERROLE_NULL, e.getMessage());
		}
	}
 
	
	
	/**
     * Tests validation of a contact no.
     */
	@Test
	void testValidContactNo() throws ValidatorInitializationException {
		user.setContactNumber("919876543210");
		String contactNumber = user.getContactNumber();
		Assertions.assertTrue(UserValidator.validateContactNo(contactNumber));
	}
	/**
     * Tests validation of a invalid contact no.
     */
	@Test
	void testInvalidContactNo() {
		try {
			UserValidator.validateContactNo("1239751328805");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_CONTACT_NUMBER, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact null.
     */
	@Test
	void testInvalidContactNoNull() {
		try {
			UserValidator.validateContactNo(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_CONTACT_NO_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact no empty.
     */
	@Test
	void testInvalidContactEmpty() {
		try {
			UserValidator.validateContactNo(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_CONTACT_NUMBER, e.getMessage());
		}
	}
	/**
     * Tests validation of a address.
     */
	@Test
	void testValidAddress() throws ValidatorInitializationException {
		user.setAddress("North Street, Taramani");
		String address = user.getAddress();
		Assertions.assertTrue(UserValidator.validateLocation(address));
	}
	/**
     * Tests validation of a invalid address.
     */
	@Test
	void testInvalidAddress() {
		try {
			UserValidator.validateLocation("Too$many%$special@characters!");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_LOCATION, e.getMessage());
		}
	}
	/**
     * Tests validation of a address null.
     */
	@Test
	void testInvalidAddressNull() {
		try {
			UserValidator.validateLocation(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_LOCATION_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a address empty.
     */
	@Test
	void testInvalidAddressEmpty() {
		try {
			UserValidator.validateLocation("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_LOCATION_NULL, e.getMessage());
		}
	}
	
	/**
     * Tests validation of a contact no.
     */
	@Test
	void testValidAadhaarNo() throws ValidatorInitializationException {
		user.setAadhaarNumber("234567890123");
		String aadhaarNumber = user.getAadhaarNumber();
		Assertions.assertTrue(UserValidator.validateAddhaarNo(aadhaarNumber));
	}
	/**
     * Tests validation of a invalid contact no.
     */
	@Test
	void testInvalidAadhaarNo() {
		try {
			UserValidator.validateAddhaarNo("8123456789012");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_AADHAAR_NUMBER, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact null.
     */
	@Test
	void testInvalidAadhaarNoNull() {
		try {
			UserValidator.validateAddhaarNo(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_AADHAAR_NO_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact no empty.
     */
	@Test
	void testInvalidAadhaarEmpty() {
		try {
			UserValidator.validateAddhaarNo(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_AADHAAR_NUMBER, e.getMessage());
		}
	}
 
}
