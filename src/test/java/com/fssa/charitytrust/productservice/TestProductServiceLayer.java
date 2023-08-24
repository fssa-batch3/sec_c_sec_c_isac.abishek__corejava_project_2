package com.fssa.charitytrust.productservice;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.productdao.ProductDao;
import com.fssa.charitytrust.productexception.DaoException;
import com.fssa.charitytrust.productexception.DaoExceptionErrors;
import com.fssa.charitytrust.productexception.ProductValidateErrors;
import com.fssa.charitytrust.productexception.ValidatorIntializationException;
import com.fssa.charitytrust.productmodel.Product;
import com.fssa.charitytrust.productservice.ProductServiceLayer;
import com.fssa.charitytrust.productvalidator.ProductValidator;

class TestProductServiceLayer {

	public Product getProduct() {
		Product Product = new Product("Wheel Chair", "Isi mark aWheelChair", "https://freeimage.host/i/HNRzLYJ.jpg", 3);

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
		Product Productnew = new Product("Tricycle", "ISI mark Stick", "https://freeimage.host/i/HNRzLYJ.jpg", 2);
		Product updateProduct = new Product("Tricycle", "ISI mark Tricycle", "https://freeimage.host/i/HNRzLYJ.jpg", 2);
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
		Product Product1 = new Product("cluster", "ISI mark Tricycler", "https://freeimage.host/i/HNRzLYJ.jpg", 3);
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertNotEquals(null, ProductServiceLayer1.readProduct());

	}

	@Test
	void deleteObject() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		Assertions.assertTrue(ProductServiceLayer1.deleteProduct("cluster"));

	}

	@Test
	void deleteObjectNull() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct(null);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}

	@Test
	void deleteObjectInvalid() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct("InvalidName");
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.ROW_AFFECTED, e.getMessage());
		}

	}

	@Test
	void testFindByName() throws SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		Product Product1 = new Product("Laser", "ISI mark laser", "https://freeimage.host/i/HNRzLYJ.jpg", 2);

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertEquals(ProductServiceLayer1.findByNameProduct(Product1.getProductName()), Product1);
	}
	//test by specific event
	@Test
	void testProductByEvent() throws SQLException, DaoException, ValidatorIntializationException, IllegalArgumentException, ConnectionException {
		Product Product1 = new Product("arms", "ISI mark arms", "https://freeimage.host/i/HNRzLYJ.jpg", 2);

		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
//		ProductServiceLayer1.addProduct(Product1);
		Product1 = new Product("Plastic legs", "ISI mark plastic legs", "https://freeimage.host/i/HNRzLYJ.jpg", 4);
//		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertTrue(ProductServiceLayer1.readProductByEvent());
	}
    //test by specific event
	@Test
	void testProductBySpecificEvent()
			throws IllegalArgumentException, SQLException, DaoException, ValidatorIntializationException, ConnectionException {
		final int eventId = 2;
		ProductServiceLayer ProductServiceLayer1 = getProductLayer();
		Assertions.assertTrue(ProductServiceLayer1.readProductBySpecificEvent(eventId));
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
		ProductServiceLayer hospitalServiceLayer = getProductLayer();
		try {
			hospitalServiceLayer.findByNameProduct(Product1);
		} catch (DaoException e) {
			Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
		}

	}

}
