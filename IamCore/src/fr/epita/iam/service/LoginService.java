package fr.epita.iam.service;

import fr.epita.iam.dao.LoginDao;
import fr.epita.iam.dao.LoginDAOInterface;
import fr.epita.iam.datamodel.Login;

/**
 * The service class for the Login feature which will also contain
 * business logic if required.
 * 
 * @author raaool
 *
 */
public class LoginService {
	
	private LoginDAOInterface login;
	
	/**
	 * Login service Controller
	 */
	public LoginService() {
		login=new LoginDao();
	}
	
	public boolean loginservice(Login loginRequest) {
		return login.checkLogin(loginRequest);
	}
	
	/**
	 * The service method to create a new admin. The method will return false if the
	 * both the password's(cnfpwd and password) don't match.
	 * 
	 * @param loginRequest The login request
	 * @return TRUE/FALSE
	 */
	public boolean createLogin(Login loginRequest) {
		if (loginRequest.getConfirmPassword().equals(loginRequest.getPassword()))
			return login.createAdmin(loginRequest);
		else {
			//TODO do some logging here to indicate the password mismatch
			return false;
		}
	}
}
