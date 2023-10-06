package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.HospitalDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Hospital;
import com.fssa.charitytrust.validator.HospitalValidator;



/**
 * The service layer class that acts as an intermediary between the application's business logic and data access layer.
 */
public class hospitalServiceLayer {

    // Instance variables for HospitalValidator and HospitalDao
    public HospitalValidator hospitalValidator;
    public HospitalDao hospitalDao;

    // Constructor to initialize the service layer with HospitalValidator and HospitalDao instances.
    public hospitalServiceLayer(HospitalValidator hospitalValidator, HospitalDao hospitalDao) {
        this.hospitalValidator = hospitalValidator;
        this.hospitalDao = hospitalDao;
    }

    /**
     * Adds a new hospital to the system if it passes validation checks.
     *
     * @param hospital The hospital object to be added.
     * @return true if the hospital is successfully added, false otherwise.
     * @throws IllegalArgumentException      if the hospital object is null.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ConnectionException 
     */
    public static boolean addHospital(Hospital hospital)
            throws ServiceException {
        System.out.println(hospital);
        if (hospital == null) {
            throw new ServiceException(DaoExceptionErrors.INVALID_INPUT);
        }
        try {
			if (HospitalValidator.validate(hospital)) {
			    return HospitalDao.addHospital(hospital);
			} else {
			    return false;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }

    /**
     * Updates an existing hospital in the system if it passes validation checks.
     *
     * @param hospital The updated hospital object.
     * @return true if the hospital is successfully updated, false otherwise.
     * @throws IllegalArgumentException      if the hospital object is null.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ConnectionException 
     */
    public boolean updateHospital(Hospital hospital)
            throws ServiceException {
        System.out.println(hospital);
        if (hospital == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        try {
			if (HospitalValidator.validate(hospital)) {
			    return HospitalDao.update(hospital);
			} else {
			    return false;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }

    /**
     * Retrieves a list of all hospitals from the system.
     *
     * @return An ArrayList containing all hospitals in the system.
     * @throws IllegalArgumentException      if there is an issue with initializing the validator.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ConnectionException 
     */
    public ArrayList ReadHospital()
            throws ServiceException {
        try {
			return HospitalDao.readFullHospitalList();
		} catch (SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    /**
     * Deletes a hospital from the system by its name.
     *
     * @param name The name of the hospital to be deleted.
     * @return true if the hospital is successfully deleted, false otherwise.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws com.fssa.product.exception.DaoException 
     * @throws ConnectionException 
     */
    public boolean deleteHospital(String name) throws ServiceException {
        if (name == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        try {
			if (HospitalValidator.validateHospitalName(name)) {
			    try {
					return HospitalDao.deleteHospital(name);
				} catch (SQLException | DaoException | ConnectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
			    return false;
			}
		} catch (ValidatorInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }

    /**
     * Finds a hospital in the system by its name.
     *
     * @param name The name of the hospital to be found.
     * @return The Hospital object if found, null otherwise.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws ConnectionException 
     */
    public Hospital FindByNameHospital(String name)
            throws ServiceException {
        if (name == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        try {
			if (HospitalValidator.validateHospitalName(name)) {
			    return HospitalDao.findHospitalByName(name);
			} else {
			    return null;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
    }

   
}
