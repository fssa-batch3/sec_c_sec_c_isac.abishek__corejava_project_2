package com.fssa.connection.dao;

/*
 * This file is for getting connection form the data base
 *  */
import java.sql.Connection;
import java.sql.DriverManager;

import com.fssa.logger.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

	public static Connection getConnection() throws ConnectionException{
		Connection con = null;

		String url;
		String userName;
		String passWord;
		if (System.getenv("CI") != null) {
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOST");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
			Logger.info("Connection success");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			Logger.info("Connection success");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException(ConectionError.CONNECTION_ERROR);
		}
		return con;
	}



}

