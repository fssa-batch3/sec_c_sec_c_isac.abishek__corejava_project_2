package com.fssa.event.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.event.exceptions.EventValidatorErrors;
import com.fssa.event.exceptions.ValidatorInitializationException;
import com.fssa.event.model.Event;

/**
 * The EventValidator class contains static methods for validating event-related data.
 * It performs various checks to ensure the data meets the specified criteria.
 */
public class EventValidator {

    /**
     * Validates the complete event object to ensure all properties meet the required criteria.
     *
     * @param event The Event object to be validated.
     * @return true if the event passes all validation checks, false otherwise.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator or other validation errors occur.
     */
    public static boolean validate(Event event) throws ValidatorInitializationException {
       
        try {
            validateEventName(event.getEventName());
            validateOrganizerName(event.getOrganizerName());
           
            validateContactNo(event.getContactNumber());
            validateEventLocation(event.getEventLocation());
            ValidateAboutEvent(event.getAboutEvent());
            validateURL(event.getImageUrl());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the event name to ensure it contains only alphabetic characters or spaces.
     *
     * @param eventName The name of the event to be validated.
     * @return true if the event name is valid, false otherwise.
     * @throws ValidatorInitializationException if the event name is null or does not meet the required format.
     */
    public static boolean validateEventName(String eventName) throws ValidatorInitializationException {
        if (eventName == null || eventName.isEmpty()) {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_NULL);
        }
        String regex = "^[A-Za-z]+(?: [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(eventName);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_NAME);
        }
    }

    /**
     * Validates the organizer's name to ensure it contains only alphabetic characters or spaces.
     *
     * @param organizerName The name of the event organizer to be validated.
     * @return true if the organizer's name is valid, false otherwise.
     * @throws ValidatorInitializationException if the organizer's name is null or does not meet the required format.
     */
    public static boolean validateOrganizerName(String organizerName) throws ValidatorInitializationException {
        if (organizerName == null || organizerName.isEmpty()) {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ORGANIZER_NULL);
        }
        String regex = "^[A-Za-z]+(?: [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(organizerName);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ORGANIZER_NAME);
        }
    }

    /**
     * Validates the event ID to ensure it is greater than zero.
     *
     * @param eventId The ID of the event to be validated.
     * @return true if the event ID is valid, false otherwise.
     * @throws ValidatorInitializationException if the event ID is less than or equal to zero.
     */
    public static boolean validateEventId(int eventId) throws ValidatorInitializationException {
        if (eventId <= 0) {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_EVENT_ID);
        } else {
            return true;
        }
    }

    /**
     * Validates the contact number to ensure it follows a specific pattern for Indian phone numbers.
     *
     * @param contactNo The contact number to be validated.
     * @return true if the contact number is valid, false otherwise.
     * @throws ValidatorInitializationException if the contact number is null or does not meet the required format.
     */
    public static boolean validateContactNo(String contactNo) throws ValidatorInitializationException {
        if (contactNo == null) {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_CONTACT_NO_NULL);
        }
        String regex = "^(\\+91|91)?[6789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contactNo);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_CONTACT_NUMBER);
        }
    }

    /**
     * Validates the event location to ensure it contains only alphanumeric characters, spaces, and certain special characters.
     *
     * @param eventLocation The location of the event to be validated.
     * @return true if the event location is valid, false otherwise.
     * @throws ValidatorInitializationException if the event location is null or does not meet the required format.
     */
    public static boolean ValidateAboutEvent(String aboutEvent) throws ValidatorInitializationException {
        if (aboutEvent == null || aboutEvent.isEmpty()) {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_ABOUT__EVENT_NULL);
        }
        String regex = "^[a-zA-Z0-9\\s\\-\\.\\,#/]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(aboutEvent);
        Boolean isMatch = matcher.matches();
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
        Boolean isMatch = matcher.matches();
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
     * @throws ValidatorInitializationException if the URL is null or does not meet the required format.
     */
    public static boolean validateURL(String URL) throws ValidatorInitializationException {
        if (URL == null || URL.trim().isEmpty()) {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_URL_NULL);
        }

        String regex = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(URL);
        Boolean isMatch = matcher.matches();

        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(EventValidatorErrors.INVALID_URL);
        }
    }
}
