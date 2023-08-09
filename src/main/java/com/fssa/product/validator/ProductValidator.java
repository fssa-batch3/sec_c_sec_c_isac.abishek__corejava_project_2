package com.fssa.product.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fssa.product.exception.ProductValidateErrors;
import com.fssa.product.exception.ValidatorIntializationException;
import com.fssa.product.model.Product;

public class ProductValidator {

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
	public static boolean validate(Product product) throws ValidatorIntializationException {
		if (product == null) {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_PRODUCT_NULL);
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
	public static boolean validateProductName(String productName) throws ValidatorIntializationException {
		if (productName == null) {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_PRODUCT_NULL);
		}
		String regex = "^[A-Za-z\s]+$";
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
	 * Validates the ProductDescription to ensure it contains only alphanumeric
	 * characters, spaces, and certain special characters.
	 *
	 * @param ProductDescription The ProductDescription to be validated.
	 * @return true if the ProductDescription is valid, false otherwise.
	 * @throws ValidatorInitializationException if the ProductDescription is null or
	 *                                          does not meet the required format.
	 */
	public static boolean validateDescription(String productDescription) throws ValidatorIntializationException {
		if (productDescription == null) {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION_NULL);
		}
		String regex = "^[a-zA-Z0-9\\s\\-\\.\\,#/]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(productDescription);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_PRODUCTDESCRIPTION);
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
	public static boolean validateURL(String url) throws ValidatorIntializationException {
		if (url == null || url.trim().isEmpty()) {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_URL_NULL);
		}

		String regex = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		boolean isMatch = matcher.matches();

		if (isMatch) {
			return true;
		} else {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_URL);
		}
	}

	public static boolean validateEventId(int eventId) throws ValidatorIntializationException {
		if (eventId <= 0) {
			throw new ValidatorIntializationException(ProductValidateErrors.INVALID_EVENT_ID);
		} else {
			return true;
		}
	}
}
