package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.ProductRequestDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.ProductRequest;
import com.fssa.charitytrust.validator.ProductRequestValidator;

 class TestContactServiceLayer {
          public ProductRequest getProductRequest() {
        	  String num="9751328806";
        			  long con=Long.parseLong(num);
        	  return new  ProductRequest("JPR","Crutches",con);
          }
          public ProductRequestService createTestRequestLayer() {
        	  ProductRequestValidator validator = new ProductRequestValidator();
        	  ProductRequestDao dao = new  ProductRequestDao();
              return new ProductRequestService(validator, dao);
          }
          @Test
          void testAddRequest() throws ValidatorInitializationException, SQLException, DaoException,
                  IllegalArgumentException, ConnectionException {
        	  ProductRequest request = getProductRequest();
        	  ProductRequestService ProductRequestService = createTestRequestLayer();
              Assertions.assertTrue(ProductRequestService.addproductRequest(request));
          }
          @Test
          void testAddNullRequest() throws DaoException, IllegalArgumentException, ValidatorInitializationException,
                  SQLException, ConnectionException {
        	  ProductRequest productRequest = null;
        	  ProductRequestService requestServiceLayer = createTestRequestLayer();
              try {
            	  requestServiceLayer.addproductRequest(productRequest);
              } catch (DaoException e) {
                  Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
              }
          }
          @Test

      	void testServiceAddInvalidrequest() throws ValidatorInitializationException, SQLException, DaoException, ConnectionException
      			 {
        	  ProductRequest productRequest = new ProductRequest("12345", "Magnifier",9751329805l);
        	  ProductRequestService requestServiceLayer = createTestRequestLayer();

      		Assertions.assertFalse(requestServiceLayer.addproductRequest(productRequest));

      	}
          @Test
          void testUpdateRequest() throws IllegalArgumentException, ValidatorInitializationException, SQLException,
                  DaoException, ConnectionException {
             
        	  ProductRequest productRequest = new ProductRequest("chepaks", "Magnifier",9751329805l);
        	  ProductRequestService productServiceLayer = createTestRequestLayer();
        	  productServiceLayer.addproductRequest(productRequest);
              Assertions.assertTrue(productServiceLayer.updateProductRequest(9751329805l,"Accepted"));
          }

  }
