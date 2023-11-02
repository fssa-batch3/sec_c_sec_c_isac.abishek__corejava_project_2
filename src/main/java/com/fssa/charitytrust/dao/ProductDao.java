package com.fssa.charitytrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.connection.ConnectionUtil;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.model.Product;

// Class for doing CRUD on the Product table
public class ProductDao {
	public ProductDao() {
 }
	public static  ProductDao getProductDao() {
		return new ProductDao();
	}
	static final String PRODUCTNAME = "Product_name";
	static final String PRODUCTDESCRIPTION = "Product_description";
	static final String IMAGEURL = "image_url";

	// Method to add a new Product record to the database
	public static boolean addProduct(Product product) throws SQLException, DaoException, ConnectionException {
		// SQL query to insert values into the products table
		final String query = "INSERT INTO products (Product_name, Product_description, Product_registerd_date, image_url,event_id) VALUES (?, ?, ?, ?,?)";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {

				// Setting the values in the query using the product object
				pst.setString(1, product.getProductName());
				pst.setString(2, product.getProductDescription());
				pst.setDate(3, java.sql.Date.valueOf(product.getProductRegisteredDate()));
				pst.setString(4, product.getImageUrl());
				pst.setInt(5, product.getEventId());
				int rowsAffected = pst.executeUpdate(); // Executing the query and getting the number of rows affected

				if (rowsAffected == 0) {
					throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
				}
			}
			return true;
		}
	}

	// Method to delete a Product record from the database based on the product name
	public static boolean deleteProduct(String name,int eventId) throws SQLException, DaoException, ConnectionException {
		final String query = "DELETE FROM products WHERE Product_name = ? and event_id=?";
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {

				// Setting the values in the query using the product name
				pst.setString(1, name);
				pst.setInt(2, eventId);
				int rowsAffected = pst.executeUpdate(); // Executing the query and getting the number of rows affected

				if (rowsAffected == 0) {
					throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
				}
			}
			return true;
		}
	}

	// Method to find a Product record from the database by its name
	public static Product findProductByName(String name) throws SQLException, ConnectionException{
		final String query = "SELECT Product_id,Product_name,Product_description,image_url,Product_registerd_date FROM products WHERE Product_name = ?";
		Product result =null;
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {

				// Setting the values in the query using the product name
				pst.setString(1, name);

				try (ResultSet rs = pst.executeQuery()) { // Executing the query and getting the result set
					result = new Product();
					if (rs.next()) {
						// Setting all the values to the product object from the result set
						result.setProductId(rs.getInt("Product_id"));
						result.setProductName(rs.getString(PRODUCTNAME));
						result.setProductDescription(rs.getString(PRODUCTDESCRIPTION));
						result.setImageUrl(rs.getString(IMAGEURL));
						result.setProductRegisteredDate(rs.getDate("Product_registerd_date"));
					}
				
					return result;
				} // Closing the result set
				
			}
			
		}
	}

	// Method to get the product ID based on the product name
	public static int getId(String name) throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT product_id FROM products WHERE product_name='" + name + "';";
			try (Statement preparedStatement = con.createStatement()) {
				try (ResultSet id = preparedStatement.executeQuery(query)) {
					int id1 = 0;
					while (id.next()) {
						id1 = id.getInt("product_id");
					}
					
					
					return id1;
				}
			}
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	// Method to update a Product record in the database
	public static boolean update(Product product) throws SQLException,  ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "UPDATE products SET Product_name = ?, Product_description = ?, image_url = ? WHERE Product_id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, product.getProductName());
				preparedStatement.setString(2, product.getProductDescription());
				preparedStatement.setString(3, product.getImageUrl());
				preparedStatement.setInt(4, getId(product.getProductName()));
				// Execute the update
				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0;
			}
		}
	}

	// Method to retrieve a list of all products from the database
	public static List<Product> readFullProductList() throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT Product_id,Product_name,Product_description,image_url,Product_registerd_date FROM products";
			ArrayList<Product> resultList = new ArrayList<>();
			try (PreparedStatement pst = con.prepareStatement(query)) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Product result = new Product();
						result.setProductId(rs.getInt("Product_id"));
						result.setProductName(rs.getString(PRODUCTNAME));
						result.setProductDescription(rs.getString(PRODUCTDESCRIPTION));
						result.setImageUrl(rs.getString(IMAGEURL));
						result.setProductRegisteredDate(rs.getDate("Product_registerd_date"));
						resultList.add(result);
					}

					return resultList;
				}
			}
		}

	}

	public static List<ArrayList<String>> listProductByEvents() throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT p.Product_name ,  e.event_name FROM events as e LEFT JOIN products as p ON e.event_id = p.event_id";

			ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
			try (PreparedStatement pst = con.prepareStatement(query)) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						ArrayList<String> ar = new ArrayList<String>();
						ar.add(rs.getString(PRODUCTNAME));
						ar.add(rs.getString("event_name"));
						resultList.add(ar);

					}
					return resultList;
				}
			}
		}
	}

	public static List<ArrayList<String>> listProductBySpecificEvents(int eventId) throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT p.Product_name , p.Product_description, p.image_url , e.event_name FROM events as e LEFT JOIN products as p ON  e.event_id = p.event_id WHERE p.event_id=?";

			ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setInt(1, eventId);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					ArrayList<String> ar = new ArrayList<String>();
					ar.add(rs.getString(PRODUCTNAME));
					ar.add(rs.getString("event_name"));
					ar.add(rs.getString(PRODUCTDESCRIPTION));
					ar.add(rs.getString(IMAGEURL));
					resultList.add(ar);

				} 

				rs.close();
				return resultList;
			}
		}
	}

	public static boolean viewProductByEvents() throws SQLException,  ConnectionException {
		List<ArrayList<String>> resultList;
		resultList = listProductByEvents();
		return true;
	}
// to view specific event
	public static List<ArrayList<String>> viewProductBySpecificEvents(int eventId) throws SQLException,  ConnectionException {
		List<ArrayList<String>> resultList;
		resultList = listProductBySpecificEvents(eventId);
		return resultList;
	}

}
