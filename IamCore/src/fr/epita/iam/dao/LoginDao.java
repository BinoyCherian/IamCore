package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.constants.SqlConstants;
import fr.epita.iam.datamodel.Login;
import fr.epita.iam.service.DBConnection;
import fr.epita.logger.Logger;

/**
 * Enterprise data object class to contain database related operations for the login.
 * 
 * @author raaool
 *
 */
public class LoginDao implements LoginDAOInterface{
	
	/** The connection object */
	Connection connection;
	
	/** The prepared statement */
	PreparedStatement preparedStatement;
	
	/** The logger. */
	private static final Logger logger = new Logger(DBConnection.class);

	
	/**
	 * Constructor
	 */
	public LoginDao() {
		connection = DBConnection.getConnection();
		//connection = DBConnection.getUltimateConnection();
	}
	

	/**
	 * Method to check the login credentials.
	 * 
	 * @param loginRequest Request containing the login credentials
	 * @return boolean TRUE or FALSE
	 */
	public boolean checkLogin(Login loginRequest) {
		
		ResultSet resultSet=null;
		
		try {
			preparedStatement=connection.prepareStatement(SqlConstants.CHECK_LOGIN);
			
			// The username can be case-insensitive but password cannot be tolerated
			preparedStatement.setString(1, loginRequest.getEmail().toUpperCase());
			preparedStatement.setString(2, loginRequest.getPassword());
			
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				logger.error("Admin found");
				return true;
			}
			else {
				logger.error("Login issue");
			}
			
		} catch (SQLException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		finally {
			try {
				if (resultSet != null)
					resultSet.close();

				if (preparedStatement != null) {
					preparedStatement.close();
				}

				connection.close();

			} catch (SQLException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		}
		
		return false;
	}

	public boolean createAdmin(Login loginRequest) {

		try {
			preparedStatement = connection.prepareStatement(SqlConstants.CREATE_ADMIN);
			// The username can be case-insensitive but password cannot be tolerated
			preparedStatement.setString(1, loginRequest.getEmail().toUpperCase());
			preparedStatement.setString(2, loginRequest.getPassword());

			int executed = preparedStatement.executeUpdate();

			if (executed > 0)
				return true;

		} catch (SQLException e) {
			logger.error(Constants.EXCEPTION, e);
		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				connection.close();
			} catch (SQLException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		}
		return false;
	}
}
