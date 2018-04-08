package fr.epita.iam.dao;

import fr.epita.iam.datamodel.Login;

/**
 * The interface for login based operations.
 * 
 * @author raaool
 *
 */
public interface LoginDAOInterface {
	
	/**
	 * The operation to check the login.
	 * 
	 * @param loginRequest The login request
	 * @return TRUE/FALSE
	 */
	boolean checkLogin(Login loginRequest);
	
	/**
	 * The operation to create a admin.
	 * 
	 * @param loginRequest The login request
	 * @return TRUE/FALSE
	 */
	boolean createAdmin(Login loginRequest);

}
