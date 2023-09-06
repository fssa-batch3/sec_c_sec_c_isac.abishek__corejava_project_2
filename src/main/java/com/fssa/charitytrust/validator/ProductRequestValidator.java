package com.fssa.charitytrust.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.exceptions.ValidatorIntializationException;
import com.fssa.charitytrust.model.ProductRequest;

public class ProductRequestValidator {
	/**
	 * Validates the complete product request object to ensure all properties meet the
	 * required criteria.
	 *
	 * @param event The ProductRequest object to be validated.
	 * @return true if the productrequest passes all validation checks, false otherwise.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator or other
	 *                                          validation errors occur.
	 */
	
	
	public boolean validate(ProductRequest request) throws ValidatorInitializationException {

		try { 
			validateEventName(request.getEventName());
			validateProductName(request.getProductName());

			validateContactNo(request.getMobileno());
			
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Validates the event name to ensure it contains only alphabetic characters or
	 * spaces.
	 *
	 * @param eventName The name of the event to be validated.
	 * @return true if the event name is valid, false otherwise.
	 * @throws ValidatorInitializationException if the event name is null or does
	 *                                          not meet the required format.
	 */
	public static boolean validateEventName(String eventName) throws ValidatorInitializationException {
		if (eventName == null || eventName.isEmpty()) {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_NULL);
		}
		String regex = "[a-zA-Z ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(eventName);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_NAME);
		}
	}
	
	/**
	 * Validates the Product name to ensure it contains only alphabetic characters
	 * or spaces.
	 *
	 * @param ProductName The name of the Product to be validated.
	 * @return true if the Product name is valid, false otherwise.
	 * @throws ValidatorInitializationException if the Product name is null or does
	 *                                          not meet the required format.
	 */
	public static boolean validateProductName(String productName) throws ValidatorIntializationException {
		if (productName == null) {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_PRODUCT_NULL);
		}
		String regex = "[a-zA-Z ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(productName);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			
			return true;
		} else {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_PRODUCT_NAME);
		}
	}
	
	/**
	 * Validates the contact number to ensure it follows a specific pattern for
	 * Indian phone numbers.
	 *
	 * @param contactNo The contact number to be validated.
	 * @return true if the contact number is valid, false otherwise.
	 * @throws ValidatorInitializationException if the contact number is null or
	 *                                          does not meet the required format.
	 */
	public boolean validateContactNo(long contactNo) throws ValidatorInitializationException {
		
		String regex = "^(\\+91|91)?[6789]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
		String contactNos=Long.toString(contactNo);
		Matcher matcher = pattern.matcher(contactNos);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_CONTACT_NUMBER);
		}
	}
	
	/**
	 * Validates the bolean value to ensure it follows a specific pattern true or false
	 *
	 * @param isactive The value to be validated.
	 * @return true if the contact number is contain true bollean value, false otherwise.
	 * @throws ValidatorInitializationException if the boolean is is null or
	 *                                          does not meet the required format.
	 */
	public boolean validateIsActive(boolean isactive) throws ValidatorInitializationException {
		boolean trueval= true;
		if (isactive==trueval) {
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_BOOLEAN);
		}
	}
}
