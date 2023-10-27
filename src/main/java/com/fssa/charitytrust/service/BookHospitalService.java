package com.fssa.charitytrust.service;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.BookHospitalDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.BookHospital;
import com.fssa.charitytrust.validator.BookHospitalValidator;

public class BookHospitalService {
	public static boolean addBooking(BookHospital bookhospital) throws ServiceException {
		 
		
			try {
				if (BookHospitalValidator.validateBookHospital(bookhospital)) {
				     
					BookHospitalDao.addBooking(bookhospital);
 
				}
			} catch (ValidatorInitializationException | DaoException | ConnectionException e) {
				throw new ServiceException(e.getMessage());
				
			}
		 
		return true;
	}
	public List<BookHospital> readBooking(String email)
			throws ServiceException {
		
			try {
				return BookHospitalDao.getBookingByEmail(email);
			} catch (DaoException | ConnectionException e) {
				throw new ServiceException(e.getMessage());
				
			}
		
	}
}
