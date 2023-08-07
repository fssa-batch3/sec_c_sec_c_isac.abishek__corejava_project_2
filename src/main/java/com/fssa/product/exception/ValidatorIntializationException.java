package com.fssa.product.exception;

/**
 * Custom exception class for handling errors related to the initialization of
 * data validators. This exception is thrown when there is an issue with
 * initializing or setting up the data validators.
 */
public class ValidatorIntializationException extends Exception {

	/**
	 * A unique identifier for the serialized version of this class. This ID is used
	 * during deserialization to ensure version compatibility.
	 */
	private static final long serialVersionUID = 3785994329939220786L;

	/**
	 * Constructs a new ValidatorInitializationException with the specified error
	 * message.
	 *
	 * @param msg The error message describing the cause of the exception.
	 */
	public ValidatorIntializationException(String msg) {
		super(msg);
	}
}
