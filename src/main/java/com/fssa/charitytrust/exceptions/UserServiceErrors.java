package com.fssa.charitytrust.exceptions;

public class UserServiceErrors {
	/**
	 * Represents an error message for cases where the input provided is invalid or
	 * does not exist.
	 */
	public static final String UNABLE_TO_ADD = "UNABLE_TO_ADD";

	/**
	 * Represents an error message for cases where no database rows are affected by
	 * an operation. This can happen, for example, when trying to update or delete a
	 * non-existing record.
	 */
	public static final String UNABLE_TO_UPDATE_USER = "UNABLE_TO_UPDATE";

	/**
	 * Represents an error message for cases where there is a problem with the
	 * database connection. This could happen when the application fails to
	 * establish a connection with the database.
	 */
	public static final String CONNECTION_ERROR = "Internal Server Connection Error";
	
	public static final String UNABLE_TO_ISACTIVE_USER = "UNABLE_TO_DELETE";
	
	public static final String UNABLE_TO_VIEW = "UNABLE_TO_VIEW";
	
	public static final String UNABLE_TO_UPDATE_ACCEBLITY = "UNABLE_TO_UPDATE_ACCEBLITY";
	
	public static final String UNABLE_TO_GET_USER = "UNABLE_TO_GET_USER";
	
	public static final String CONTACT_NOT_AVAILABLE = "CONTACT_NOT_AVAILABLE";
	
	public static final String MAIL_NOT_AVAILABLE = "MAIL_NOT_AVAILABLE";
	
	public static final String MAIL_AND_PASSWORD_NOT_AVAILABLE = "Mail or password is wrong";
	
	public static final String CONTACT_EXISTS = "One Request For One Mobile Number";
	public static final String SUCCESSFULLY_LOGGEDIN   ="Successfully LoggedIn";
	
	private UserServiceErrors() {
		
	}
}
