package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.epita.iam.constants.Constants;

/**
 * Enterprise data object for the database connection
 * 
 * @author raaool
 *
 */
public class DBConnection {
	
	/** The connection object*/
	static Connection con;
	
	/**
	 * Method to create and return a database connection
	 * 
	 * @param properties The properties object
	 * @return Connection The connection object
	 */
	public static Connection getConnection(Properties properties){
		try {
			try {
				Class.forName(properties.getProperty(Constants.DRIVER)).newInstance();
				con=DriverManager.getConnection(properties.getProperty(Constants.DB_URL), properties.getProperty(Constants.DB_USER), 
						properties.getProperty(Constants.DB_PASSWORD));
				
				if(con==null)
				{
					System.out.println("Connection error");
				}
				
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
