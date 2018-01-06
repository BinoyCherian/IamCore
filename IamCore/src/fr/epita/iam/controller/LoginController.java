package fr.epita.iam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.datamodel.Login;
import fr.epita.iam.service.LoginService;

/**
 * Enterprise data object representing the controller class
 * 
 * @author raaool
 *
 */
public class LoginController extends HttpServlet{

	/** 
	 * The doGet method
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if ((req.getRequestURL() + "").contains(Constants.LOGIN)) {

			Login loginRequest = new Login();
			loginRequest.setEmail(req.getParameter(Constants.EMAIL));
			loginRequest.setPassword(req.getParameter(Constants.LOGIN_PASSWORD));

			LoginService loginService = new LoginService();
			Boolean loginStatus = loginService.loginservice(loginRequest);

			if (loginStatus) {
				System.out.println("Admin, Send to a new page with more rights");
			} else {
				// deny access
			}

		}
	}
}