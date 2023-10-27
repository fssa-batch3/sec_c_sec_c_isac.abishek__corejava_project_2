package com.fssa.charitytrust.validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.UserValidatorError;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.BookHospital;

public class BookHospitalValidator {

	public static boolean validateBookHospital(BookHospital bookhospital) throws ValidatorInitializationException {
		validateName(bookhospital.getUsername());
		validateName(bookhospital.getHospitalname());
		validateEmail(bookhospital.getEmail());
		validateContactNo(bookhospital.getContactNumber());
		isValidValidDate(bookhospital.getBookdate());
		return true;
		
	}
	
	public static boolean validateName(String name) throws ValidatorInitializationException {

		if (name == null || name.trim().equals("")) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USERNAME_NULL);
		}

		String regex = "^[a-zA-Z ]{3,50}+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		boolean isMatch = matcher.matches();

		if (isMatch) {
			return true;
		}
		else {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USERNAME_PATTERN);
		}
		
	}
	
	public static boolean validateEmail(String email) throws ValidatorInitializationException {
	       
		if (email == null || email.trim().equals("")) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USEREMAIL_NULL);
		}

		String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		boolean isMatch = matcher.matches();
 
		
		if (isMatch) {
		
			return true;
		}
		else { 
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USEREMAIL_PATTERN);
		}

		
	}
	
	public static boolean validateContactNo(String contactNo) throws ValidatorInitializationException {
		if (contactNo == null) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_CONTACT_NO_NULL);
		}
		String regex = "^(\\+91|91)?[6789]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(contactNo);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_CONTACT_NUMBER);
		}
	}
	
	
	public static boolean isValidValidDate(LocalDate eventDate) throws ValidatorInitializationException {
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
