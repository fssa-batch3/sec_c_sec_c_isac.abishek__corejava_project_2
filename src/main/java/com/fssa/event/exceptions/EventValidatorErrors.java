package com.fssa.event.exceptions;

/**
 * An interface containing constant error messages for event data validation.
 * These error messages can be used to identify specific issues when validating
 * event-related data.
 */
public interface EventValidatorErrors {

	/**
	 * Represents an error message for cases where the event is null.
	 */
	public static final String INVALID_EVENT_DATE_NULL = "Event  Date can't be Null ";
	/**
	 * Represents an error message for cases where the event is past date.
	 */
	public static final String INVALID_EVENT_DATE = "Event can't be Past Date";
	/**
	 * Represents an error message for cases where the event is null or empty.
	 */
	public static final String INVALID_EVENT_NULL = "Event can't be null or empty";

	/**
	 * Represents an error message for cases where the event name is invalid or
	 * empty.
	 */
	public static final String INVALID_EVENT_NAME = "Event Name Invalid or empty";

	/**
	 * Represents an error message for cases where the event ID is less than or
	 * equal to 0.
	 */
	public static final String INVALID_EVENT_ID = "Event ID must be greater than 0";

	/**
	 * Represents an error message for cases where the organizer name is null or
	 * empty.
	 */
	public static final String INVALID_ORGANIZER_NULL = "Organizer Name can't be null or empty";

	/**
	 * Represents an error message for cases where the organizer name is invalid.
	 */
	public static final String INVALID_ORGANIZER_NAME = "Organizer Name Invalid";

	/**
	 * Represents an error message for cases where the contact number is null or
	 * empty.
	 */
	public static final String INVALID_CONTACT_NO_NULL = "Contact Number can't be null or empty";

	/**
	 * Represents an error message for cases where the contact number is invalid.
	 */
	public static final String INVALID_CONTACT_NUMBER = "Invalid Contact Number";

	/**
	 * Represents an error message for cases where the event location is null or
	 * empty.
	 */
	public static final String INVALID_EVENT_LOCATION_NULL = "Event Location can't be null or empty";

	/**
	 * Represents an error message for cases where the event location is invalid.
	 */
	public static final String INVALID_EVENT_LOCATION = "Invalid Event Location";

	/**
	 * Represents an error message for cases where the event type is null or empty.
	 */
	public static final String INVALID_ABOUT_EVENT_NULL = "Event about can't be null or empty";

	/**
	 * Represents an error message for cases where the event type is invalid.
	 */
	public static final String INVALID_ABOUT_EVENT_TYPE = "Invalid Event about";

	/**
	 * Represents an error message for cases where the URL is null or empty.
	 */
	public static final String INVALID_URL_NULL = "URL can't be null or empty";

	/**
	 * Represents an error message for cases where the URL is invalid.
	 */
	public static final String INVALID_URL = "Invalid URL";
}
