package fr.epita.iam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.datamodel.Login;
import fr.epita.iam.service.LoginService;
import fr.epita.logger.Logger;

/**
 * Enterprise data object representing the controller class
 * 
 * @author raaool
 *
 */
public class LoginController extends HttpServlet{
	
	/** The default serial version id. */
	private static final long serialVersionUID = -7262828497113954905L;
	
	/** The logger. */
	private static final Logger logger = new Logger(LoginController.class);
	

	/** 
	 * The doGet method of the controller
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		
		if ((req.getRequestURL() + "").contains(Constants.LOGIN)) {
			login(req, resp);
		}
	}
	
	/**
	 * Method to load the identity from the controller.
	 * 
	 * @param req The http request
	 * @return Identity The identity populated from the http request
	 */
	public Identity populateIdentity(HttpServletRequest req) {
		Identity identity = new Identity();
		identity.setDisplayName(req.getParameter(Constants.DISPLAY_NAME_DB_COLOUMN));
		identity.setEmail(req.getParameter(Constants.EMAIL_DB_COLOUMN));
		identity.setUid(req.getParameter(Constants.UID_DB_COLOUMN));
		return identity;
	}

	/**
	 * The method handling the login operation.
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		Login loginRequest = new Login();
		loginRequest.setEmail(req.getParameter(Constants.EMAIL));
		loginRequest.setPassword(req.getParameter(Constants.LOGIN_PASSWORD));

		LoginService loginService = new LoginService();
		boolean status = loginService.loginservice(loginRequest);

		if (status) {
			logger.info("Admin, Send to a new page with more rights");
			try {
				//send to the dashboard.
				 resp.sendRedirect("adminpage.html");
				 
			} catch (IOException e) {
				logger.error(Constants.EXCEPTION, e);
			}
			
		} else {
			logger.error("Issue with login. Redirecting for error page.");
		}
	}
}
