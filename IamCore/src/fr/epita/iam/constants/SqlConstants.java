package fr.epita.iam.constants;

/**
 * Enterprise class holding all the sql constants.
 * 
 * @author raaool
 *
 */
public class SqlConstants {
	
	/** Prepared statement to count login accounts*/
	public static final String CHECK_LOGIN="SELECT * FROM LOGIN WHERE LOGIN_EMAIL =? AND PASSWORD=?";
	
	/** Prepared statement to insert into IDENTITIES*/
	public static final String INSERT_IDENTITY="INSERT INTO IDENTITIES(DISPLAY_NAME,EMAIL,UID) VALUES (?,?,?)";
	
	/** Prepared statement to delete from IDENTITIES*/
	public static final String DELETE_IDENTITY="DELETE FROM IDENTITIES WHERE UID=?";
	
	/** Prepared statement to update in IDENTITIES*/
	public static final String UPDATE_IDENTITY="UPDATE IDENTITIES SET";
	
	/** The constant for the display name to be used in the update scenario */
	public static final String DISPLAY_NAME_CLAUSE="DISPLAY_NAME=?";
	
	/** The constant for the email to be used in the update scenario */
	public static final String EMAIL_CLAUSE="EMAIL=?";
	
	/** The constant for the WHERE CLAUSE*/
	public static final String WHERE_CLAUSE = "WHERE";

	/** The constant for the uid to be used in the update scenario */
	public static final String UID_CLAUSE="UID=?";
	
	/** The sql query to select rows from the IDENTITIES table */
	public static final String SELECT_IDENTITY="select * from IDENTITIES";
	
	/** The constant for the AND CLAUSE*/
	public static final String AND_CLAUSE = "AND";

}
