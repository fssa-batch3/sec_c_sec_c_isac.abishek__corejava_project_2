package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.ProductDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Product;
import com.fssa.charitytrust.validator.ProductValidator;

/**
 * The service layer class that acts as an intermediary between the
 * application's business logic and data access layer.
 */
public class ProductServiceLayer {

	// Instance variables for HospitalValidator and HospitalDao
	ProductValidator productValidator;
	ProductDao productDao;

	// Constructor to initialize the service layer with HospitalValidator and
	// HospitalDao instances.
	public ProductServiceLayer(ProductValidator productValidator, ProductDao productDao) {
		this.productValidator = productValidator;
		this.productDao = productDao;
	}

	/**
	 * Adds a new Product to the system if it passes validation checks.
	 *
	 * @param Product The Product object to be added.
	 * @return true if the Product is successfully added, false otherwise.
	 * @throws IllegalArgumentException         if the Product object is null.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 */
	public boolean addProduct(Product product)
			throws ServiceException {

	
		try {
			if (ProductValidator.validate(product)) {
				return ProductDao.addProduct(product);
			} else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates an existing hospital in the system if it passes validation checks.
	 *
	 * @param hospital The updated hospital object.
	 * @return true if the hospital is successfully updated, false otherwise.
	 * @throws IllegalArgumentException         if the hospital object is null.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 */
	public boolean updateProduct(Product product)
			throws ServiceException {

		if (product == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (ProductValidator.validate(product)) {
				return ProductDao.update(product);
			} else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retrieves a list of all hospitals from the system.
	 *
	 * @return An ArrayList containing all hospitals in the system.
	 * @throws IllegalArgumentException         if there is an issue with
	 *                                          initializing the validator.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 */
	public List<Product> readProduct() throws ServiceException {
		try {
			return ProductDao.readFullProductList();
		} catch (SQLException | ConnectionException e) {
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
	 * @throws SQLException                            if there is an issue with the
	 *                                                 SQL database operation.
	 * @throws DaoException                            if there is an issue with the
	 *                                                 data access layer.
	 * @throws ValidatorInitializationException        if there is an issue with
	 *                                                 initializing the validator.
	 * @throws com.fssa.charitytrust.exceptions.DaoException
	 * @throws ConnectionException 
	 */
	public boolean deleteProduct(String name,int eventId) throws ServiceException {
		if (name == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (ProductValidator.validateProductName(name)) {
				return ProductDao.deleteProduct(name,eventId);
			} else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Finds a hospital in the system by its name.
	 *
	 * @param name The name of the hospital to be found.
	 * @return The Hospital object if found, null otherwise.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 */
	public Product findByNameProduct(String name) throws ServiceException {
		if (name == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (ProductValidator.validateProductName(name)) {
				return ProductDao.findProductByName(name);
			} else {
				return null;
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean readProductByEvent() throws ServiceException {
 
		try {
			return ProductDao.viewProductByEvents();
		} catch (SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<ArrayList<String>> readProductBySpecificEvent(int eventId)
			throws ServiceException {

		try {
			if (ProductValidator.validateEventId(eventId)) {
				return ProductDao.viewProductBySpecificEvents(eventId);
			} else {
				return Collections.emptyList();
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e.getMessage());
		}
		

	}
}
