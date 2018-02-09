package fr.epita.iam.service;

import fr.epita.iam.dao.LoginDao;
import fr.epita.iam.dao.LoginInterface;
import fr.epita.iam.datamodel.Login;

/**
 * The service class for the Login feature which will also contain
 * business logic if required.
 * 
 * @author raaool
 *
 */
public class LoginService {
	
	private LoginInterface login;
	
	/**
	 * Login service Controller
	 */
	public LoginService() {
		login=new LoginDao();
	}
	
	public boolean loginservice(Login loginRequest) {
		return login.checkLogin(loginRequest);
	}
}
