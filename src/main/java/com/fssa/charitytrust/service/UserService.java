package com.fssa.charitytrust.service;

import java.time.LocalDate;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.UserDAO;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.logger.Logger;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;
import com.fssa.charitytrust.validator.UserValidator;


public class UserService {
 
	public static boolean addNewUser(User user) throws ValidatorInitializationException, DaoException, ConnectionException {
 

 
			UserDAO.addUser(user);
 
	
		return true;
	}
 
	public static boolean updateUser(User user) throws ValidatorInitializationException, DaoException, ConnectionException {
 
		if (UserValidator.validateUpdate(user)) {
             
			UserDAO.updateUser(user);
 
		}
		return true;
	}
 
	public static boolean deleteUser(User user) throws ValidatorInitializationException, DaoException, ConnectionException {
 
		if (UserValidator.validate(user)) {
 
			UserDAO.deleteUser(user);
 
		}
		return true;
	}
 
	public static List<User> getUserbyEmail(String mail) throws ValidatorInitializationException, DaoException, ConnectionException {
             
		if (UserValidator.validateEmail(mail)) {
 
			return UserDAO.getUserByEmail(mail);
			
		}
	   return null;
	}
	public static boolean AddUser(User user) throws ValidatorInitializationException, DaoException, ConnectionException {
		 
		if (UserValidator.validateEmail(user.getEmail())) {
 
		boolean res=UserDAO.checkMailAvailable(user.getEmail());
			if(res==true) {
				boolean checkActive=UserDAO.checkIsActive(user.getEmail());
			   if(checkActive==true) {
				   return false; // already exists
			   }
			   else {
				   UserDAO.makeIsActive(user.getEmail());
				   return true;
			   }
				
			}
			else {
				UserService.addNewUser(user);
				return true;
			}
		}
		return true;
	}
	
    public static boolean checkMailAvailable(String email) throws ValidatorInitializationException, DaoException, ConnectionException {
		
		if(UserValidator.validateEmail(email)) {
			
			return UserDAO.checkMailAvailable(email);
			
		}
		return true;
	}
    public boolean checkMailAndPassword(String email,String password) throws ValidatorInitializationException, DaoException, ConnectionException {
		
		if(UserValidator.validateEmail(email) && UserValidator.validatePassword(password)) {
			
			return UserDAO.checkMailAndPassword(email,password);
			
		}
		return true;
	}
	public static boolean setAccessblity(String email,boolean val) throws ConnectionException, DaoException {
		
		return UserDAO.makeActiveAccessblity(email,val);
	}
	
	public static void printUser(User user) {
		
		Logger.info("UserName :" + user.getUsername());
		Logger.info("Id :" + user.getId());
		Logger.info("Email :"+user.getEmail());
		Logger.info("Password :"+user.getPassword());		
		
	}
 
	public static void main(String[] args) throws DaoException, ConnectionException, ValidatorInitializationException {
		User user = new User("Isacdevabishek", "Isac@gmail.com", "Isac@123","Noerth Chennai,royapuram","7305836758","123456789012", UserRole.ADMIN, LocalDate.of(2005, 04, 06));
		UserService.updateUser(user);
//		UserService.deleteUser(user);
	}
}