package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.ProductRequestDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.ProductRequest;
import com.fssa.charitytrust.validator.ProductRequestValidator;

public class ProductRequestService {
	private ProductRequestValidator productRequestValidator;

	public ProductRequestValidator getproductRequestValidatorValidator() {
		return productRequestValidator;
	}

	private ProductRequestDao productRequestDao;

	public ProductRequestDao getProductRequestDaoDao() {
		return productRequestDao;
	}

	// Constructor to initialize the service layer with EventValidator and EventDao
	// instances.
	public ProductRequestService(ProductRequestValidator productRequestValidator, ProductRequestDao productRequestDao) {
		this.productRequestValidator = productRequestValidator;
		this.productRequestDao = productRequestDao;
	}

	/**
	 * Adds a new ProductRequest to the system if it passes validation checks.
	 * 
	 * @param event The ProductRequest object to be added.
	 * @return true if the event is successfully added, false otherwise.
	 * @throws IllegalArgumentException         if the event object is null.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException
	 */
	public boolean addproductRequest(ProductRequest productRequest) throws ServiceException {

		if (productRequest == null) {
			throw new ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}

		
			try {
				if (productRequestValidator.validate(productRequest)) {

					return productRequestDao.addrequest(productRequest);
				}
				else {
					return false;
				}
			} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			
			throw new ServiceException(	e.getMessage());
			}
		
		
	}

	/**
	 * Updates an existing ProductRequest in the system if it passes validation
	 * checks.
	 *
	 * @param event The updated ProductRequest object.
	 * @return true if the ProductRequest is successfully updated, false otherwise.
	 * @throws IllegalArgumentException         if the event object is null.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException
	 */
	public boolean updateProductRequest(String mobile, String active) throws ServiceException {

		try {
			if (productRequestValidator.validateIsActive(active) && productRequestValidator.validateContactNo(mobile)) {
				return productRequestDao.updateRequest(mobile, active);
			} 
			else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retrieves a list of all productRequest from the system.
	 *
	 * @return An ArrayList containing all productRequest in the system.
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
	public List<ProductRequest> readRequests() throws ServiceException {
		try {
			return productRequestDao.viewRequests();
		} catch (SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Finds an event in the system by its name.
	 *
	 * @param name The name of the request to be found.
	 * @return The request object if found, null otherwise.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws ConnectionException
	 */
	public List<ProductRequest> findRequestByConatactNo(String contact)
			throws ServiceException {
		List<ProductRequest> arr = new ArrayList<>();
		try {
			if (productRequestValidator.validateContactNo(contact)) {

				arr = productRequestDao.findRequestByConatactNo(contact);
				return arr;
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

}
