package com.fssa.charitytrust.validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.charitytrust.exceptions.HospitalValidatorErrors;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Hospital;


/**
 * The HospitalValidator class contains static methods for validating hospital-related data.
 * It performs various checks to ensure the data meets the specified criteria.
 */
public class HospitalValidator {

    /**
     * Validates the complete hospital object to ensure all properties meet the required criteria.
     *
     * @param hospital The Hospital object to be validated.
     * @return true if the hospital passes all validation checks, false otherwise.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator or other validation errors occur.
     */
    public static boolean validate(Hospital hospital) throws ValidatorInitializationException {
        if (hospital == null) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_HOSPITAL_NULL);
        }
        try {
            validateHospitalName(hospital.getHospitalName());
            validateDoctorName(hospital.getDoctorName());
            
            validateContactNo(hospital.getContactNumber());
            validateAddress(hospital.getHospitalAddress());
            isValidType(hospital.getCategory());
            validateURL(hospital.getImageUrl());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the hospital name to ensure it contains only alphabetic characters or spaces.
     *
     * @param hospitalName The name of the hospital to be validated.
     * @return true if the hospital name is valid, false otherwise.
     * @throws ValidatorInitializationException if the hospital name is null or does not meet the required format.
     */
    public static boolean validateHospitalName(String hospitalName) throws ValidatorInitializationException {
        if (hospitalName == null) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_HOSPITAL_NULL);
        }
        String regex = "^[A-Za-z]+(?: [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hospitalName);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_HOSPITAL_NAME);
        }
    }

    /**
     * Validates the doctor's name to ensure it contains only alphabetic characters or spaces.
     *
     * @param DoctorName The name of the doctor to be validated.
     * @return true if the doctor's name is valid, false otherwise.
     * @throws ValidatorInitializationException if the doctor's name is null or does not meet the required format.
     */
    public static boolean validateDoctorName(String DoctorName) throws ValidatorInitializationException {
        if (DoctorName == null || DoctorName.isEmpty()) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_DOCTOR_NULL);
        }
        String regex = "^[A-Za-z]+(?: [A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(DoctorName);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_DOCTOR_NAME);
        }
    }

    /**
     * Validates the hospital ID to ensure it is greater than zero.
     *
     * @param hospitalId The ID of the hospital to be validated.
     * @return true if the hospital ID is valid, false otherwise.
     * @throws ValidatorInitializationException if the hospital ID is less than or equal to zero.
     */
    public static boolean validateHospitalId(int hospitalId) throws ValidatorInitializationException {
        if (hospitalId <= 0) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_HOSPITAL_ID);
        } else {
            return true;
        }
    }

    /**
     * Validates the contact number to ensure it follows a specific pattern for Indian phone numbers.
     *
     * @param contactNo The contact number to be validated.
     * @return true if the contact number is valid, false otherwise.
     * @throws ValidatorInitializationException if the contact number is null or does not meet the required format.
     */
    public static boolean validateContactNo(String contactNo) throws ValidatorInitializationException {
        if (contactNo == null) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_CONTACTNO_NULL);
        }
        String regex = "^(\\+91|91)?[6789]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contactNo);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_CONTACT_NUMBER);
        }
    }

    /**
     * Validates the address to ensure it contains only alphanumeric characters, spaces, and certain special characters.
     *
     * @param address The address to be validated.
     * @return true if the address is valid, false otherwise.
     * @throws ValidatorInitializationException if the address is null or does not meet the required format.
     */
    public static boolean validateAddress(String address) throws ValidatorInitializationException {
        if (address == null) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_ADDRESS_NULL);
        }
        String regex = "^[a-zA-Z0-9\\s\\-\\.\\,#/]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);
        Boolean isMatch = matcher.matches();
        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_ADDRESS);
        }
    }

    /**
     * Checks if the provided type is a valid type based on the Hospital.Types enum.
     *
     * @param type The type to be validated.
     * @return true if the type is a valid type in the Hospital.Types enum, false otherwise.
     * @throws ValidatorInitializationException if the type is null, empty, or not a valid type.
     */
    public static boolean isValidType(String type) throws ValidatorInitializationException {
        if (type == null || type.trim().isEmpty()) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_SERVICE_NULL);
        }
        for (Hospital.Types validType : Hospital.Types.values()) {
            if (validType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_SERVICE);
    }

    /**
     * Validates the URL to ensure it follows a specific pattern for image URLs.
     *
     * @param URL The URL to be validated.
     * @return true if the URL is valid, false otherwise.
     * @throws ValidatorInitializationException if the URL is null or does not meet the required format.
     */
    public static boolean validateURL(String URL) throws ValidatorInitializationException {
        if (URL == null || URL.trim().isEmpty()) {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_URL_NULL);
        }

        String regex = "(?i)\\b((https?|ftp)://)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\.(jpg|jpeg|gif|png|bmp)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(URL);
        Boolean isMatch = matcher.matches();

        if (isMatch) {
            return true;
        } else {
            throw new ValidatorInitializationException(HospitalValidatorErrors.INVALID_URL);
        }
    }
}

