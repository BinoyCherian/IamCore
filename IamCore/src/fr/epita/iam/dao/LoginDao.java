package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import fr.epita.iam.constants.DBProperties;
import fr.epita.iam.constants.SqlConstants;
import fr.epita.iam.datamodel.Login;

/**
 * Enterprise data object class to contain database related operations for the login.
 * 
 * @author raaool
 *
 */
public class LoginDao {
	
	/** The connection object */
	Connection connection;
	
	/** The prepared statement */
	PreparedStatement preparedStatement;
	
	/**
	 * Constructor
	 */
	public LoginDao() {
		initialiseDatabase(DBProperties.initialiseProperties());
	}
	
	/**
	 * Initialise the database.
	 * 
	 * @param properties The properties object
	 */
	private void initialiseDatabase(Properties properties) {
		
		connection=DBConnection.getConnection(properties);
	}

	/**
	 * Method to check the login credentials.
	 * 
	 * @param loginRequest Request containing the login credentials
	 * @return boolean TRUE or FALSE
	 */
	public boolean checkLogin(Login loginRequest) {
		
		try {
			//TODO null check on connection object
			preparedStatement=connection.prepareStatement(SqlConstants.CHECK_LOGIN);
			
			// The username can be case-insensitive but password cannot be tolerated
			preparedStatement.setString(1, loginRequest.getEmail().toUpperCase());
			preparedStatement.setString(2, loginRequest.getPassword());
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				System.out.println("Found");
				return true;
			}
			else {
				System.out.println("Login issue");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
