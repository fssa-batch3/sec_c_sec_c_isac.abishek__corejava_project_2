package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.ProductRequestDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.EventValidatorErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.ProductRequest;
import com.fssa.charitytrust.validator.ProductRequestValidator;

 class TestContactServiceLayer {
          public ProductRequest getProductRequest() {
        	  String num="9751328806";
        			
        	  return new  ProductRequest("JPR","Crutches",num);
          }
          public ProductRequestService createTestRequestLayer() {
        	  ProductRequestValidator validator = new ProductRequestValidator();
        	  ProductRequestDao dao = new  ProductRequestDao();
              return new ProductRequestService(validator, dao);
          }
          @Test
          void testAddRequest() throws  ServiceException {
        	  ProductRequest request = getProductRequest();
        	  ProductRequestService ProductRequestService = createTestRequestLayer();
              Assertions.assertTrue(ProductRequestService.addproductRequest(request));
          }
          @Test
          void testAddNullRequest() throws  ServiceException {
        	  ProductRequest productRequest = null;
        	  ProductRequestService requestServiceLayer = createTestRequestLayer();
              try {
            	  requestServiceLayer.addproductRequest(productRequest);
              } catch (ServiceException e) {
                  Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
              }
          }
          @Test

      	void testServiceAddInvalidrequest() throws ServiceException    	 {
        	  ProductRequest productRequest = new ProductRequest("12345", "Magnifier","9751329805");
        	  ProductRequestService requestServiceLayer = createTestRequestLayer();
              try {
            	  requestServiceLayer.addproductRequest(productRequest);
              }
              catch(Exception e) {
            	  Assertions.assertEquals(EventValidatorErrors.INVALID_EVENT_NAME, e.getMessage());
              }
      		

      	}
          @Test
          void testUpdateRequest() throws ServiceException {
             
        	  ProductRequest productRequest = new ProductRequest("chepaks", "Magnifier","9751329805");
        	  ProductRequestService productServiceLayer = createTestRequestLayer();
        	  productServiceLayer.addproductRequest(productRequest);
              Assertions.assertTrue(productServiceLayer.updateProductRequest("9751329805","Accepted"));
          }

  }
