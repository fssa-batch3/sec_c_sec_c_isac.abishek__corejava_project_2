package com.fssa.charitytrust.service;

import java.time.LocalDate;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.UserDAO;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.logger.Logger;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;
import com.fssa.charitytrust.validator.UserValidator;


public class UserService {
 
	public static boolean addNewUser(User user) throws ServiceException {
 

 
			try {
				if(UserValidator.validate(user))
				UserDAO.addUser(user);
			} catch (DaoException | ConnectionException | ValidatorInitializationException e) {
				throw new ServiceException(e.getMessage());
			}
 
	 
		return true;
	}
 
	public static boolean updateUser(User user) throws ServiceException {
 
		try {
			if (UserValidator.validateUpdate(user)) {
			     
				UserDAO.updateUser(user);
 
			}
		} catch (ValidatorInitializationException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		return true;
	}
 
	public static boolean deleteUser(User user) throws ServiceException {
 
		try {
			if (UserValidator.validate(user)) {
 
				UserDAO.deleteUser(user);
 
			}
		} catch (ValidatorInitializationException | DaoException | ConnectionException e) {
			throw new ServiceException(e.getMessage());
		}
		return true;
	}
 
	public static List<User> getUserbyEmail(String mail) throws ServiceException {
             
		try {
			if (UserValidator.validateEmail(mail)) {
 
				return UserDAO.getUserByEmail(mail);
				
			}
		} catch (ValidatorInitializationException | DaoException | ConnectionException e) {
			throw new ServiceException(e.getMessage());
		}
	   return null;
	}
	public static boolean AddUser(User user) throws ServiceException {
		 try {
			if(UserValidator.validate(user)) {
			if (UserValidator.validateEmail(user.getEmail())) {
 
			boolean res=UserDAO.checkMailAvailable(user.getEmail().trim().toLowerCase());
				if(res==true) {
					boolean checkActive=UserDAO.checkIsActive(user.getEmail());
				   if(checkActive==true) {
					   throw new ServiceException("Email Already Exist"); // already exists
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
			 }
		} catch (ValidatorInitializationException | ConnectionException | DaoException | ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
	
		}
		return true;
	}
	
    public static boolean checkMailAvailable(String email) throws ServiceException {
		
		try {
			if(UserValidator.validateEmail(email)) {
				
				return UserDAO.checkMailAvailable(email);
				
			}
		} catch (ValidatorInitializationException | ConnectionException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return true;
	}
public static boolean checkMobileAvailable(String mobile) throws ServiceException {
		
		try {
			if(UserValidator.validateContactNo(mobile)) {
				
				return UserDAO.CheckMobileExists(mobile);
				
			}
		} catch (ValidatorInitializationException | ConnectionException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return true;
	}
    public boolean checkMailAndPassword(String email,String password) throws ServiceException {
		
		try {
			if(UserValidator.validateEmail(email) && UserValidator.validatePassword(password)) {
				try {
					password = UserDAO.hashPassword(password);
				} catch (DaoException e) {
					e.printStackTrace();
				}
				return UserDAO.checkMailAndPassword(email,password);
				
			}
		} catch (ValidatorInitializationException | ConnectionException | DaoException e) {
			
		
			throw new ServiceException(e.getMessage());
		}
		return true;
	}
	public static boolean setAccessblity(String email,boolean val) throws ServiceException, ValidatorInitializationException {
		
		try {
			if(UserValidator.validateEmail(email)) {
				return UserDAO.makeActiveAccessblity(email,val);
			}
			
		} catch (ConnectionException | DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
		
	}
	

 
	
}