package com.fssa.charitytrust.exceptions;

/**
 * An interface containing constant error messages for hospital data validation.
 * These error messages can be used to identify specific issues when validating hospital-related data.
 */
public interface HospitalValidatorErrors {

    /**
     * Represents an error message for cases where the hospital is null or empty.
     */
    public static final String INVALID_HOSPITAL_NULL = "Hospital can't be null or empty";

    /**
     * Represents an error message for cases where the hospital name is invalid or empty.
     */
    public static final String INVALID_HOSPITAL_NAME = "HospitalName Invalid or empty";

    /**
     * Represents an error message for cases where the hospital ID is less than or equal to 0.
     */
    public static final String INVALID_HOSPITAL_ID = "HospitalId must be greater than 0";

    /**
     * Represents an error message for cases where the doctor name is null or empty.
     */
    public static final String INVALID_DOCTOR_NULL = "DoctorName can't be null or empty";

    /**
     * Represents an error message for cases where the doctor name is invalid.
     */
    public static final String INVALID_DOCTOR_NAME = "DoctorName Invalid";

    /**
     * Represents an error message for cases where the contact number is null or empty.
     */
    public static final String INVALID_CONTACTNO_NULL = "ContactNo can't be null or empty";

    /**
     * Represents an error message for cases where the contact number is invalid.
     */
    public static final String INVALID_CONTACT_NUMBER = "ContactNo Invalid";

    /**
     * Represents an error message for cases where the address is null or empty.
     */
    public static final String INVALID_ADDRESS_NULL = "Address can't be null or empty";

    /**
     * Represents an error message for cases where the address is invalid.
     */
    public static final String INVALID_ADDRESS = "Address Invalid";

    /**
     * Represents an error message for cases where the service is null or empty.
     */
    public static final String INVALID_SERVICE_NULL = "Service can't be null or empty";

    /**
     * Represents an error message for cases where the service is invalid.
     */
    public static final String INVALID_SERVICE = "Invalid Service";

    /**
     * Represents an error message for cases where the URL is null or empty.
     */
    public static final String INVALID_URL_NULL = "URL can't be null or empty";

    /**
     * Represents an error message for cases where the URL is invalid.
     */
    public static final String INVALID_URL = "Invalid URL";
}
