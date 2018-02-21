package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.constants.DBProperties;
import fr.epita.logger.Logger;

/**
 * Enterprise data object for the database connection
 * 
 * @author raaool
 *
 */
public class DBConnection {
	
	/** The connection object. */
	private static Connection con;
	
	/** The properties file. */
	private static Properties properties;
	
	/** The logger. */
	private static final Logger logger = new Logger(DBConnection.class);

	static {
		properties = DBProperties.initialiseProperties();
	}
	
	
	/**
	 * Private Constructor.
	 * 
	 */
	private DBConnection() {
		
	}
	
	/**
	 * Method to create and return a database connection
	 * 
	 * @return Connection The connection object
	 */
	public static Connection getConnection(){
		
			try {
				Class.forName(properties.getProperty(Constants.DRIVER)).newInstance();
				con=DriverManager.getConnection(properties.getProperty(Constants.DB_URL), properties.getProperty(Constants.DB_USER), 
						properties.getProperty(Constants.DB_PASSWORD));
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				logger.error(Constants.EXCEPTION, e);
			}
			
		return con;
	}
}
