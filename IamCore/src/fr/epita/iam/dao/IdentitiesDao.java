package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.constants.SqlConstants;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityException;
import fr.epita.iam.exceptions.SearchIdentityException;
import fr.epita.iam.service.DBConnection;
import fr.epita.logger.Logger;

/**
 * Enterprise data access object to handle database operations for the identities.
 * 
 * @author raaool
 *
 */
public class IdentitiesDao implements DaoInterface{
	
	/** The connection object */
	Connection connection;
	
	/** The prepared statement */
	PreparedStatement preparedStatement;
	
	/** The logger. */
	private static final Logger logger = new Logger(IdentitiesDao.class);

	
	/**
	 * Constructor
	 */
	public IdentitiesDao() {
			connection = DBConnection.getConnection();
	}
	
	
	/**
	 * Method to add identity to the database table IDENTITIES
	 * 
	 * @param identity The identity object
	 * @return boolean TRUE OR FALSE
	 * @deprecated
	 */
	@Deprecated
	public boolean addIdentity(Identity identity) {
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_IDENTITY);

				preparedStatement.setString(1, identity.getDisplayName());
				preparedStatement.setString(2, identity.getEmail());
				preparedStatement.setString(3, identity.getUid());

				int executed = preparedStatement.executeUpdate();

				if (executed > 0) {
					logger.info("Executed");
					return true;
				} else {
					logger.error("Failure");
				}

			} catch (SQLException e) {
				logger.error(Constants.EXCEPTION, e);
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
	@Deprecated
	public boolean deleteIdentity(Identity identity) {
		if (connection != null) {

			try {
				preparedStatement=connection.prepareStatement(SqlConstants.DELETE_IDENTITY);
				preparedStatement.setString(1, identity.getUid());
				int executed=preparedStatement.executeUpdate();
				
				if(executed>0) {
					logger.info("Executed");
					return true;
				}
				else {
					logger.error("Error");
				}
			} catch (SQLException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		}
		return false;
	}
	
	/**
	 * Method to update,delete and add a record into the identities table.
	 * See {@link #modifySqlString(String, Identity)} for more information.
	 * 
	 * @param identity The identity object
	 * @param operation The operation to be performed
	 * @return boolean TRUE or FALSE
	 * @throws IdentityException The exception related to update, insert or create database operation
	 */
	public boolean updateDeleteAndInsert(Identity identity, String operation) throws IdentityException{
		
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
					logger.info("Record updated");
					return true;
				} else {
					logger.error("Issue with operation");
				}
			}
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
			
		} catch (SQLException e) {
			logger.error(Constants.EXCEPTION, e);
			throw new IdentityException(e, identity, operation);
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
			modifiedSqlString=modifiedSqlString + Constants.SPACE+ SqlConstants.DISPLAY_NAME_CLAUSE+Constants.SPACE+ 
					SqlConstants.WHERE_CLAUSE+Constants.SPACE+SqlConstants.UID_CLAUSE;
		}

		if (identity.getEmail() != null && identity.getDisplayName() == null) {
			modifiedSqlString = modifiedSqlString + Constants.SPACE+ SqlConstants.EMAIL_CLAUSE+Constants.SPACE+
					SqlConstants.WHERE_CLAUSE+Constants.SPACE+SqlConstants.UID_CLAUSE;
		}
		
		if(identity.getEmail() != null && identity.getDisplayName() != null) {
			
			modifiedSqlString = modifiedSqlString + Constants.SPACE + SqlConstants.DISPLAY_NAME_CLAUSE
					+ Constants.COMMA + Constants.SPACE + SqlConstants.EMAIL_CLAUSE + Constants.SPACE
					+ SqlConstants.WHERE_CLAUSE + Constants.SPACE + SqlConstants.UID_CLAUSE;
		}
		return modifiedSqlString;
	}
	
	/**
	 * Gets the list of identities from the sql table IDENTITIES. 
	 * 
	 * @param criteria The search criteria
	 * @return List The list of identities
	 * @throws SearchIdentityException The exception related to the search operation.
	 */
	public List<Identity> getIdentities(Identity criteria) throws SearchIdentityException {
		
		List<Identity> identitiesList=null;
		Identity identityLocal=null;
		ResultSet resultSet = null;
		
		final String sqlString = "SELECT DISPLAY_NAME, EMAIL, UID FROM IDENTITIES " + "WHERE (? IS NULL OR DISPLAY_NAME LIKE ?) "
				+ "AND (? IS NULL OR EMAIL LIKE ?) " + "AND (? IS NULL OR UID = ?)";


		try {
			if (criteria == null) {
				preparedStatement = connection.prepareStatement(SqlConstants.SELECT_IDENTITY);
			} else {
				preparedStatement = connection
						.prepareStatement(sqlString);
				
				preparedStatement.setString(1, criteria.getDisplayName());
				preparedStatement.setString(2, criteria.getDisplayName() + "%");
				preparedStatement.setString(3, criteria.getEmail());
				preparedStatement.setString(4, criteria.getEmail() + "%");
				preparedStatement.setString(5, criteria.getUid());
				preparedStatement.setString(6, criteria.getUid());

			}
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet != null) {
				identitiesList=new ArrayList<>();
				
				while (resultSet.next()) {
					// assemble the object and send back the list
					identityLocal=new Identity();
					identityLocal.setDisplayName(resultSet.getString(Constants.DISPLAY_NAME_DB_COLOUMN));
					identityLocal.setEmail(resultSet.getString(Constants.EMAIL_DB_COLOUMN));
					identityLocal.setUid(resultSet.getString(Constants.UID_DB_COLOUMN));
					
					identitiesList.add(identityLocal);
				}
			}else {
				logger.info("No details found for identity: "+criteria);
			}
		} catch (SQLException e) {
			logger.error(Constants.EXCEPTION, e);
			
			throw new SearchIdentityException(e, criteria);
		}
		finally {
			try {
				if(resultSet != null)
				resultSet.close();
			} catch (SQLException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		}
		return identitiesList;
	}
}
