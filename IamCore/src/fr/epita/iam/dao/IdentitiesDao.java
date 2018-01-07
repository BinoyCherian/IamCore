package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.derby.client.am.SqlException;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.constants.DBProperties;
import fr.epita.iam.constants.SqlConstants;
import fr.epita.iam.datamodel.Identity;

/**
 * Enterprise data access object to handle database operations for the identities.
 * 
 * @author raaool
 *
 */
public class IdentitiesDao {
	
	/** The connection object */
	Connection connection;
	
	/** The prepared statement */
	PreparedStatement preparedStatement;
	
	
	/**
	 * Constructor
	 */
	public IdentitiesDao() {
			initialiseDatabase(DBProperties.initialiseProperties());
	}
	
	
	/**
	 * Method to add identity to the database table IDENTITIES
	 * 
	 * @param identity The identity object
	 * @return boolean TRUE OR FALSE
	 */
	public boolean addIdentity(Identity identity) {
		//TODO test method later.
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_IDENTITY);

				// TODO table has four coloumns and inserting into id is causing issues.
				preparedStatement.setString(1, identity.getDisplayName());
				preparedStatement.setString(2, identity.getEmail());
				preparedStatement.setString(3, identity.getUid());

				int executed = preparedStatement.executeUpdate();

				if (executed > 0) {
					System.out.println("Executed");
					return true;
				} else {
					System.out.println("Failure");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * Method to delete an identity from the table IDENTITIES
	 * 
	 * @param identity The Identity object
	 * @return boolean TRUE OR FALSE
	 */
	public boolean deleteIdentity(Identity identity) {
		//TODO test method later.
		if (connection != null) {

			try {
				preparedStatement=connection.prepareStatement(SqlConstants.DELETE_IDENTITY);
				preparedStatement.setString(1, identity.getUid());
				int executed=preparedStatement.executeUpdate();
				
				if(executed>0) {
					System.out.println("Executed");
					return true;
				}
				else {
					System.out.println("Error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Method to update,delete and add a record into the identities table.
	 * 
	 * @param identity The identity object
	 * @param operation The operation to be performed
	 * @return boolean TRUE or FALSE
	 */
	public boolean updateDeleteAndInsert(Identity identity, String operation) {
		
		//TODO need to test

		int executed = 0;
		try {
			if (operation != null && connection != null) {
				if (operation.equalsIgnoreCase(Constants.UPDATE_OPERATION)) {

					preparedStatement = connection.prepareStatement(SqlConstants.UPDATE_IDENTITY);
					preparedStatement.setString(1, identity.getDisplayName());
					preparedStatement.setString(2, identity.getEmail());

				} else if (operation.equalsIgnoreCase(Constants.CREATE_OPERATION)) {

					preparedStatement = connection.prepareStatement(SqlConstants.INSERT_IDENTITY);
					preparedStatement.setString(1, identity.getDisplayName());
					preparedStatement.setString(2, identity.getEmail());
					
				} else if(operation.equalsIgnoreCase(Constants.DELETE_OPERATION)) {
					
					preparedStatement=connection.prepareStatement(SqlConstants.DELETE_IDENTITY);
					preparedStatement.setString(1, identity.getUid());
				}

				executed = preparedStatement.executeUpdate();

				if (executed > 0) {
					System.out.println("Record updated");
					return true;
				} else {
					System.out.println("Issue with operation");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * Initialise the database.
	 * 
	 * @param properties The properties object
	 */
	private void initialiseDatabase(Properties properties) {

		connection = DBConnection.getConnection(properties);
	}

}
