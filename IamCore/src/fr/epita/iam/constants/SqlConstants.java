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
	
	/**The constant for the display name to be used in the update scenario */
	public static final String UPDATE_DISPLAY_NAME_CONDITION="DISPLAY_NAME=?";
	
	/**The constant for the email to be used in the update scenario */
	public static final String UPDATE_EMAIL_CONDITION="EMAIL=?";
	
	/** The constant for the WHERE CLAUSE*/
	public static final String WHERE = "WHERE";

	/**The constant for the uid to be used in the update scenario */
	public static final String UPDATE_UID_CONDITION="UID=?";

}
