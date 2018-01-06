package fr.epita.iam.service;

import fr.epita.iam.dao.LoginDao;
import fr.epita.iam.datamodel.Login;

public class LoginService {
	
	private LoginDao loginDao;
	
	/**
	 * Login service Controller
	 */
	public LoginService() {
		loginDao=new LoginDao();
	}
	
	public boolean loginservice(Login loginRequest) {
		return loginDao.checkLogin(loginRequest);
	}
}
