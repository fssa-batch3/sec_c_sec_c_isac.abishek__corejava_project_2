package com.fssa.charitytrust.connection;

/*
 * This file is for getting connection form the data base
 *  */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.charitytrust.logger.Logger;

public class ConnectionUtil {
	private ConnectionUtil() {
		
		
		
	}   
	public static Connection getConnection() throws ConnectionException, SQLException  {

		Connection con = null;

		String url;
		String userName;
		String passWord;
		
//		url = System.getenv("DATABASE_HOST");
//		userName = System.getenv("DATABASE_USERNAME");
//		passWord = System.getenv("DATABASE_PASSWORD");

//		url =System.getenv("LOCAL_URL");
//		userName = System.getenv("LOCAL_USERNAME");
//		passWord = System.getenv("LOCAL_PASSWORD");
		
		
		url="jdbc:mysql://localhost:3306/charitytrust";
		userName="root";
		passWord="123456";
		

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			Logger.info("Connection success");
		} catch (SQLException e) {
			throw new ConnectionException(ConectionError.CONNECTION_ERROR);
		}
		catch (Exception e) {
			throw new RuntimeException("unable to get connection");
		}
		return con;

	}


}
