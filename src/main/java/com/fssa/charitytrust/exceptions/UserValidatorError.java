package com.fssa.charitytrust.exceptions;

public interface UserValidatorError {
 
	public static final String INVALID_USER_NULL = "User can't be null";
 
	public static final String INVALID_ID = "Id Can't be less than zero";
 
	public static final String INVALID_USERNAME_NULL = "UserName can't be null";
 
	public static final String INVALID_USERNAME_PATTERN = "UserName should not contain Special Character";
 
	public static final String INVALID_USEREMAIL_NULL = "UserEmail can't be null";
 
	public static final String INVALID_USEREMAIL_PATTERN = "UserEmail should contain Special Character";
 
	public static final String INVALID_USERROLE_NULL = "UserRole can't be null";
 
	public static final String INVALID_DATE_NULL = "Date of Birth can't be null";
 
	public static final String INVALID_DATE_FORMAT = "Date of Birth can't be in the future";
	
	public static final String INVALID_PASSWORD_NULL = "Password can't be null";

	public static final String INVALID_PASSWORD_PATTERN = "PassWord should not contain Special Character";
 
	public static final String  INVALID_CONTACT_NUMBER = "invalid contact number";
   
	public static final String  INVALID_CONTACT_NO_NULL = "invalid contact number";
	
	public static final String  INVALID_AADHAAR_NUMBER = "invalid AADHAAR number";
	   
	public static final String  INVALID_AADHAAR_NO_NULL = "aadhaar can't be null";

	public static final String INVALID_LOCATION_NULL = "location can't be null";

	public static final String INVALID_LOCATION = "invalid location";
}
