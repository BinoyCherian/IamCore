package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
	 * @deprecated
	 */
	public boolean addIdentity(Identity identity) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_IDENTITY);

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
	 * @deprecated
	 */
	public boolean deleteIdentity(Identity identity) {
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
		
		int executed = 0;
		try {
			if (operation != null && connection != null) {
				if (operation.equalsIgnoreCase(Constants.UPDATE_OPERATION)) {

					//need to modify the sql statement based on the content to be modified
					preparedStatement = connection.prepareStatement(modifySqlString(SqlConstants.UPDATE_IDENTITY,identity));
					
					//set only those required in the prepared statement
					preparedStatementSetString(identity);

				} else if (operation.equalsIgnoreCase(Constants.CREATE_OPERATION)) {

					preparedStatement = connection.prepareStatement(SqlConstants.INSERT_IDENTITY);
					preparedStatement.setString(1, identity.getDisplayName());
					preparedStatement.setString(2, identity.getEmail());
					preparedStatement.setString(3, identity.getUid());
					
				} else if(operation.equalsIgnoreCase(Constants.DELETE_OPERATION)) {
					
					preparedStatement=connection.prepareStatement(SqlConstants.DELETE_IDENTITY);
					preparedStatement.setString(1, identity.getUid());
				}

				//execute the sql
				executed = preparedStatement.executeUpdate();

				if (executed > 0) {
					System.out.println("Record updated");
					return true;
				} else {
					System.out.println("Issue with operation");
				}
			}
			
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * Set the string to the prepared statement based on the update scenario. 
	 * <p>This will update either the display name or either the email or both.</p>
	 * 
	 * @param identity The identity object
	 * @throws SQLException The sql exception
	 */
	public void preparedStatementSetString(Identity identity) throws SQLException {
		
		if (identity.getDisplayName() != null && identity.getEmail() == null) {
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getUid());
		}
		
		if (identity.getEmail() != null && identity.getDisplayName() == null) {
			preparedStatement.setString(1, identity.getEmail());
			preparedStatement.setString(2, identity.getUid());
		}
		
		if(identity.getDisplayName() != null && identity.getEmail() != null ) {
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
		}

		
	}

	
	/**
	 * Modify the sql string based on the null checks of the contents/properties in the identity object.
	 * 
	 * @param updateIdentity The sql string to be updated
	 * @param identity The identity object
	 * @return String The modified update sql
	 */
	private String modifySqlString(String updateIdentity,Identity identity) {
		
		String modifiedSqlString=updateIdentity;
		
		if (identity.getDisplayName() != null && identity.getEmail() == null) {
			//modifiedSqlString = modifiedSqlString + " DISPLAY_NAME=? WHERE UID=?";
			modifiedSqlString=modifiedSqlString + Constants.SPACE+ SqlConstants.UPDATE_DISPLAY_NAME_CONDITION+Constants.SPACE+ 
					SqlConstants.WHERE+Constants.SPACE+SqlConstants.UPDATE_UID_CONDITION;
		}

		if (identity.getEmail() != null && identity.getDisplayName() == null) {
			//modifiedSqlString = modifiedSqlString + "EMAIL=? WHERE UID=?";
			modifiedSqlString = modifiedSqlString + Constants.SPACE+ SqlConstants.UPDATE_EMAIL_CONDITION+Constants.SPACE+
					SqlConstants.WHERE+Constants.SPACE+SqlConstants.UPDATE_UID_CONDITION;
		}
		
		if(identity.getEmail() != null && identity.getDisplayName() != null) {
			//modifiedSqlString=modifiedSqlString +"DISPLAY_NAME=?, EMAIL=? WHERE UID=?";
			
			modifiedSqlString = modifiedSqlString + Constants.SPACE + SqlConstants.UPDATE_DISPLAY_NAME_CONDITION
					+ Constants.COMMA + Constants.SPACE + SqlConstants.UPDATE_EMAIL_CONDITION + Constants.SPACE
					+ SqlConstants.WHERE + Constants.SPACE + SqlConstants.UPDATE_UID_CONDITION;
		}
		return modifiedSqlString;
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
