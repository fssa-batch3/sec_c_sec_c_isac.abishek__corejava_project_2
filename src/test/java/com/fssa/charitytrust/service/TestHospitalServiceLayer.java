package com.fssa.charitytrust.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.dao.HospitalDao;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.HospitalValidatorErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.model.Hospital;
import com.fssa.charitytrust.validator.HospitalValidator;


public class TestHospitalServiceLayer {

	

	public Hospital getHospital() {
		Hospital hospital = new Hospital( "Gem Hospital", "North Street,Taramani", "Naresh", "9751328805",
				"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP");

		return hospital;
	}  
	
	
	public hospitalServiceLayer getHospitalLayer() {
		HospitalValidator hospitalValidator = new HospitalValidator();
		HospitalDao  hospitalDao = new HospitalDao();
		
		
		hospitalServiceLayer hospitalServiceLayer=new hospitalServiceLayer(hospitalValidator,hospitalDao );
				return hospitalServiceLayer;
	}
	
	@Test
	 @Order(1)
	   public void testServiceAdd() throws ServiceException {
		   Hospital hospital1= getHospital();
		   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
		   
   Assertions.assertTrue(hospitalServiceLayer.addHospital(hospital1));
	   }
	@Test
////	 @Order(1)
	   public void testServiceAddNull() throws ServiceException{
		   Hospital hospital1= null;
		   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
		   try {
			   hospitalServiceLayer.addHospital(hospital1);
		   }
		   catch(ServiceException e) {
			   Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT,e.getMessage());
		   }
		  
	   }
	@Test
//	 @Order(1)
	   public void testServiceAddInvalid() throws ServiceException {
		  Hospital hospital1=  new Hospital( "12345", "North Street,Taramani", "Naresh", "9751328805",
					"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP");
		   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
		   
		   Assertions.assertFalse(hospitalServiceLayer.addHospital(hospital1));
		  
	   }
	@Test 
	@Order(2)
	   public void testServiceUpdate() throws ServiceException {
		Hospital hospitalnew = new Hospital( "Appolo", "North Street,Taramani", "Naresh", "9751328805",
				"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP"); 
		Hospital updateHospital = new Hospital( "Appolo", "North Street,Taramani", "Mathan", "9751328805",
				"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP");
		   hospitalServiceLayer hospitalServiceLayer1=getHospitalLayer();
		   hospitalServiceLayer1.addHospital(hospitalnew);
		   Assertions.assertTrue(hospitalServiceLayer1.updateHospital(updateHospital));
	   }
	@Test
//	 @Order(1)
	   public void testServiceupdateNull() throws ServiceException {
		   Hospital hospital1= null;
		   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
		   try {
			   hospitalServiceLayer.updateHospital(hospital1);
		   }
		   catch(Exception e) {
			   Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT,e.getMessage());
		   }
		  
	   }
	@Test
//	 @Order(1)
	   public void testServiceupdateInvalid() throws ServiceException {
		  Hospital hospital1=  new Hospital( "12345", "North Street,Taramani", "Naresh", "9751328805",
					"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP");
		   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
		   
		   Assertions.assertFalse(hospitalServiceLayer.updateHospital(hospital1));
		  
	   }
	@Test  
	@Order(3)
	public void testReadObject() throws ServiceException {
		Hospital hospital1=  new Hospital( "balaji maruthuvamanai", "North Street,Taramani", "Naresh", "9751328805",
				"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP");
		hospitalServiceLayer hospitalServiceLayer1=getHospitalLayer();
		hospitalServiceLayer1.addHospital(hospital1);
		Assertions.assertNotEquals(null, hospitalServiceLayer1.ReadHospital());
		
	}
	
	@Test
	public void deleteObject() throws ServiceException {
		
		hospitalServiceLayer hospitalServiceLayer1=getHospitalLayer();
		Assertions.assertTrue(hospitalServiceLayer1.deleteHospital("balaji maruthuvamanai"));
		
	}
	@Test
	public void deleteObjectNull() throws ServiceException{
		
		hospitalServiceLayer hospitalServiceLayer1=getHospitalLayer();
		try {
			hospitalServiceLayer1.deleteHospital(null);
		}
		catch(Exception e) {
			Assertions.assertEquals(e.getMessage(),DaoExceptionErrors.INVALID_INPUT);
		}
		
		
	}
	@Test
	public void deleteObjectInvalid() throws ServiceException {
		
		hospitalServiceLayer hospitalServiceLayer1=getHospitalLayer();
		try {
			hospitalServiceLayer1.deleteHospital("InvalidName");
		}
		catch(ServiceException e) {
			Assertions.assertEquals(DaoExceptionErrors.ROW_AFFECTED,e.getMessage());
		}
		
		
	}
	
	@Test
	public void testFindByName() throws ServiceException {
		Hospital hospital1=  new Hospital( "Gokul maruthuvamanai", "North Street,Taramani", "Naresh", "9751328805",
				"https://freeimage.host/i/HNRzLYJ.jpg", "NORMALCHECKUP");
		
		hospitalServiceLayer hospitalServiceLayer1=getHospitalLayer();
		hospitalServiceLayer1.addHospital(hospital1);
		Assertions.assertEquals(hospitalServiceLayer1.FindByNameHospital(hospital1.getHospitalName()),hospital1);
	}
	
	@Test
//@Order(1)
  public void testServiceFindByNameNull() throws ServiceException {
	   String hospital1= null;
	   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
	   try {
		   hospitalServiceLayer.FindByNameHospital(hospital1);
	   }
	   catch(Exception e) {
		   Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT,e.getMessage());
	   }
	  
  }
	@Test
///@Order(1)
 public void testServiceFindByNameInvalid() throws ServiceException {
	   String hospital1= "12345hospitalname";
   hospitalServiceLayer hospitalServiceLayer=getHospitalLayer();
   try {
	   hospitalServiceLayer.FindByNameHospital(hospital1);
   }
   catch( ServiceException e) {
	   Assertions.assertEquals(HospitalValidatorErrors.INVALID_HOSPITAL_NAME,e.getMessage());
   }
	  
		   
	   
	  
  }
}
