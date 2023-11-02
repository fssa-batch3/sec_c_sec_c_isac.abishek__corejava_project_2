package com.fssa.charitytrust.validator;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;

import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Product;

public class ProductValidator {
	
	 public ProductValidator() {
 }
   public static ProductValidator getProductValidator() {
	   return new ProductValidator();
   }
	/**
	 * Validates the complete Product object to ensure all properties meet the
	 * required criteria.
	 *
	 * @param Product The Product object to be validated.
	 * @return true if the Product passes all validation checks, false otherwise.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator or other
	 *                                          validation errors occur.
	 */
	public static boolean validate(Product product) throws ValidatorInitializationException {
		if (product == null) {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_PRODUCT_NULL);
		}
		try {
			validateProductName(product.getProductName());
			validateEventId(product.getEventId());
			validateDescription(product.getProductDescription());
			validateURL(product.getImageUrl());
			return true;
		} catch (Exception e) {
			return false;
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
	public static boolean validateProductName(String productName) throws ValidatorInitializationException {
		if (productName == null) {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_PRODUCT_NULL);
		}
		String regex = "[a-zA-Z ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(productName);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_PRODUCT_NAME);
		}
	}

	/**
	 * Validates the ProductDescription to ensure it contains only alphanumeric
	 * characters, spaces, and certain special characters.
	 *
	 * @param ProductDescription The ProductDescription to be validated.
	 * @return true if the ProductDescription is valid, false otherwise.
	 * @throws ValidatorInitializationException if the ProductDescription is null or
	 *                                          does not meet the required format.
	 */
	public static boolean validateDescription(String productDescription) throws ValidatorInitializationException {
		if (productDescription == null) {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL);
		}
		String regex = "^[a-zA-Z0-9\\s\\-\\.\\,#/]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(productDescription);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);
		}
	}

	/**
	 * Validates the URL to ensure it follows a specific pattern for image URLs.
	 *
	 * @param URL The URL to be validated.
	 * @return true if the URL is valid, false otherwise.
	 * @throws ValidatorInitializationException if the URL is null or does not meet
	 *                                          the required format.
	 */
	public static boolean validateURL(String url) throws ValidatorInitializationException,MalformedURLException {
		if (url == null || url.trim().isEmpty()) {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_URL_NULL);
		}
	    UrlValidator validator = new UrlValidator();
	   boolean isMatch=validator.isValid(url);
	    if (isMatch) {
			return true;
		} else {
			throw new MalformedURLException(ProductValidateErrors.INVALID_URL);
		}
	}


	public static boolean validateEventId(int eventId) throws ValidatorInitializationException {
		if (eventId <= 0) {
			throw new ValidatorInitializationException(ProductValidateErrors.INVALID_EVENT_ID);
		} else {
			return true;
		}
	}
}
