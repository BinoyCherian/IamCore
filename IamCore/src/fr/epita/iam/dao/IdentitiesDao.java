package fr.epita.iam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
	 * Initialise the database.
	 * 
	 * @param properties The properties object
	 */
	private void initialiseDatabase(Properties properties) {

		connection = DBConnection.getConnection(properties);
	}

}
