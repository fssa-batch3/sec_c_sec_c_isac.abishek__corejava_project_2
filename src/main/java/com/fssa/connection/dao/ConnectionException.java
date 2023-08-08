package com.fssa.connection.dao;

public class ConnectionException extends Exception {

	/**
	 * A unique identifier for the serialized version of this class.
	 */
	private static final long serialVersionUID = 3785994329739220887L;

	/**
	 * Constructs a new DaoException with the specified error message.
	 *
	 * @param msg The error message describing the cause of the exception.
	 */
	public ConnectionException(String msg) {
		super(msg);
	}
}
