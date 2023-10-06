package com.fssa.charitytrust.validator;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.exceptions.HospitalValidatorErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Hospital;


public class TestHospitalValidator {
	Hospital hospital=new Hospital("Gem Hospital","North Street,Taramani","Naresh","9751328805","https://freeimage.host/i/HNRzLYJ.jpg","NORMALCHECKUP");
	Hospital testobj= new Hospital();
	
	
	

	@Test
	public void testHospitalValidatorobject() {
		
	try {
		HospitalValidator obj= new HospitalValidator();
	} catch (Exception e) {
		e.getMessage();
	} 
	}
	
	@Test
	public void testValidateobject() {
		
	try {
		Assertions.assertTrue(HospitalValidator.validate(hospital));
	} catch (ValidatorInitializationException e) {
		e.getMessage();
	} 
	}
	
	
	@Test
	public void testNullObject() {
		try { 
			HospitalValidator.validate(null);
		} catch (ValidatorInitializationException e) { 
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_HOSPITAL_NULL);
		}
	}
	
	@Test 
	public void testValidName() throws ValidatorInitializationException {
		testobj.setHospitalName("Gem Hospital");
		String hospitalName=testobj.getHospitalName();
		Assertions.assertTrue(HospitalValidator.validateHospitalName(hospitalName));
	}
	
	@Test  
	public void testInValidName() { 
		testobj.setHospitalName("Gem123");
		String hospitalName=testobj.getHospitalName();
		try { 
			HospitalValidator.validateHospitalName(hospitalName);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_HOSPITAL_NAME);
		}
	} 
	
	@Test  
	public void testInValidNameNull() {
		
		try { 
			HospitalValidator.validateHospitalName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_HOSPITAL_NULL);
		}
	}
	@Test   
	public void testInValidNameEmpty() {
		try { 
			HospitalValidator.validateHospitalName("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_HOSPITAL_NAME);
		}
	}
	 
	@Test 
	public void testValidDoctorName() throws ValidatorInitializationException {
		testobj.setDoctorName("Jason Manova");
		String DoctorName=testobj.getDoctorName();
		Assertions.assertTrue(HospitalValidator.validateDoctorName(DoctorName));
	}
	
	@Test  
	public void testInValidDoctorName() { 
		try { 
			HospitalValidator.validateDoctorName("jason 122");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_DOCTOR_NAME);
		}
	}
	
	@Test  
	public void testInValidDoctorNameNull() {
		try { 
			HospitalValidator.validateDoctorName(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_DOCTOR_NULL);
		}
	}
	@Test  
	public void testInValidDoctorNameEmpty() {
		try { 
			HospitalValidator.validateDoctorName(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_DOCTOR_NAME);
		}
	}
	

	
	
	@Test 
	public void testValidContactNo() throws ValidatorInitializationException {
		testobj.setContactNumber("919876543210");
		String ContactNumber=testobj.getContactNumber();
		Assertions.assertTrue(HospitalValidator.validateContactNo(ContactNumber));
	}
	
	@Test  
	public void testInValidContactNo() { 
		try { 
			HospitalValidator.validateContactNo("1239751328805");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_CONTACT_NUMBER);
		}
	}
	
	@Test  
	public void testInValidContactNoNull() {
		try { 
			HospitalValidator.validateContactNo(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_CONTACTNO_NULL);
		}
	}
	@Test  
	public void testInValidContactEmpty() {
		try { 
			HospitalValidator.validateContactNo(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_CONTACT_NUMBER);
		}
	}
	
	@Test 
	public void testValidAddress() throws ValidatorInitializationException {
		testobj.setHospitalAddress("North Street,Taramani");
		String address=testobj.getHospitalAddress();
		Assertions.assertTrue(HospitalValidator.validateAddress(address));
	}
	
	@Test  
	public void testInValidAddress() { 
		try { 
			HospitalValidator.validateAddress("Too$many%$special@characters!");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_ADDRESS);
		}
	}
	
	@Test  
	public void testInValidAddressNull() {
		try { 
			HospitalValidator.validateAddress(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_ADDRESS_NULL);
		}
	}
	@Test  
	public void testInValidAddressEmpty() {
		try { 
			HospitalValidator.validateAddress("");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_ADDRESS);
		}
	}
	@Test 
	public void testDate() throws ValidatorInitializationException {
		testobj.setHospitalRegisteredDate();
		LocalDate nowDate=testobj.getHospitalRegisteredDate();
		Assertions.assertEquals(nowDate,LocalDate.now());
	}
	
	
	@Test 
	public void testValidService() throws ValidatorInitializationException {
		testobj.setCategory("NORMALCHECKUP");
		String res=testobj.getCategory();
		Assertions.assertTrue(HospitalValidator.isValidType(res));
	}
	@Test  
	public void testInValidService() {
		try { 
			HospitalValidator.isValidType("NOTINTYPE");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_SERVICE);
		}
	}
	
	@Test  
	public void testInValidServiceEmpty() {
		try { 
			HospitalValidator.isValidType(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_SERVICE_NULL);
		}
	}
	
	@Test  
	public void testInValidServiceNull() {
		try { 
			HospitalValidator.isValidType(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_SERVICE_NULL);
		}
	}
	
	@Test 
	public void testValidURL() throws ValidatorInitializationException {
		testobj.setImageUrl("https://iili.io/H8lK1MQ.jpg"); 
		String URL=testobj.getImageUrl();
		Assertions.assertTrue(HospitalValidator.validateURL(URL));
	}
	@Test   
	public void testInValidURL() {
		try { 
			HospitalValidator.validateURL("NOTINTYPE");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_URL);
		}
	}
	
	@Test  
	public void testInValidURLEmpty() {
		try { 
			HospitalValidator.validateURL(" ");
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_URL_NULL);
		}
	}
	
	@Test  
	public void testInValidURLNull() {
		try { 
			HospitalValidator.validateURL(null);
		} catch (ValidatorInitializationException e) {
			Assertions.assertEquals(e.getMessage(), HospitalValidatorErrors.INVALID_URL_NULL);
		}
	}
	
	

	
	
} 
