package com.fssa.charitytrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.connection.ConnectionUtil;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.logger.Logger;
import com.fssa.charitytrust.model.ProductRequest;

public class ProductRequestDao {
	public ProductRequestDao() {
		
	} 
	
	public static ProductRequestDao getrequestDao(){
		return new ProductRequestDao();
	}


	public boolean addrequest(ProductRequest productRequest) throws SQLException, DaoException, ConnectionException {

		final String query = "INSERT INTO requests (event_name, product_name, contact_number, request_registerd_date) VALUES (?, ?, ?, ?)";
		// query for adding the values in the table
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			try (PreparedStatement pst = con.prepareStatement(query)) { // prepare statement for query update
				// setting the values in the question mark

				pst.setString(1, productRequest.getEventName());
				
				pst.setString(2, productRequest.getProductName());
				pst.setLong(3, productRequest.getMobileno());
				pst.setDate(4, java.sql.Date.valueOf(productRequest.getRequestDate()));
				

				int rs = pst.executeUpdate(); // executing the query and it returns the number of rows affected
				if (rs == 0) {

					throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
				}
			} // closing the prepared statement
		}
		return true;
	}



	public ProductRequest findRequestByConatactNo(long conatactNo) throws SQLException, ConnectionException {
		final String query = "SELECT request_id,event_name,product_name,request_registerd_date,contact_number FROM requests WHERE contact_number=?";
		// query for finding an event by name in the table
		ProductRequest result = new ProductRequest(); // object created
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			try (PreparedStatement pst = con.prepareStatement(query)) { // prepare statement for query update
				pst.setLong(1, conatactNo); // setting the values in the question mark

				try (ResultSet rs = pst.executeQuery()) { // executing the query

					if (rs.next()) { // Setting all the values to the object

						
						result.setEventName(rs.getString("event_name"));
						result.setProductName(rs.getString("product_name"));
						result.setMobileno(rs.getLong("contact_number"));
						result.setRequestDate(rs.getDate("request_registerd_date"));
					}
					
					return result; // returning the result object	// connections are closed
				}
			}
			
		}
		

	}

	public static int getId(long contactNo) throws SQLException, ConnectionException {

		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT request_id FROM requests WHERE contact_number='" + contactNo + "'";
			try (Statement preparedStatement = con.createStatement()) {
				try (ResultSet id = preparedStatement.executeQuery(query)) {
					int id1 = 0;
					while (id.next()) {
						id1 = id.getInt("request_id");
					}

					
					
					return id1;
				}
			} catch (SQLException ex) {
				throw new SQLException("ERROR");
			}
		}
	}

	public boolean updateRequest(long contact,boolean active) throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {

			final String query = "UPDATE requests SET is_active = ? WHERE contact_number = ?";
			// query for updating the value in the table
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setBoolean(1, active);
				preparedStatement.setLong(2, contact);
				
				// Execute the update
				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0;

			}

		}

	}

	public List<ProductRequest> readFullRequestList() throws SQLException, ConnectionException {
		
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			final String query = "SELECT request_id,event_name,product_name,contact_number,request_registerd_date FROM requests";
			ArrayList<ProductRequest> resultlist = new ArrayList<>(); // arraylist declared
			try (PreparedStatement pst = con.prepareStatement(query)) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						ProductRequest result = new ProductRequest();
						result.setRequestId(rs.getInt("request_id"));
						result.setEventName(rs.getString("event_name"));
						result.setProductName(rs.getString("product_name"));
						result.setMobileno(rs.getLong("contact_number"));
						result.setRequestDate(rs.getDate("request_registerd_date"));
						resultlist.add(result); // objects are pushed
					}
				} // result set is closed
				return resultlist; // arraylist is returned
			}

		}

	}
	public List<ProductRequest> viewRequests() throws SQLException,  ConnectionException {
		List<ProductRequest> resultList;
		ProductRequestDao productRequestDao =new ProductRequestDao();
		resultList = productRequestDao.readFullRequestList();
		Logger.info(resultList);
		return resultList;
	}
	
	
	
	

}
