package com.fssa.charitytrust.productservice;

import java.sql.SQLException;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.productdao.ProductDao;
import com.fssa.charitytrust.productexception.DaoException;
import com.fssa.charitytrust.productexception.DaoExceptionErrors;
import com.fssa.charitytrust.productexception.ValidatorIntializationException;
import com.fssa.charitytrust.productmodel.Product;
import com.fssa.charitytrust.productvalidator.ProductValidator;

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
			throws  SQLException, DaoException, ValidatorIntializationException, ConnectionException {

		if (product == null) {
			throw new DaoException(DaoExceptionErrors.INVALID_INPUT);
		}
		if (ProductValidator.validate(product)) {
			return ProductDao.addProduct(product);
		} else {
			return false;
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
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {

		if (product == null) {
			throw new  DaoException(DaoExceptionErrors.INVALID_INPUT);
		}
		if (ProductValidator.validate(product)) {
			return ProductDao.update(product);
		} else {
			return false;
		}
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
	public List<Product> readProduct() throws IllegalArgumentException, SQLException, DaoException, ConnectionException {
		return ProductDao.readFullProductList();
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
	 * @throws com.fssa.charitytrust.productexception.DaoException
	 * @throws ConnectionException 
	 */
	public boolean deleteProduct(String name) throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		if (name == null) {
			throw new  DaoException(DaoExceptionErrors.INVALID_INPUT);
		}
		if (ProductValidator.validateProductName(name)) {
			return ProductDao.deleteProduct(name);
		} else {
			return false;
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
	public Product findByNameProduct(String name) throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		if (name == null) {
			throw new  DaoException(DaoExceptionErrors.INVALID_INPUT);
		}
		if (ProductValidator.validateProductName(name)) {
			return ProductDao.findProductByName(name);
		} else {
			return null;
		}
	}

	public boolean readProductByEvent() throws IllegalArgumentException, SQLException,  ConnectionException {

		return ProductDao.viewProductByEvents();
	}

	public boolean readProductBySpecificEvent(int eventId)
			throws IllegalArgumentException, SQLException, ValidatorIntializationException,  ConnectionException {

		if (ProductValidator.validateEventId(eventId)) {
			return ProductDao.viewProductBySpecificEvents(eventId);
		} else {
			return false;
		}

	}
}
