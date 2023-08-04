package com.fssa.event.exceptions;

/**
 * Custom exception class for handling data access related issues in the application's DAO layer.
 */
public class DaoException extends Exception {

    /**
     * A unique identifier for the serialized version of this class.
     */
    private static final long serialVersionUID = 3785994329939220887L;

    /**
     * Constructs a new DaoException with the specified error message.
     *
     * @param msg The error message describing the cause of the exception.
     */
    public DaoException(String msg) {
        super(msg);
    }
}

