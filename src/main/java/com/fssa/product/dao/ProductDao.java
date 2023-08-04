package com.fssa.product.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.Connection.dao.ConnectionUtil;
import com.fssa.product.exception.DaoException;
import com.fssa.product.exception.DaoExceptionErrors;
import com.fssa.product.model.Product;

//class for doing CRUD on Product table
public class ProductDao {

	public static boolean addProduct(Product Product) throws SQLException, DaoException {

		final String query = "INSERT INTO ProductList ( Product_name, Product_description,  Product_registerd_date, image_url )VALUES (?, ?, ?, ? );";
      // query for adding the values in table
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			PreparedStatement pst = con.prepareStatement(query); //prepare statement for query update
			//setting the values in the question mark
			pst.setString(1, Product.getProductName());
			pst.setString(2, Product.getProductDescription());
			
			pst.setDate(3, java.sql.Date.valueOf(Product.getProductRegisteredDate()));
			pst.setString(4, Product.getImageUrl());
			
			int rs=pst.executeUpdate(); //executing the query and it returns number of row affected
			if (rs == 0) {

				throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
			}
            pst.close(); //closing the prepared statement 
		}
		return true; 
	}

	public static boolean deleteProduct(String name) throws SQLException, DaoException {

		final String query = "DELETE FROM ProductList WHERE Product_name = ?";
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

	public static Product findProductByName(String name) throws SQLException, DaoException {
		final String query = "select * from ProductList where Product_name=?";
		//query for find hospital by name the value in the table
		Product result = new Product(); //object created
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			PreparedStatement pst = con.prepareStatement(query); //prepare statement for query update
			pst.setString(1, name);//setting the values in the question mark

			ResultSet rs = pst.executeQuery();  //executing the query 


			while (rs.next()) { //Setting all the values to the object
				result.setProductId(rs.getInt("Product_id"));
				result.setProductName(rs.getString("Product_name"));
				result.setProductDescription(rs.getString("Product_description"));
				result.setImageUrl(rs.getString("image_url"));
				result.setProductRegisteredDate(rs.getDate("Product_registerd_date"));

//                	}
			}
			 //connections are closed
			rs.close();
			pst.close();
		}
		return result; //returning the result object

	}

	
	public static int getId(String name) throws SQLException {
	    

   	 try (Connection con = ConnectionUtil.getConnection()) {
   			final String query = "SELECT product_id FROM ProductList WHERE product_name='" + name + "'";
   			try (Statement preparedStatement = con.createStatement()) {
   				ResultSet id = preparedStatement.executeQuery(query);
   				int id1=0;
   				while (id.next()) {
   					id1=id.getInt("product_id");
   				}
   				
   				System.out.println("Last ID: " + id1);
   				return id1; 
   			}
   	 }catch(SQLException ex) {
   		 throw new SQLException("ERROR");
   	 }
   	
   }
	
	
	public static boolean update(Product Product) throws SQLException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "UPDATE ProductList SET  Product_name = ?, Product_description = ?,image_url = ? WHERE Product_id = ?;";
			//query for update the value in the table
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, Product.getProductName());
				preparedStatement.setString(2, Product.getProductDescription());
				preparedStatement.setString(3, Product.getImageUrl());
				preparedStatement.setInt(4, getId(Product.getProductName()));
				// Execute the update
				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0;

			}

		}

	}

	public static ArrayList readFullProductList() throws SQLException, DaoException {
		try (Connection con = ConnectionUtil.getConnection()) { //getting connection
			final String query = "SELECT * FROM freshtrust.ProductList";
			ArrayList<Product> resultlist = new ArrayList();//arraylist declared
			try (PreparedStatement pst = con.prepareStatement(query)) {
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Product result = new Product();
					result.setProductId(rs.getInt("Product_id"));
					result.setProductName(rs.getString("Product_name"));
					result.setProductDescription(rs.getString("Product_description"));
					result.setImageUrl(rs.getString("image_url"));
					result.setProductRegisteredDate(rs.getDate("Product_registerd_date"));
					resultlist.add(result); //objects are pushed
				}
				rs.close(); // result set is closed
				return resultlist; //arraylist is returned
			}

		}

	}
	

}

