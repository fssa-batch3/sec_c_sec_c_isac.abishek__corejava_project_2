package com.fssa.charitytrust.validator;

import java.net.MalformedURLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Event;
import com.fssa.charitytrust.validator.EventValidator;

class TestEventValidator {
	 LocalDate input = LocalDate.of(2023, 12, 12);
	    Event event = new Event("Gem Event", "North Street, Taramani", "Organizer", "9751328805",
	            "https://freeimage.host/i/HNRzLYJ.jpg", input, "Concert");
	    Event testObj = new Event();

	    /**
	     * Tests validation of a complete event object.
	     */
	    @Test
	    void testValidateCompleteEvent() {
	        try {
	            Assertions.assertTrue(EventValidator.validate(event));
	        } catch (ValidatorInitializationException e) {
	            e.getMessage();
	        }
	    }

	    /**
	     * Tests validation of a null event object.
	     */
	    @Test
	    void testValidateNullEvent() {
	        try {
	            EventValidator.validate(null);
	        } catch (ValidatorInitializationException e) {
	            Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NULL, e.getMessage());
	        }
	    }

	    /**
	     * Tests validation of a name.
	     */
	@Test
	void testValidName() throws ValidatorInitializationException {
		testObj.setEventName("Gem Event");
		String eventName = testObj.getEventName();
		Assertions.assertTrue(EventValidator.validateEventName(eventName));
	}
	/**
     * Tests validation of a Invalidname.
     */
	@Test
	void testInvalidName() {
		testObj.setEventName("Gem123");
		String eventName = testObj.getEventName();
		try {
			EventValidator.validateEventName(eventName);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NAME, e.getMessage());
		}
	}
	/**
     * Tests validation of a name null.
     */
	@Test
	void testInvalidNameNull() {
		try {
			EventValidator.validateEventName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a name empty.
     */
	@Test
	void testInvalidNameEmpty() {
		try {
			EventValidator.validateEventName("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a organizer name.
     */
	@Test
	void testValidOrganizerName() throws ValidatorInitializationException {
		testObj.setOrganizerName("Jason Manova");
		String organizerName = testObj.getOrganizerName();
		Assertions.assertTrue(EventValidator.validateOrganizerName(organizerName));
	}
	/**
     * Tests validation of a Invalid organizer name.
     */
	@Test
	void testInvalidOrganizerName() {
		try {
			EventValidator.validateOrganizerName("jason 122");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ORGANIZER_NAME, e.getMessage());
		}
	}
	/**
     * Tests validation of a organizer name null.
     */
	@Test
	void testInvalidOrganizerNameNull() {
		try {
			EventValidator.validateOrganizerName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ORGANIZER_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a organizer name empty.
     */
	@Test
	void testInvalidOrganizerNameEmpty() {
		try {
			EventValidator.validateOrganizerName(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ORGANIZER_NAME, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact no.
     */
	@Test
	void testValidContactNo() throws ValidatorInitializationException {
		testObj.setContactNumber("919876543210");
		String contactNumber = testObj.getContactNumber();
		Assertions.assertTrue(EventValidator.validateContactNo(contactNumber));
	}
	/**
     * Tests validation of a invalid contact no.
     */
	@Test
	void testInvalidContactNo() {
		try {
			EventValidator.validateContactNo("1239751328805");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_CONTACT_NUMBER, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact null.
     */
	@Test
	void testInvalidContactNoNull() {
		try {
			EventValidator.validateContactNo(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_CONTACT_NO_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a contact no empty.
     */
	@Test
	void testInvalidContactEmpty() {
		try {
			EventValidator.validateContactNo(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_CONTACT_NUMBER, e.getMessage());
		}
	}
	/**
     * Tests validation of a address.
     */
	@Test
	void testValidAddress() throws ValidatorInitializationException {
		testObj.setEventLocation("North Street, Taramani");
		String address = testObj.getEventLocation();
		Assertions.assertTrue(EventValidator.validateEventLocation(address));
	}
	/**
     * Tests validation of a invalid address.
     */
	@Test
	void testInvalidAddress() {
		try {
			EventValidator.validateEventLocation("Too$many%$special@characters!");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_LOCATION, e.getMessage());
		}
	}
	/**
     * Tests validation of a address null.
     */
	@Test
	void testInvalidAddressNull() {
		try {
			EventValidator.validateEventLocation(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_LOCATION_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a address empty.
     */
	@Test
	void testInvalidAddressEmpty() {
		try {
			EventValidator.validateEventLocation("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_LOCATION_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a date.
     */
	@Test
	void testDate() throws ValidatorInitializationException {
		LocalDate input = LocalDate.of(2022, 10, 10);
		testObj.setEventDate(input);
		LocalDate nowDate = testObj.getEventDate();
		Assertions.assertEquals(nowDate, input);
	}
	/**
     * Tests validation of a about Event.
     */
	@Test
	void testValidAboutEvent() throws ValidatorInitializationException {
		testObj.setAboutEvent("Concert");
		String res = testObj.getAboutEvent();
		Assertions.assertTrue(EventValidator.validateAboutEvent(res));
	}
	/**
     * Tests validation of a invalid about Event.
     */
	@Test
	void testInvalidAbout() {
		try {
			EventValidator.validateAboutEvent("%%%%%123456");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ABOUT_EVENT_TYPE, e.getMessage());
		}
	}
	/**
     * Tests validation of a about Event empty.
     */
	@Test
	void testInvalidAboutEventEmpty() {
		try {
			EventValidator.validateAboutEvent(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ABOUT_EVENT_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a about Event null.
     */
	@Test
	void testInvalidAboutEventNull() {
		try {
			EventValidator.validateAboutEvent(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ABOUT_EVENT_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a url.
     */
	@Test
	void testValidURL() throws ValidatorInitializationException, MalformedURLException {
		testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
		String URL = testObj.getImageUrl();
		Assertions.assertTrue(EventValidator.validateURL(URL));
	}
	/**
     * Tests validation of a invalid url.
     */
	@Test
	void testInvalidURL() throws MalformedURLException, ValidatorInitializationException {
		try {
			EventValidator.validateURL("NOTINTYPE");
		} catch (MalformedURLException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_URL, e.getMessage());
		}
	}
	/**
     * Tests validation of a url empty.
     */
	@Test
	void testInvalidURLEmpty() throws MalformedURLException {
		try {
			EventValidator.validateURL(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_URL_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a url null.
     */
	@Test
	void testInvalidURLNull() throws MalformedURLException {
		try {
			EventValidator.validateURL(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_URL_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a date.
     */
	@Test
	void validDate() throws ValidatorInitializationException {
		LocalDate input1 = LocalDate.of(2023, 12, 12);
		testObj.setEventDate(input1);
		LocalDate input = testObj.getEventDate();
		Assertions.assertTrue(EventValidator.isValidEventDate(input));
	}
	/**
     * Tests validation of a invalid date.
     */
	@Test
	void testInvalidDateNull() {
		try {
			EventValidator.isValidEventDate(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_DATE_NULL, e.getMessage());
		}
	}
	/**
     * Tests validation of a date before.
     */
	@Test
	void testInvalidDateBefore() {
		try {
			LocalDate input1 = LocalDate.of(2022, 10, 10);
			EventValidator.isValidEventDate(input1);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_DATE, e.getMessage());
		}
	}
}
