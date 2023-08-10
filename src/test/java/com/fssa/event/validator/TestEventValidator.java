package com.fssa.event.validator;

import java.net.MalformedURLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.event.exceptions.EventValidatorErrors;
import com.fssa.event.exceptions.ValidatorInitializationException;
import com.fssa.event.model.Event;

class TestEventValidator {
	LocalDate input = LocalDate.of(2023, 10, 10);
	Event event = new Event("Gem Event", "North Street, Taramani", "Organizer", "9751328805",
			"https://freeimage.host/i/HNRzLYJ.jpg", input, "Concert");
	Event testObj = new Event();

	@Test
	void testValidateObject() {

		try {
			Assertions.assertTrue(EventValidator.validate(event));
		} catch (ValidatorInitializationException e) {
			e.getMessage();
		}
	}

	@Test
	void testNullObject() {
		try {
			EventValidator.validate(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidName() throws ValidatorInitializationException {
		testObj.setEventName("Gem Event");
		String eventName = testObj.getEventName();
		Assertions.assertTrue(EventValidator.validateEventName(eventName));
	}

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

	@Test
	void testInvalidNameNull() {
		try {
			EventValidator.validateEventName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidNameEmpty() {
		try {
			EventValidator.validateEventName("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidOrganizerName() throws ValidatorInitializationException {
		testObj.setOrganizerName("Jason Manova");
		String organizerName = testObj.getOrganizerName();
		Assertions.assertTrue(EventValidator.validateOrganizerName(organizerName));
	}

	@Test
	void testInvalidOrganizerName() {
		try {
			EventValidator.validateOrganizerName("jason 122");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ORGANIZER_NAME, e.getMessage());
		}
	}

	@Test
	void testInvalidOrganizerNameNull() {
		try {
			EventValidator.validateOrganizerName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ORGANIZER_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidOrganizerNameEmpty() {
		try {
			EventValidator.validateOrganizerName(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ORGANIZER_NAME, e.getMessage());
		}
	}

	@Test
	void testValidContactNo() throws ValidatorInitializationException {
		testObj.setContactNumber("919876543210");
		String contactNumber = testObj.getContactNumber();
		Assertions.assertTrue(EventValidator.validateContactNo(contactNumber));
	}

	@Test
	void testInvalidContactNo() {
		try {
			EventValidator.validateContactNo("1239751328805");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_CONTACT_NUMBER, e.getMessage());
		}
	}

	@Test
	void testInvalidContactNoNull() {
		try {
			EventValidator.validateContactNo(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_CONTACT_NO_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidContactEmpty() {
		try {
			EventValidator.validateContactNo(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_CONTACT_NUMBER, e.getMessage());
		}
	}

	@Test
	void testValidAddress() throws ValidatorInitializationException {
		testObj.setEventLocation("North Street, Taramani");
		String address = testObj.getEventLocation();
		Assertions.assertTrue(EventValidator.validateEventLocation(address));
	}

	@Test
	void testInvalidAddress() {
		try {
			EventValidator.validateEventLocation("Too$many%$special@characters!");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_LOCATION, e.getMessage());
		}
	}

	@Test
	void testInvalidAddressNull() {
		try {
			EventValidator.validateEventLocation(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_LOCATION_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidAddressEmpty() {
		try {
			EventValidator.validateEventLocation("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_LOCATION_NULL, e.getMessage());
		}
	}

	@Test
	void testDate() throws ValidatorInitializationException {
		LocalDate input = LocalDate.of(2022, 10, 10);
		testObj.setEventDate(input);
		LocalDate nowDate = testObj.getEventDate();
		Assertions.assertEquals(nowDate, input);
	}

	@Test
	void testValidType() throws ValidatorInitializationException {
		testObj.setAboutEvent("Concert");
		String res = testObj.getAboutEvent();
		Assertions.assertTrue(EventValidator.validateAboutEvent(res));
	}

	@Test
	void testInvalidType() {
		try {
			EventValidator.validateAboutEvent("123456");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ABOUT_EVENT_TYPE, e.getMessage());
		}
	}

	@Test
	void testInvalidTypeEmpty() {
		try {
			EventValidator.validateAboutEvent(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ABOUT_EVENT_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidTypeNull() {
		try {
			EventValidator.validateAboutEvent(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_ABOUT_EVENT_NULL, e.getMessage());
		}
	}

	@Test
	void testValidURL() throws ValidatorInitializationException, MalformedURLException {
		testObj.setImageUrl("https://iili.io/H8lK1MQ.jpg");
		String URL = testObj.getImageUrl();
		Assertions.assertTrue(EventValidator.validateURL(URL));
	}

	@Test
	void testInvalidURL() throws MalformedURLException, ValidatorInitializationException {
		try {
			EventValidator.validateURL("NOTINTYPE");
		} catch (MalformedURLException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_URL, e.getMessage());
		}
	}

	@Test
	void testInvalidURLEmpty() throws MalformedURLException {
		try {
			EventValidator.validateURL(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_URL_NULL, e.getMessage());
		}
	}

	@Test
	void testInvalidURLNull() throws MalformedURLException {
		try {
			EventValidator.validateURL(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_URL_NULL, e.getMessage());
		}
	}

	@Test
	void validDate() throws ValidatorInitializationException {
		LocalDate input1 = LocalDate.of(2023, 10, 10);
		testObj.setEventDate(input1);
		LocalDate input = testObj.getEventDate();
		Assertions.assertTrue(EventValidator.isValidEventDate(input));
	}

	@Test
	void testInvalidDateNull() {
		try {
			EventValidator.isValidEventDate(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_DATE_NULL, e.getMessage());
		}
	}

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
