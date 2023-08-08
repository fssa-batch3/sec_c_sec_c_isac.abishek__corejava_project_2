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

// Class for doing CRUD on the Product table
public class ProductDao {

    // Method to add a new Product record to the database
	public static boolean addProduct(Product product) throws SQLException, DaoException {
        // SQL query to insert values into the ProductList table
        final String query = "INSERT INTO ProductList (Product_name, Product_description, Product_registerd_date, image_url,event_id) VALUES (?, ?, ?, ?,?);";
        try (Connection con = ConnectionUtil.getConnection()) {
        	try(PreparedStatement pst = con.prepareStatement(query)){
        		
        	
            
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
    public static boolean deleteProduct(String name) throws SQLException, DaoException {
        final String query = "DELETE FROM ProductList WHERE Product_name = ?;";
        try (Connection con = ConnectionUtil.getConnection()) {
        	try(PreparedStatement pst = con.prepareStatement(query)){
        		
        	
            
            // Setting the values in the query using the product name
            pst.setString(1, name);

            int rowsAffected = pst.executeUpdate(); // Executing the query and getting the number of rows affected
            pst.close(); // Closing the prepared statement

            if (rowsAffected == 0) {
                throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
            }
        }
        return true;
        }
    }

    // Method to find a Product record from the database by its name
    public static Product findProductByName(String name) throws SQLException {
        final String query = "SELECT * FROM ProductList WHERE Product_name = ?;";
        Product result = new Product();
        try (Connection con = ConnectionUtil.getConnection()) {
        	try(PreparedStatement pst = con.prepareStatement(query)){
        		
        	
          
            // Setting the values in the query using the product name
            pst.setString(1, name);

            ResultSet rs = pst.executeQuery(); // Executing the query and getting the result set

            while (rs.next()) {
                // Setting all the values to the product object from the result set
                result.setProductId(rs.getInt("Product_id"));
                result.setProductName(rs.getString("Product_name"));
                result.setProductDescription(rs.getString("Product_description"));
                result.setImageUrl(rs.getString("image_url"));
                result.setProductRegisteredDate(rs.getDate("Product_registerd_date"));
            }
            rs.close(); // Closing the result set

        }
        return result;
        }
    }

    // Method to get the product ID based on the product name
    public static int getId(String name) throws SQLException {
        try (Connection con = ConnectionUtil.getConnection()) {
            final String query = "SELECT product_id FROM ProductList WHERE product_name='" + name + "';";
            try (Statement preparedStatement = con.createStatement()) {
                ResultSet id = preparedStatement.executeQuery(query);
                int id1 = 0;
                while (id.next()) {
                    id1 = id.getInt("product_id");
                }
                System.out.println("Last ID: " + id1);
                return id1;
            }
        } catch (SQLException ex) {
            throw new SQLException("ERROR");
        }
    }

    // Method to update a Product record in the database
    public static boolean update(Product product) throws SQLException {
        try (Connection con = ConnectionUtil.getConnection()) {
            final String query = "UPDATE ProductList SET Product_name = ?, Product_description = ?, image_url = ? WHERE Product_id = ?;";
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
    public static ArrayList<Product> readFullProductList() throws SQLException {
        try (Connection con = ConnectionUtil.getConnection()) {
            final String query = "SELECT * FROM ProductList";
            ArrayList<Product> resultList = new ArrayList<>();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Product result = new Product();
                    result.setProductId(rs.getInt("Product_id"));
                    result.setProductName(rs.getString("Product_name"));
                    result.setProductDescription(rs.getString("Product_description"));
                    result.setImageUrl(rs.getString("image_url"));
                    result.setProductRegisteredDate(rs.getDate("Product_registerd_date"));
                    resultList.add(result);
                }
                rs.close();
                return resultList;
            }
        }
        
        
    }
    public static ArrayList<ArrayList<String>> listProductByEvents() throws SQLException {
    	try (Connection con = ConnectionUtil.getConnection()) {
            final String query = "SELECT p.Product_name ,  e.event_name FROM EventList as e LEFT JOIN ProductList as p ON e.event_id = p.event_id;" ;
            		
            		
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                	ArrayList<String> ar= new ArrayList<String>();
                	ar.add(rs.getString("Product_name"));
                	ar.add(rs.getString("event_name"));
                	resultList.add(ar);
                    
                }

                rs.close();
                return resultList;
            }
        }
    }
    public static ArrayList<ArrayList<String>> listProductBySpecificEvents(int eventId) throws SQLException {
    	try (Connection con = ConnectionUtil.getConnection()) {
            final String query = "SELECT p.Product_name ,  e.event_name FROM EventList as e LEFT JOIN ProductList as p ON  e.event_id = p.event_id WHERE p.event_id=?" ;
            		
            		
            ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
            try (PreparedStatement pst = con.prepareStatement(query)) {
            	pst.setInt(1, eventId);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                	ArrayList<String> ar= new ArrayList<String>();
                	ar.add(rs.getString("Product_name"));
                	ar.add(rs.getString("event_name"));
                	resultList.add(ar);
                    
                }

                rs.close();
                return resultList; 
            }
        }
    }
    public static boolean viewProductByEvents() throws SQLException {
    	ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
    	resultList=listProductByEvents();
    	System.out.println(resultList);
    	return true;
    }
    public static boolean viewProductBySpecificEvents(int eventId) throws SQLException {
    	ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
    	resultList=listProductBySpecificEvents(eventId);
    	System.out.println(resultList);
    	return true;
    }
 
    
}
