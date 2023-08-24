package com.fssa.charitytrust.productexception;

/**
 * An interface containing constant error messages for the custom DaoException
 * class. These error messages can be used to identify specific types of data
 * access related issues when handling DaoException in the application.
 */
public final class DaoExceptionErrors {

	/**
	 * Represents an error message for cases where the input provided is invalid or
	 * does not exist.
	 */
	public static final String INVALID_INPUT = "Input Not Exists";

	/**
	 * Represents an error message for cases where no database rows are affected by
	 * an operation. This can happen, for example, when trying to update or delete a
	 * non-existing record.
	 */
	public static final String ROW_AFFECTED = "No Lines Are Affected";

	/**
	 * Represents an error message for cases where there is a problem with the
	 * database connection. This could happen when the application fails to
	 * establish a connection with the database.
	 */
	public static final String CONNECTION_ERROR = "Connection Error";
	
	private DaoExceptionErrors() {
		
	}
}
