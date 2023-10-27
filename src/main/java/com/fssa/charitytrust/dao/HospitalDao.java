package com.fssa.charitytrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.connection.ConnectionUtil;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.model.Hospital;



//class for doing CRUD on hospital table
public class HospitalDao {

	public static boolean addHospital(Hospital hospital) throws SQLException, DaoException, ConnectionException {

		final String query = "INSERT INTO hospitallist ( hospital_name, hospital_address, doctor_name, contact_number, hospital_registerd_date, image_url, check_types)VALUES (?, ?, ?, ?, ?, ?, ? );";
      // query for adding the values in table
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			PreparedStatement pst = con.prepareStatement(query); //prepare statement for query update
			//setting the values in the question mark
			pst.setString(1, hospital.getHospitalName());
			pst.setString(2, hospital.getHospitalAddress());
			pst.setString(3, hospital.getDoctorName());
			pst.setString(4, hospital.getContactNumber());
			pst.setDate(5, java.sql.Date.valueOf(hospital.getHospitalRegisteredDate()));
			pst.setString(6, hospital.getImageUrl());
			pst.setString(7, hospital.getCategory().toString());
			
			int rs=pst.executeUpdate(); //executing the query and it returns number of row affected
			if (rs == 0) {

				throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
			}
            pst.close(); //closing the prepared statement 
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return true; 
	}

	public static boolean deleteHospital(String name) throws SQLException, DaoException, ConnectionException {

		final String query = "DELETE FROM hospitallist WHERE hospital_name = ?";
		//query for deleting the value in the table

		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			PreparedStatement pst = con.prepareStatement(query); //prepare statement for query update
			//setting the values in the question mark
			pst.setString(1, name);
			int rs = pst.executeUpdate();//executing the query and it returns number of row affected

			if (rs == 0) {

				throw new DaoException(DaoExceptionErrors.ROW_AFFECTED); //if no row are affected throw a exception
			}
			pst.close();
		}
		return true;
	}

	public static Hospital findHospitalByName(String name) throws SQLException, DaoException, ConnectionException {
		final String query = "select * from hospitallist where hospital_name=?";
		//query for find hospital by name the value in the table
		Hospital result = new Hospital(); //object created
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			PreparedStatement pst = con.prepareStatement(query); //prepare statement for query update
			pst.setString(1, name);//setting the values in the question mark

			ResultSet rs = pst.executeQuery();  //executing the query 


			while (rs.next()) { //Setting all the values to the object
				result.setHospitalId(rs.getInt("hospital_id"));
				result.setHospitalName(rs.getString("hospital_name"));
				result.setHospitalAddress(rs.getString("hospital_address"));
				result.setDoctorName(rs.getString("doctor_name"));
				result.setContactNumber(rs.getString("contact_number"));
				result.setImageUrl(rs.getString("image_url"));
				result.setCategory(rs.getString("check_types"));
				result.setHospitalRegisteredDate(rs.getDate("hospital_registerd_date"));

//                	}
			}
			 //connections are closed
			rs.close();
			pst.close();
		}
		return result; //returning the result object

	}
	public static int getId(String name) throws SQLException, ConnectionException {
	    
   	 try (Connection con = ConnectionUtil.getConnection()) {
   			final String query = "SELECT hospital_id FROM hospitallist WHERE hospital_name='" + name + "'";
   			try (Statement preparedStatement = con.createStatement()) {
   				ResultSet id = preparedStatement.executeQuery(query);
   				int id1=0;
   				while (id.next()) {
   					id1=id.getInt("hospital_id");
   				}
   				
   				System.out.println("Last ID: " + id1);
   				return id1; 
   			}
   	 }catch(SQLException ex) {
   		 throw new SQLException("ERROR");
   	 }
   	
   }

	public static boolean update(Hospital hospital) throws SQLException, DaoException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "UPDATE hospitallist SET  hospital_name = ?, doctor_name = ?, hospital_address = ?, contact_number = ?,image_url = ?,check_types = ? WHERE hospital_id = ?;";
			//query for update the value in the table
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, hospital.getHospitalName());
				preparedStatement.setString(2, hospital.getDoctorName());
				preparedStatement.setString(3, hospital.getHospitalAddress());
				preparedStatement.setString(4, hospital.getContactNumber());
				preparedStatement.setString(5, hospital.getImageUrl());
				preparedStatement.setString(6, hospital.getCategory());
				System.out.println(hospital.getHospitalName());
				preparedStatement.setInt(7, getId(hospital.getHospitalName()));
				// Execute the update
				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0;

			}

		}

	}

	public static ArrayList readFullHospitalList() throws SQLException, DaoException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) { //getting connection
			final String query = "SELECT * FROM hospitallist";
			ArrayList<Hospital> resultlist = new ArrayList();//arraylist declared
			try (PreparedStatement pst = con.prepareStatement(query)) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Hospital result = new Hospital();
					result.setHospitalId(rs.getInt("hospital_id"));
					result.setHospitalName(rs.getString("hospital_name"));
					result.setHospitalAddress(rs.getString("hospital_address"));
					result.setDoctorName(rs.getString("doctor_name"));
					result.setContactNumber(rs.getString("contact_number"));
					result.setImageUrl(rs.getString("image_url"));
					result.setCategory(rs.getString("check_types"));
					result.setHospitalRegisteredDate(rs.getDate("hospital_registerd_date"));
					resultlist.add(result); //objects are pushed
				}
				rs.close(); // result set is closed
				return resultlist; //arraylist is returned
			}

		}

	}
	//


}
