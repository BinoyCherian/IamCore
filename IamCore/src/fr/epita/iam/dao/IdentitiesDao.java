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
	 * See {@link #modifySqlString(String, Identity)} for more information.
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
			modifiedSqlString=modifiedSqlString + Constants.SPACE+ SqlConstants.DISPLAY_NAME_CLAUSE+Constants.SPACE+ 
					SqlConstants.WHERE_CLAUSE+Constants.SPACE+SqlConstants.UID_CLAUSE;
		}

		if (identity.getEmail() != null && identity.getDisplayName() == null) {
			//modifiedSqlString = modifiedSqlString + "EMAIL=? WHERE UID=?";
			modifiedSqlString = modifiedSqlString + Constants.SPACE+ SqlConstants.EMAIL_CLAUSE+Constants.SPACE+
					SqlConstants.WHERE_CLAUSE+Constants.SPACE+SqlConstants.UID_CLAUSE;
		}
		
		if(identity.getEmail() != null && identity.getDisplayName() != null) {
			//modifiedSqlString=modifiedSqlString +"DISPLAY_NAME=?, EMAIL=? WHERE UID=?";
			
			modifiedSqlString = modifiedSqlString + Constants.SPACE + SqlConstants.DISPLAY_NAME_CLAUSE
					+ Constants.COMMA + Constants.SPACE + SqlConstants.EMAIL_CLAUSE + Constants.SPACE
					+ SqlConstants.WHERE_CLAUSE + Constants.SPACE + SqlConstants.UID_CLAUSE;
		}
		return modifiedSqlString;
	}
	
	/**
	 * Gets the list of identities from the sql table IDENTITIES. 
	 * The search string is based on the conditions mentioned in the {@link #modifySearchQuery(String, Identity)}.
	 * 
	 * @param identity The search criteria
	 * @return List The list of identities
	 */
	public List<Identity> getIdentities(Identity identity) {
		
		List<Identity> identitiesList=null;
		Identity identityLocal=null;

		try {
			if (identity == null) {
				preparedStatement = connection.prepareStatement(SqlConstants.SELECT_IDENTITY);
			} else {
				preparedStatement = connection
						.prepareStatement(modifySearchQuery(SqlConstants.SELECT_IDENTITY, identity));
			}

			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.getFetchSize()>0) {
				identitiesList=new ArrayList<>();
			}

			while (resultSet.next()) {
				// assemble the object and send back the list
				identityLocal=new Identity();
				//TODO fix string constant here
				identityLocal.setDisplayName(resultSet.getString("DISPLAY_NAME"));
				identityLocal.setEmail(resultSet.getString("EMAIL"));
				identityLocal.setUid(resultSet.getString("UID"));
				
				identitiesList.add(identityLocal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return identitiesList;
	}


	/**
	 * The search query is designed to search for all the identities if the identity object is passed as null.
	 * else if the object is passed it would construct the query using the and operator between all the properties
	 * passed to the identity object except the user id.
	 * 
	 * @param selectIdentity The select query 
	 * @param identity The identity object
	 * @return String The sql query
	 */
	private String modifySearchQuery(String selectIdentity, Identity identity) {
		
		//TODO Need to test

		String temp = selectIdentity;

		if (identity.getUid() != null) {

			// select * from identities where x=? and y=?
			if (identity.getDisplayName() != null && identity.getEmail() != null) {
				temp = temp + Constants.SPACE + SqlConstants.WHERE_CLAUSE + Constants.SPACE
						+ SqlConstants.DISPLAY_NAME_CLAUSE + "AND" + SqlConstants.EMAIL_CLAUSE;
			}

			if (identity.getDisplayName() != null && identity.getEmail() == null) {
				// select * from IDENTITIES where displaname=?
				temp = temp + Constants.SPACE + SqlConstants.WHERE_CLAUSE + Constants.SPACE
						+ SqlConstants.DISPLAY_NAME_CLAUSE;
			}

			if (identity.getEmail() != null && identity.getDisplayName() == null) {
				temp=temp+ Constants.SPACE + SqlConstants.WHERE_CLAUSE + Constants.SPACE
						+ SqlConstants.EMAIL_CLAUSE;
			}
		}

		return temp;
	}
}
