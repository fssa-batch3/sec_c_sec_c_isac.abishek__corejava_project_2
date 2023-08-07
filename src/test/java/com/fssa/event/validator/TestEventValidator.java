package com.fssa.event.validator;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.event.exceptions.EventValidatorErrors;
import com.fssa.event.exceptions.ValidatorInitializationException;
import com.fssa.event.model.Event;

public class TestEventValidator {
	LocalDate input=LocalDate.of(2022, 10, 10);
    Event event = new Event( "Gem Event", "North Street, Taramani", "Organizer", "9751328805",
            "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert");
    Event testObj = new Event();

    @Test
    public void testEventValidatorObject() {

        try {
            EventValidator obj = new EventValidator();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void testValidateObject() {

        try {
            Assertions.assertTrue(EventValidator.validate(event));
        } catch (ValidatorInitializationException e) {
            e.getMessage();
        }
    }

    @Test
    public void testNullObject() {
        try {
            EventValidator.validate(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_NULL);
        }
    }

    @Test
    public void testValidName() throws ValidatorInitializationException {
        testObj.setEventName("Gem Event");
        String eventName = testObj.getEventName();
        Assertions.assertTrue(EventValidator.validateEventName(eventName));
    }

    @Test
    public void testInvalidName() {
        testObj.setEventName("Gem123");
        String eventName = testObj.getEventName();
        try {
            EventValidator.validateEventName(eventName);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_NAME);
        }
    }

    @Test
    public void testInvalidNameNull() {
        try {
            EventValidator.validateEventName(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_NULL);
        }
    }

    @Test
    public void testInvalidNameEmpty() {
        try {
            EventValidator.validateEventName("");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_NULL);
        }
    }

    @Test
    public void testValidOrganizerName() throws ValidatorInitializationException {
        testObj.setOrganizerName("Jason Manova");
        String organizerName = testObj.getOrganizerName();
        Assertions.assertTrue(EventValidator.validateOrganizerName(organizerName));
    }

    @Test
    public void testInvalidOrganizerName() {
        try {
            EventValidator.validateOrganizerName("jason 122");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_ORGANIZER_NAME);
        }
    }

    @Test
    public void testInvalidOrganizerNameNull() {
        try {
            EventValidator.validateOrganizerName(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_ORGANIZER_NULL);
        }
    }

    @Test
    public void testInvalidOrganizerNameEmpty() {
        try {
            EventValidator.validateOrganizerName(" ");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_ORGANIZER_NAME);
        }
    }

   

    @Test
    public void testValidContactNo() throws ValidatorInitializationException {
        testObj.setContactNumber("919876543210");
        String contactNumber = testObj.getContactNumber();
        Assertions.assertTrue(EventValidator.validateContactNo(contactNumber));
    }

    @Test
    public void testInvalidContactNo() {
        try {
            EventValidator.validateContactNo("1239751328805");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_CONTACT_NUMBER);
        }
    }

    @Test
    public void testInvalidContactNoNull() {
        try {
            EventValidator.validateContactNo(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_CONTACT_NO_NULL);
        }
    }

    @Test
    public void testInvalidContactEmpty() {
        try {
            EventValidator.validateContactNo(" ");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_CONTACT_NUMBER);
        }
    }

    @Test
    public void testValidAddress() throws ValidatorInitializationException {
        testObj.setEventLocation("North Street, Taramani");
        String address = testObj.getEventLocation();
        Assertions.assertTrue(EventValidator.validateEventLocation(address));
    }

    @Test
    public void testInvalidAddress() {
        try {
            EventValidator.validateEventLocation("Too$many%$special@characters!");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_LOCATION);
        }
    }

    @Test
    public void testInvalidAddressNull() {
        try {
            EventValidator.validateEventLocation(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_LOCATION_NULL);
        }
    }

    @Test
    public void testInvalidAddressEmpty() {
        try {
            EventValidator.validateEventLocation("");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_EVENT_LOCATION_NULL);
        }
    }

    @Test
    public void testDate() throws ValidatorInitializationException {
    	LocalDate input=LocalDate.of(2022, 10, 10);
        testObj.setEventDate(input);
        LocalDate nowDate = testObj.getEventDate();
        Assertions.assertEquals(nowDate, input);
    }

    @Test
    public void testValidType() throws ValidatorInitializationException {
        testObj.setAboutEvent("Concert");
        String res = testObj.getAboutEvent();
        Assertions.assertTrue(EventValidator.validateAboutEvent(res));
    }

    @Test
    public void testInvalidType() {
        try {
            EventValidator.validateAboutEvent("123456");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_ABOUT_EVENT_TYPE);
        }
    }

    @Test
    public void testInvalidTypeEmpty() {
        try {
            EventValidator.validateAboutEvent(" ");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_ABOUT__EVENT_NULL);
        }
    }

    @Test
    public void testInvalidTypeNull() {
        try {
            EventValidator.validateAboutEvent(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_ABOUT__EVENT_NULL);
        }
    }

    @Test
    public void testValidURL() throws ValidatorInitializationException {
        testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
        String URL = testObj.getImageUrl();
        Assertions.assertTrue(EventValidator.validateURL(URL));
    }

    @Test
    public void testInvalidURL() {
        try {
            EventValidator.validateURL("NOTINTYPE");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_URL);
        }
    }

    @Test
    public void testInvalidURLEmpty() {
        try {
            EventValidator.validateURL(" ");
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_URL_NULL);
        }
    }

    @Test
    public void testInvalidURLNull() {
        try {
            EventValidator.validateURL(null);
        } catch (ValidatorInitializationException e) {
            Assertions.assertEquals(e.getMessage(), EventValidatorErrors.INVALID_URL_NULL);
        }
    }
}
