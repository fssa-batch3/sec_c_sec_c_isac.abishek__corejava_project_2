/**
 * 
 */
package com.fssa.charitytrust.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author IsacDevaAbishek
 *  Model object for representing the hospital in the appplication
 */
/**
 * Model object for representing the hospital in the application.
 */
public class Hospital {

    // Member variables representing hospital properties
    private int hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String doctorName;
    private String contactNumber;
    private LocalDate hospitalRegisteredDate;
    private Date hospitalRegisteredDateSQL; // This appears to be a SQL-specific date representation.
    private String imageUrl;
    private String category;

    // Enum for hospital types
    public enum Types {
        NORMALCHECKUP, OPERATION, MEDICINE
    }

    // hashCode() and equals() methods to support comparison based on hospitalName property
    @Override
    public int hashCode() {
        return Objects.hash(hospitalName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Hospital other = (Hospital) obj;
        return Objects.equals(hospitalName, other.hospitalName);
    }

    /**
     * Parameterized constructor to initialize Hospital object with provided values.
     *
     * @param hospitalId        The unique identifier for the hospital.
     * @param hospitalName      The name of the hospital.
     * @param hospitalAddress   The address of the hospital.
     * @param doctorName        The name of the doctor associated with the hospital.
     * @param contactNumber     The contact number of the hospital.
     * @param imageUrl          The URL representing the hospital's image.
     * @param category          The category of the hospital (e.g., NORMALCHECKUP, OPERATION, MEDICINE).
     */
    public Hospital( String hospitalName, String hospitalAddress, String doctorName,
                    String contactNumber, String imageUrl, String category) {
        setHospitalRegisteredDate();
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.doctorName = doctorName;
        this.contactNumber = contactNumber;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    /**
     * Default constructor for the Hospital class.
     * Sets the hospital registration date to the current date.
     */
    public Hospital() {
        setHospitalRegisteredDate();
    }

    // Getters and setters for the member variables

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDate getHospitalRegisteredDate() {
        return hospitalRegisteredDate;
    }

    /**
     * Sets the hospital registration date to the current date.
     */
    public void setHospitalRegisteredDate() {
        this.hospitalRegisteredDate = LocalDate.now();
    }

    /**
     * Sets the hospital registration date based on a given SQL Date representation.
     *
     * @param date The SQL Date representing the hospital registration date.
     */
    public void setHospitalRegisteredDate(Date date) {
        this.hospitalRegisteredDateSQL = date;
    }
}
