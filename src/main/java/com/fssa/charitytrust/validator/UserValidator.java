package com.fssa.charitytrust.validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.charitytrust.exceptions.UserValidatorError;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;

public class UserValidator {

	public static boolean validate(User user) throws ValidatorInitializationException {

		if (user == null) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USER_NULL);
		}
     try {
    	 validateName(user.getUsername());
 		
 		validateEmail(user.getEmail());
 		
 		validateUserRole(user.getRole());
 		 
 		validatePassword(user.getPassword());
 		 
 		validateContactNo(user.getContactNumber());
 		 
 		validateLocation(user.getAddress());
 		 
 		

 		return true;
     }
     catch(ValidatorInitializationException e) {
    	 throw new ValidatorInitializationException(e.getMessage());
     }
		
	}
	public static boolean validateUpdate(User user) throws ValidatorInitializationException {

		if (user == null) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USER_NULL);
		}

		validateName(user.getUsername());
		validateEmail(user.getEmail());
		validateContactNo(user.getContactNumber());
		validateLocation(user.getAddress());
		

		return true;
	}

	public static boolean validateId(int id) throws ValidatorInitializationException {

		if (id <= 0) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_ID);
		}
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

	public static boolean validateUserRole(UserRole userRole) throws ValidatorInitializationException {

		if (userRole == null) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_USERROLE_NULL);
		}
		return true;
	}

	public static boolean validatePassword(String password) throws ValidatorInitializationException {

		if (password == null || password.isEmpty()) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_PASSWORD_NULL);
		}

		String regex = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(password);

		boolean isMatch = matcher.matches();

		if (isMatch) {
			return true;
		}
		else {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_PASSWORD_LOGIN);
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

	
	public static boolean validateLocation(String location) throws ValidatorInitializationException {
		if (location == null || location.isEmpty()) {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_LOCATION_NULL);
		}
		String regex = "^(?!0\\d{5}|(\\d)\\1{5})[1-9]\\d{5}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(location);
		boolean isMatch = matcher.matches();
		if (isMatch) {
			return true;
		} else {
			throw new ValidatorInitializationException(UserValidatorError.INVALID_LOCATION);
		}
	}
}
