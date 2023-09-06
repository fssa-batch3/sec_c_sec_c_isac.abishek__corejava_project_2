package com.fssa.charitytrust.validator;

import java.net.MalformedURLException;


import org.apache.commons.validator.routines.UrlValidator;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Event;

/**
 * The EventValidator class contains static methods for validating event-related
 * data. It performs various checks to ensure the data meets the specified
 * criteria.
 */
public class EventValidator {

	/**
	 * Validates the complete event object to ensure all properties meet the
	 * required criteria.
	 *
	 * @param event The Event object to be validated.
	 * @return true if the event passes all validation checks, false otherwise.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator or other
	 *                                          validation errors occur.
	 */
	
	
	public static boolean validate(Event event) throws ValidatorInitializationException {

		try { 
			validateEventName(event.getEventName());
			
			validateOrganizerName(event.getOrganizerName());
		
			validateContactNo(event.getContactNumber());
			
			validateEventLocation(event.getEventLocation());
		
			validateAboutEvent(event.getAboutEvent());
			
			validateURL(event.getImageUrl());
			
			isValidEventDate(event.getEventDate());
			
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
	 * Validates the organizer's name to ensure it contains only alphabetic
	 * characters or spaces.
	 *
	 * @param organizerName The name of the event organizer to be validated.
	 * @return true if the organizer's name is valid, false otherwise.
	 * @throws ValidatorInitializationException if the organizer's name is null or
	 *                                          does not meet the required format.
	 */
	public static boolean validateOrganizerName(String organizerName) throws ValidatorInitializationException {
		if (organizerName == null || organizerName.isEmpty()) {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ORGANIZER_NULL);
		}
		String regex = "[a-zA-Z ]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(organizerName);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ORGANIZER_NAME);
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
	public static boolean validateContactNo(String contactNo) throws ValidatorInitializationException {
		if (contactNo == null) {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_CONTACT_NO_NULL);
		}
		String regex = "^(\\+91|91)?[6789]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(contactNo);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_CONTACT_NUMBER);
		}
	}

	/**
	 * Validates the event location to ensure it contains only alphanumeric
	 * characters, spaces, and certain special characters.
	 *
	 * @param eventLocation The location of the event to be validated.
	 * @return true if the event location is valid, false otherwise.
	 * @throws ValidatorInitializationException if the event location is null or
	 *                                          does not meet the required format.
	 */
	public static boolean validateAboutEvent(String aboutEvent) throws ValidatorInitializationException {
		if (aboutEvent == null || aboutEvent.isEmpty()) {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ABOUT_EVENT_NULL);
		}
		String regex = "^[a-zA-Z0-9\\s\\-\\.\\,#/]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(aboutEvent);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ABOUT_EVENT_TYPE);
		}
	}

	public static boolean validateEventLocation(String eventLocation) throws ValidatorInitializationException {
		if (eventLocation == null || eventLocation.isEmpty()) {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_LOCATION_NULL);
		}
		String regex = "^[a-zA-Z0-9\\s\\-\\.\\,#/]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(eventLocation);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_LOCATION);
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
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_URL_NULL);
		}
	    UrlValidator validator = new UrlValidator();
	   boolean isMatch=validator.isValid(url);
	    if (isMatch) {
			return true;
		} else {
			throw new MalformedURLException(EventValidatorErrors.INVALID_URL);
		}
	}
	

	public static boolean isValidEventDate(LocalDate eventDate) throws ValidatorInitializationException {
		LocalDate today = LocalDate.now();
		if (eventDate == null) {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_DATE_NULL);
		} else if (eventDate.isAfter(today)) {
			return true;
		} else {
			throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_DATE);
		}
	}
}
