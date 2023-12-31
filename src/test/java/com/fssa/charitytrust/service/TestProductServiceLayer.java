package com.fssa.charitytrust.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.ProductDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Product;
import com.fssa.charitytrust.validator.ProductValidator;

class TestProductServiceLayer {

	public Product getProduct() {
		Product Product = new Product("WheelChair", "Isi mark WheelChair", "https://iili.io/HNRIOGt.jpg", 3);

		return Product;
	}

	public ProductServiceLayer getProductLayer() {
		ProductValidator ProductValidatorobj = ProductValidator.getProductValidator();
		
		ProductDao ProductDaoobj = ProductDao.getProductDao();
	

		ProductServiceLayer ProductServiceLayerobj = new ProductServiceLayer(ProductValidatorobj, ProductDaoobj);
		return ProductServiceLayerobj;
	}
	/**
	 * Test the ProductServiceLayer.addProduct method with a valid Product object.
	 *
	 * @throws IllegalArgumentException if the Product object is invalid.
	 * @throws SQLException if there is a problem with the database connection.
	 * @throws DaoException if there is a problem with the DAO.
	 * @throws ValidatorInitializationException if the validator is not initialized properly.
	 * @throws ConnectionException if there is a problem with the database connection.
	 */
	@Test

	void testServiceAdd() throws ServiceException {
		Product Product1 = getProduct();
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		Assertions.assertTrue(ProductServiceLayer.addProduct(Product1));
	}
	/**
	 * Test the ProductServiceLayer.addProduct method with a null Product object.
	 *
	 * @throws IllegalArgumentException if the Product object is invalid.
	 * @throws ValidatorInitializationException if the validator is not initialized properly.
	 * @throws SQLException if there is a problem with the database connection.
	 * @throws DaoException if there is a problem with the DAO.
	 * @throws ConnectionException if there is a problem with the database connection.
	 */
	@Test

	void testServiceAddNull()
			throws ServiceException {
		Product Product1 = null;
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		try {
			ProductServiceLayer.addProduct(Product1);
		} catch (ServiceException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_PRODUCT_NULL, e.getMessage());
		}

	}
	/**
	 * Test the ProductServiceLayer.addProduct method with an invalid Product object.
	 *
	 * @throws IllegalArgumentException if the Product object is invalid.
	 * @throws ValidatorInitializationException if the validator is not initialized properly.
	 * @throws SQLException if there is a problem with the database connection.
	 * @throws DaoException if there is a problem with the DAO.
	 * @throws ConnectionException if there is a problem with the database connection.
	 */
	@Test

	void testServiceAddInvalid()
			throws ServiceException {
		Product Product1 = new Product("12345", "ISI mark wheel chair", "https://freeimage.host/i/HNRzLYJ.jpg", 1);
		ProductServiceLayer ProductServiceLayer = getProductLayer();

		Assertions.assertFalse(ProductServiceLayer.addProduct(Product1));

	}
	/**
	 * Test the ProductServiceLayer's updateProduct method with a valid product.
	 *
	 * @throws IllegalArgumentException if the product is invalid
	 * @throws ValidatorInitializationException if the validator is not initialized
	 * @throws SQLException if there is a problem with the database
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ConnectionException if there is a problem with the connection to the database
	 * @throws ServiceException 
	 */
	@Test

	void testServiceUpdate()
			throws  ServiceException {
		Product Productnew = new Product("Tricycle", "ISI mark Stick", "https://iili.io/HNRIS8G.jpg", 2);
		Product updateProduct = new Product("Tricycle", "ISI mark Tricycle", "https://iili.io/HNRIS8G.jpg", 2);
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		ProductServiceLayer.addProduct(Productnew);
		Assertions.assertTrue(ProductServiceLayer.updateProduct(updateProduct));
	}
	/**
	 * Test the ProductServiceLayer's updateProduct method with a null product.
	 *
	 * @throws IllegalArgumentException if the product is invalid
	 * @throws ValidatorInitializationException if the validator is not initialized
	 * @throws SQLException if there is a problem with the database
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ConnectionException if there is a problem with the connection to the database
	 * @throws ServiceException 
	 */
	@Test

	void testServiceupdateNull()
			throws ServiceException {
		Product Product1 = null;
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		try {
			ProductServiceLayer.updateProduct(Product1);
		} catch (ServiceException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}
	/**
	 * Tests that the updateProduct method throws an IllegalArgumentException when passed an invalid product.
	 *
	 * @throws IllegalArgumentException if the product is invalid
	 * @throws ValidatorInitializationException if the validator is not initialized properly
	 * @throws SQLException if there is a problem with the database
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ConnectionException if there is a problem with the connection to the database
	 */
	@Test

	void testServiceupdateInvalid()
			throws ServiceException{
		Product Product1 = new Product("12345", "ISIS mark stick", "https://freeimage.host/i/HNRzLYJ.jpg", 1);
		ProductServiceLayer ProductServiceLayer = getProductLayer();

		Assertions.assertFalse(ProductServiceLayer.updateProduct(Product1));

	}
	/**
	 * Test the readProduct() method in the ProductServiceLayer class.
	 *
	 * @throws DaoException if there is a problem accessing the database
	 * @throws SQLException if there is a problem with the SQL statement
	 * @throws IllegalArgumentException if the product ID is not valid
	 * @throws ValidatorInitializationException if the validator is not initialized properly
	 * @throws ConnectionException if there is a problem connecting to the database
	 */
	@Test

	void testReadObject() throws ServiceException {
//		Product Product1 = new Product("Artificial limbs", "ISI mark Artificial limbs", "https://iili.io/HNRzs2a.jpg", 3);
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
//		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertNotEquals(null, ProductServiceLayer1.readProduct());

	}
	 /**
     * Test that a product can be deleted.
     *
     * @throws SQLException if there is a problem with the database.
     * @throws DaoException if there is a problem with the DAO.
     * @throws ValidatorInitializationException if there is a problem initializing the validator.
     * @throws ConnectionException if there is a problem with the connection.
     */
	@Test
	void deleteObject() throws ServiceException {
		Product Product1 = new Product("Artificialhands", "ISI mark Artificial limbs", "https://iili.io/HNRzs2a.jpg", 3);
		
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		
		
		
		Assertions.assertTrue(ProductServiceLayer1.deleteProduct("Artificialhands",3));

	}
	/**
     * Test that a product cannot be deleted with null values.
     *
     * @throws SQLException if there is a problem with the database.
     * @throws DaoException if there is a problem with the DAO.
     * @throws ValidatorInitializationException if there is a problem initializing the validator.
     * @throws ConnectionException if there is a problem with the connection.
     */
	@Test
	void deleteObjectNull() throws ServiceException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct(null,0);
		} catch (ServiceException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		} 

	}
	 /**
     * Test that a product cannot be deleted with invalid values.
     *
     * @throws SQLException if there is a problem with the database.
     * @throws DaoException if there is a problem with the DAO.
     * @throws ValidatorInitializationException if there is a problem initializing the validator.
     * @throws ConnectionException if there is a problem with the connection.
     */
	@Test
	void deleteObjectInvalid() throws ServiceException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct("InvalidName",0);
		} catch (ServiceException e) {
			Assertions.assertEquals(DaoExceptionErrors.ROW_AFFECTED, e.getMessage());
		}

	}
	
	//test by specific event
	/**
	 * Test the readProductByEvent method of the ProductServiceLayer.
	 *
	 * @throws SQLException if there is a problem with the database connection
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ValidatorInitializationException if there is a problem initializing the validator
	 * @throws IllegalArgumentException if the event ID is invalid
	 * @throws ConnectionException if there is a problem with the database connection
	 */
	@Test
	void testProductByEvent() throws ServiceException {
		Product Product1 = new Product("stick", "ISI mark stick", "https://iili.io/HNRIM8J.jpg", 2);

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Product1 = new Product("Crutches", "ISI mark plastic Crutches", "https://iili.io/HNRI9EX.jpg", 3);
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertTrue(ProductServiceLayer1.readProductByEvent());
	}
    //test by specific event
	/**
	 * Test the readProductBySpecificEvent method of the ProductServiceLayer.
	 *
	 * @throws SQLException if there is a problem with the database connection
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ValidatorInitializationException if there is a problem initializing the validator
	 * @throws IllegalArgumentException if the event ID is invalid
	 * @throws ConnectionException if there is a problem with the database connection
	 */
	@Test
	void testProductBySpecificEvent()
			throws ServiceException {
		final int eventId = 2;
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
	
		Assertions.assertNotEquals(null, ProductServiceLayer1.readProductBySpecificEvent(eventId));
	}
	/**
	 * Test that an exception is thrown when trying to get a product by an invalid event ID.
	 *
	 * @throws IllegalArgumentException if the event ID is not a positive integer
	 * @throws SQLException if there is a problem with the database connection
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ValidatorInitializationException if the validator cannot be initialized
	 * @throws ConnectionException if there is a problem with the database connection
	 */
	@Test
	void testProductBySpecificEventInvalid()
			throws ServiceException {
		final int eventId = 0;
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.readProductBySpecificEvent(eventId);
		} catch (ServiceException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}

	}
	/**
	 * Test that an exception is thrown when trying to find a product by a null name.
	 *
	 * @throws IllegalArgumentException if the product name is null
	 * @throws ValidatorInitializationException if the validator cannot be initialized
	 * @throws SQLException if there is a problem with the database connection
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ConnectionException if there is a problem with the database connection
	 */
	@Test

	void testServiceFindByNameNull()
			throws ServiceException {
		String Product1 = null;
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		try {
			ProductServiceLayer.findByNameProduct(Product1);
		} catch (ServiceException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}
	/**
	 * Test that an exception is thrown when trying to find a product by an invalid name.
	 *
	 * @throws SQLException if there is a problem with the database connection
	 * @throws DaoException if there is a problem with the DAO
	 * @throws ValidatorInitializationException if the validator cannot be initialized
	 * @throws ConnectionException if there is a problem with the database connection
	 */
     @Test
      void testinvalidfindbyname() throws ServiceException {
    	 String productName="invalid";
    	 ProductServiceLayer ProductServiceLayer = getProductLayer();
    	 Assertions.assertEquals(null,ProductServiceLayer.findByNameProduct(productName).getProductName());
    	 
     }
}
