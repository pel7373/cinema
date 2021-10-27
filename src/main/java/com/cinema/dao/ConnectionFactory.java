package com.cinema.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConnectionFactory {
	
	private static final Logger logger = LogManager.getLogger();

	private static ConnectionFactory instance = 
            new ConnectionFactory();
	
	private static final String URL_CONNECTION = getProperties(); 
	//private static final String URL_CONNECTION = "jdbc:mysql://localhost:3306/mydb?user=root&password=root";

	String driverClass = "com.mysql.cj.jdbc.Driver";
	
	 
	//private constructor
	private ConnectionFactory() {
	    try {
	        Class.forName(driverClass);
	    } catch (ClassNotFoundException e) {
	    	logger.error(this.getClass(), e);
	    	e.printStackTrace();
	    }
	}
	 
	public static ConnectionFactory getInstance()   {
	    return instance;
	}
	 
	public Connection getConnection() throws SQLException, 
	ClassNotFoundException {
	    Connection connection = 
	        DriverManager.getConnection(URL_CONNECTION);
	    return connection;
	}
	
	public static String getProperties() {
		Properties properties = new Properties();
		try (
				InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(
		                "app.properties");
				) {
			properties.load(input);
			return properties.getProperty("connection.url");
		} catch (IOException e) {
			logger.error("Can't read app.properties: " + e.getMessage());
		}
		return null;
	}
	
	public static void closeAllConnections(Connection connection, ResultSet id, PreparedStatement ps) {
		if (connection != null && id != null && ps != null) {
			try {
				id.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				logger.error("Can't close connection: " + e.getMessage());
			}
		}
	}

}
