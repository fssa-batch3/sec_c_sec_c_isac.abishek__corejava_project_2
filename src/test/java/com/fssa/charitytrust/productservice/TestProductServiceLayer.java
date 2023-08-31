package com.fssa.charitytrust.productservice;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.ProductDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ProductValidateErrors;
import com.fssa.charitytrust.exceptions.ValidatorIntializationException;
import com.fssa.charitytrust.model.Product;
import com.fssa.charitytrust.service.ProductServiceLayer;
import com.fssa.charitytrust.validator.ProductValidator;

class TestProductServiceLayer {

	public Product getProduct() {
		Product Product = new Product("Wheel Chair", "Isi mark WheelChair", "https://iili.io/HNRIOGt.jpg", 3);

		return Product;
	}

	public ProductServiceLayer getProductLayer() {
		ProductValidator ProductValidatorobj = ProductValidator.getProductValidator();
		
		ProductDao ProductDaoobj = ProductDao.getProductDao();
	

		ProductServiceLayer ProductServiceLayerobj = new ProductServiceLayer(ProductValidatorobj, ProductDaoobj);
		return ProductServiceLayerobj;
	}

	@Test

	void testServiceAdd() throws IllegalArgumentException, SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		Product Product1 = getProduct();
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		Assertions.assertTrue(ProductServiceLayer.addProduct(Product1));
	}

	@Test

	void testServiceAddNull()
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {
		Product Product1 = null;
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		try {
			ProductServiceLayer.addProduct(Product1);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}

	@Test

	void testServiceAddInvalid()
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {
		Product Product1 = new Product("12345", "ISI mark wheel chair", "https://freeimage.host/i/HNRzLYJ.jpg", 1);
		ProductServiceLayer ProductServiceLayer = getProductLayer();

		Assertions.assertFalse(ProductServiceLayer.addProduct(Product1));

	}

	@Test

	void testServiceUpdate()
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {
		Product Productnew = new Product("Tricycle", "ISI mark Stick", "https://iili.io/HNRIS8G.jpg", 2);
		Product updateProduct = new Product("Tricycle", "ISI mark Tricycle", "https://iili.io/HNRIS8G.jpg", 2);
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		ProductServiceLayer.addProduct(Productnew);
		Assertions.assertTrue(ProductServiceLayer.updateProduct(updateProduct));
	}

	@Test
//	 @Order(1)
	void testServiceupdateNull()
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {
		Product Product1 = null;
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		try {
			ProductServiceLayer.updateProduct(Product1);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}

	@Test
//	 @Order(1)
	void testServiceupdateInvalid()
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {
		Product Product1 = new Product("12345", "ISIS mark stick", "https://freeimage.host/i/HNRzLYJ.jpg", 1);
		ProductServiceLayer ProductServiceLayer = getProductLayer();

		Assertions.assertFalse(ProductServiceLayer.updateProduct(Product1));

	}

	@Test

	void testReadObject() throws DaoException, SQLException, IllegalArgumentException, ValidatorIntializationException, ConnectionException {
		Product Product1 = new Product("Artificial limbs", "ISI mark Artificial limbs", "https://iili.io/HNRzs2a.jpg", 3);
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertNotEquals(null, ProductServiceLayer1.readProduct());

	}

	@Test
	void deleteObject() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		Product Product1 = new Product("Artificial limbs", "ISI mark Artificial limbs", "https://iili.io/HNRzs2a.jpg", 4);
		
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertTrue(ProductServiceLayer1.deleteProduct("Artificial limbs",4));

	}

	@Test
	void deleteObjectNull() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct(null,0);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}

	@Test
	void deleteObjectInvalid() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct("InvalidName",0);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.ROW_AFFECTED, e.getMessage());
		}

	}

	@Test
	void testFindByName() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		Product Product1 = new Product("Hearing Aid", "ISI mark hearing aid", "https://iili.io/HNRzyBt.jpg", 2);

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertEquals(ProductServiceLayer1.findByNameProduct(Product1.getProductName()), Product1);
	}
	//test by specific event
	@Test
	void testProductByEvent() throws SQLException, DaoException, ValidatorIntializationException, IllegalArgumentException, ConnectionException {
		Product Product1 = new Product("stick", "ISI mark stick", "https://iili.io/HNRIM8J.jpg", 2);

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Product1 = new Product("Crutches", "ISI mark plastic Crutches", "https://iili.io/HNRI9EX.jpg", 3);
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertTrue(ProductServiceLayer1.readProductByEvent());
	}
    //test by specific event
	@Test
	void testProductBySpecificEvent()
			throws IllegalArgumentException, SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		final int eventId = 2;
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
	
		Assertions.assertNotEquals(null, ProductServiceLayer1.readProductBySpecificEvent(eventId));
	}

	@Test
	void testProductBySpecificEventInvalid()
			throws IllegalArgumentException, SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		final int eventId = 0;
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.readProductBySpecificEvent(eventId);
		} catch (ValidatorIntializationException e) {
			Assertions.assertEquals(ProductValidateErrors.INVALID_EVENT_ID, e.getMessage());
		}

	}

	@Test

	void testServiceFindByNameNull()
			throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException, ConnectionException {
		String Product1 = null;
		ProductServiceLayer ProductServiceLayer = getProductLayer();
		try {
			ProductServiceLayer.findByNameProduct(Product1);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}
     @Test
      void testinvalidfindbyname() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {
    	 String productName="invalid";
    	 ProductServiceLayer ProductServiceLayer = getProductLayer();
    	 Assertions.assertEquals(ProductServiceLayer.findByNameProduct(productName).getProductName(),null);
    	 
     }
}
