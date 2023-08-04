package com.fssa.Connection.dao;

import java.sql.*;
/*
 * This file is for getting connection form the data base
 *  */

public class ConnectionUtil {

	public static Connection getConnection() {
		Connection con = null; // declaring connection as null
		String url = "jdbc:mysql://localhost:3306/freshtrust"; //data Base URL
		String userName = "root";// data base user name
		String passWord = "123456"; // data base password
		try {
			con = DriverManager.getConnection(url, userName, passWord); //driver manger gets connection 
			System.out.println("connected");
		} catch (Exception e) {
			e.getMessage();
		}
		return con; //returning the connection
	}

}
