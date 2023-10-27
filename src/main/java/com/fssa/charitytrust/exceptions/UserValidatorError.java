package com.fssa.charitytrust.exceptions;

public interface UserValidatorError {
 
	public static final String INVALID_USER_NULL = "User can't be null";
 
	public static final String INVALID_ID = "Id Can't be less than zero";
 
	public static final String INVALID_USERNAME_NULL = "UserName can't be null";
 
	public static final String INVALID_USERNAME_PATTERN = "UserName should be mimium 3 maximum 50 should not contain specail charcters and numbers";
 
	public static final String INVALID_USEREMAIL_NULL = "UserEmail can't be null";
 
	public static final String INVALID_USEREMAIL_PATTERN = "Invalid email please enter a valid email email should only contain alphabets,numbers,@,. with maximum length 45  ";
 
	public static final String INVALID_USERROLE_NULL = "UserRole can't be null";
 

	public static final String INVALID_PASSWORD_LOGIN = "PassWord Invalid in Login";
	public static final String INVALID_PASSWORD_NULL = "Password can't be null";

	public static final String INVALID_PASSWORD_PATTERN = "PassWord should  contain Special Character";
 
	public static final String  INVALID_CONTACT_NUMBER = "contact number should only contain number with length 10 only starts with 6,7,8,9 ";
   
	public static final String  INVALID_CONTACT_NO_NULL = "invalid contact number null";
	

	public static final String INVALID_LOCATION_NULL = "pincode can't be null";

	public static final String INVALID_LOCATION = "Invalid pincode,pincode only contain 6 digit number";
	
	public static final String UNABLE_TO_LOGIN="Login Email or Password is wrong"; 
}
