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
	public static final String INSERT_IDENTITY="INSERT INTO IDENTITY VALUES (?,?,?)";
	
	/** Prepared statement to delete from IDENTITIES*/
	public static final String DELETE_IDENTITY="DELETE FROM IDENTITIES WHERE UID=?";

}
