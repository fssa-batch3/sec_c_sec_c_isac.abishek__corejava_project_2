package com.fssa.Connection.dao;

/*
 * This file is for getting connection form the data base
 *  */
import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {

	public static Connection getConnection() {
        Connection con = null;

        String url, userName, passWord;

        if (System.getenv("CI") != null) {
            url = System.getenv("DATABASE_HOST");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
        } else {
            Dotenv env = Dotenv.load();
            url = env.get("DATABASE_HOST");
            userName = env.get("DATABASE_USERNAME");
            passWord = env.get("DATABASE_PASSWORD");
            System.out.println("Connection success");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passWord);
            System.out.println("Connection success");
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database");
        }
        return con;
    }
	public static void main(String[] args) {
		getConnection();
	}

}
