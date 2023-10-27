package com.fssa.charitytrust.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.UserServiceErrors;
import com.fssa.charitytrust.exceptions.UserValidatorError;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;

public class TestUserService {
	UserService service = new UserService();
	UserRole role = UserRole.valueOf("VOLUNTEER");
	User obj = new User(9, "Balaji", "balajii@gmail.com", "Balaji@2002", "626109", "9080020730",
			 role);
	
	@Test
	void testAddNewUSer() throws ServiceException {
		User obj = new User(9, "Balaji", "balaji@gmail.com", "Balaji@2002", "626109", "9080020730",
				 role);
		Assertions.assertTrue(service.AddUser(obj));
	}

	@Test
	void testInvalidUSer() {
		try {
			service.addNewUser(null);
		} catch (ServiceException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USER_NULL, e.getMessage());
		}
	}

	@Test
	void testUpdateUSer() throws ServiceException {
		User obj = new User(9, "Nantha", "balaji@gmail.com", "Balaji@2002", "626109", "9080020730",
				 role);
		Assertions.assertTrue(service.updateUser(obj));
	}

	@Test
	void testInvalidUpdateUSer() {
		try {
			service.updateUser(null);
		} catch (ServiceException e) {
			Assertions.assertEquals(e.getMessage(), UserValidatorError.INVALID_USER_NULL);
		}
	}

	@Test
	void testDeleteUSer() throws ServiceException {
		User obj = new User(9, "Nantha", "balaji@gmail.com", "Balaji@2002", "626109", "9080020730",
				 role);
		Assertions.assertTrue(service.deleteUser(obj));
	}

	@Test
	void testInvalidDeleteUser() {
		try {
			service.deleteUser(null);
		} catch (ServiceException e) {
			Assertions.assertEquals(e.getMessage(), UserValidatorError.INVALID_USER_NULL);
		}
	}

	@Test
	void testUserByMail() throws ServiceException {

		Assertions.assertNotNull(service.getUserbyEmail(obj.getEmail()));
	}

	@Test
	void testInvalidUserByMail() {
		try {
			service.getUserbyEmail("NotAvailableEmail@gmail.com");
		} catch (ServiceException e) {
			Assertions.assertEquals(e.getMessage(), UserValidatorError.INVALID_USER_NULL);
		}
	}

	@Test
	void testUserAlreadyMail() {
		try {
			service.AddUser(obj);
		} catch (ServiceException e) {
			Assertions.assertEquals("Email Already Exist", e.getMessage());
		}
	}

	@Test
	void testCheckUserByMail() throws ServiceException {

		Assertions.assertTrue(service.checkMailAvailable(obj.getEmail()));
	}

	@Test
	void testUserNotAlreadyAvilableMail() {
		try {
			service.checkMailAvailable("Invalid");
		} catch (ServiceException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USEREMAIL_PATTERN, e.getMessage());
		}
	}

	@Test
	void testCheckUserByMailAndPassword() throws ServiceException {

		Assertions.assertTrue(service.checkMailAndPassword(obj.getEmail(), obj.getPassword()));
	}

	@Test
	void testNotCheckUserByMailAndPassword() {
		try {
			service.checkMailAndPassword("Invalid", "Invalid");
		} catch (ServiceException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USEREMAIL_PATTERN, e.getMessage());
		}
	}

	@Test
	void testAcceblity() throws ServiceException, ValidatorInitializationException {

		Assertions.assertFalse(service.setAccessblity(obj.getEmail(), true));
	}

	@Test
	void testInvalidAcceblity() throws ValidatorInitializationException, ServiceException {
		try {
			UserService.setAccessblity("Invalid", true);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(UserValidatorError.INVALID_USEREMAIL_PATTERN, e.getMessage());
		}
	}
}
