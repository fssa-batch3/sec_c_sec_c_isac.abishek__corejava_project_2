package com.fssa.product.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.fssa.product.dao.ProductDao;
import com.fssa.product.exception.DaoException;
import com.fssa.product.exception.DaoExceptionErrors;
import com.fssa.product.exception.ValidatorIntializationException;
import com.fssa.product.model.Product;
import com.fssa.product.validator.ProductValidator;



public class TestProductServiceLayer {

	

	public Product getProduct() {
		Product Product = new Product( "Wheel Chair", "Isi mark aWheelChair",
				"https://freeimage.host/i/HNRzLYJ.jpg");

		return Product; 
	}  
	
	
	public ProductServiceLayer getProductLayer() {
		ProductValidator ProductValidator = new ProductValidator();
		ProductDao  ProductDao = new ProductDao();
		
		
		ProductServiceLayer ProductServiceLayer=new ProductServiceLayer(ProductValidator,ProductDao );
				return ProductServiceLayer;
	}
	
	@Test
	 @Order(1)
	   public void testServiceAdd() throws IllegalArgumentException, SQLException, DaoException, ValidatorIntializationException {
		Product Product1= getProduct();
		   
   Assertions.assertTrue(ProductServiceLayer.addProduct(Product1));
	   }
	@Test
////	 @Order(1)
	   public void testServiceAddNull() throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
		Product Product1= null;
		
		   try {
			   ProductServiceLayer.addProduct(Product1);
		   }
		   catch(Exception e) {
			   Assertions.assertEquals(e.getMessage(),DaoExceptionErrors.INVALID_INPUT);
		   }
		  
	   }
	@Test
//	 @Order(1)
	   public void testServiceAddInvalid() throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
		Product Product1=  new Product( "12345", "ISI mark wheel chair",
					"https://freeimage.host/i/HNRzLYJ.jpg");
		
		   
		   Assertions.assertFalse(ProductServiceLayer.addProduct(Product1));
		  
	   }
	@Test 
	@Order(2)
	   public void testServiceUpdate() throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
		Product Productnew = new Product( "Tricycle", "ISI mark Stick", 
				"https://freeimage.host/i/HNRzLYJ.jpg");
		Product updateProduct = new Product( "Tricycle", "ISI mark Tricycle", 
				"https://freeimage.host/i/HNRzLYJ.jpg");
		ProductServiceLayer ProductServiceLayer1=getProductLayer();
		ProductServiceLayer1.addProduct(Productnew);
		   Assertions.assertTrue(ProductServiceLayer1.updateProduct(updateProduct));
	   }
	@Test
//	 @Order(1)
	   public void testServiceupdateNull() throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
		Product Product1= null;
		ProductServiceLayer ProductServiceLayer=getProductLayer();
		   try {
			   ProductServiceLayer.updateProduct(Product1);
		   }
		   catch(Exception e) {
			   Assertions.assertEquals(e.getMessage(),DaoExceptionErrors.INVALID_INPUT);
		   }
		  
	   }
	@Test
//	 @Order(1)
	   public void testServiceupdateInvalid() throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
		Product Product1=  new Product( "12345", "ISIS mark stick", 
					"https://freeimage.host/i/HNRzLYJ.jpg");
		ProductServiceLayer ProductServiceLayer=getProductLayer();
		   
		   Assertions.assertFalse(ProductServiceLayer.updateProduct(Product1));
		  
	   }
	@Test  
	
	public void testReadObject() throws DaoException, SQLException, IllegalArgumentException, ValidatorIntializationException {
		Product Product1=  new Product( "cluster", "ISI mark Tricycler",
				"https://freeimage.host/i/HNRzLYJ.jpg");
		ProductServiceLayer ProductServiceLayer1=getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertNotEquals(null, ProductServiceLayer1.ReadProduct());
		
	}
	
	@Test
	public void deleteObject() throws SQLException, DaoException, ValidatorIntializationException {
		
		ProductServiceLayer ProductServiceLayer1=getProductLayer();
		Assertions.assertTrue(ProductServiceLayer1.deleteProduct("cluster"));
		
	}
	@Test
	public void deleteObjectNull() throws SQLException, DaoException, ValidatorIntializationException {
		
		ProductServiceLayer ProductServiceLayer1=getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct(null);
		}
		catch(Exception e) {
			Assertions.assertEquals(e.getMessage(),DaoExceptionErrors.INVALID_INPUT);
		}
		
		
	}
	@Test
	public void deleteObjectInvalid() throws SQLException, DaoException, ValidatorIntializationException {
		
		ProductServiceLayer ProductServiceLayer1=getProductLayer();
		try {
			ProductServiceLayer1.deleteProduct("InvalidName");
		}
		catch(Exception e) {
			Assertions.assertEquals(e.getMessage(),DaoExceptionErrors.ROW_AFFECTED);
		}
		
		
	}
	
	@Test
	public void testFindByName() throws SQLException, DaoException, ValidatorIntializationException {
		Product Product1=  new Product( "Laser", "ISI mark laser", 
				"https://freeimage.host/i/HNRzLYJ.jpg");
		
		ProductServiceLayer ProductServiceLayer1=getProductLayer();
		ProductServiceLayer1.addProduct(Product1);
		Assertions.assertEquals(ProductServiceLayer1.FindByNameProduct(Product1.getProductName()),Product1);
	}
	
	@Test
////@Order(1)
  public void testServiceFindByNameNull() throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
	   String Product1= null;
	   ProductServiceLayer hospitalServiceLayer=getProductLayer();
	   try {
		   hospitalServiceLayer.FindByNameProduct(Product1);
	   }
	   catch(Exception e) {
		   Assertions.assertEquals(e.getMessage(),DaoExceptionErrors.INVALID_INPUT);
	   }
	  
  }

}
