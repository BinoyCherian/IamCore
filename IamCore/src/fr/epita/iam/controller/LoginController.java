package fr.epita.iam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epita.iam.constants.Constants;
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
		if (null != req) {
			if ((req.getRequestURL() + "").contains(Constants.LOGIN)) {
				login(req, resp);
			} else if ((req.getRequestURL() + "").contains(Constants.CREATE_LOGIN)) {
				createTheAdmin(req, resp);
			}
		}
	}

	/**
	 * The method handling the login operation.
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		Login loginRequest = populateLoginInfo(req, null);

		LoginService loginService = new LoginService();
		boolean status = loginService.loginservice(loginRequest);

		if (status) {
			logger.info("Admin, Send to a new page with more rights");
			try {
				//send to the dashboard.
				 resp.sendRedirect(Constants.SUCCESSPAGE);
				 
			} catch (IOException e) {
				logger.error(Constants.EXCEPTION, e);
			}
			
		} else {
			logger.error("Issue with login. Redirecting for error page.");
			try {
				resp.sendRedirect(Constants.ERRORPAGE);
			} catch (IOException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		}
	}
	
	/**
	 * Method that will support creating a new admin for the portal.
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	private void createTheAdmin(HttpServletRequest req, HttpServletResponse resp) {
		Login loginRequest = populateLoginInfo(req, Constants.CREATE_LOGIN);
		
		LoginService loginService = new LoginService();
		boolean status = loginService.createLogin(loginRequest);
		
		if (status) {
			logger.info("Admin created, Redirect to dashboard");
			try {
				//send to the dashboard.
				 resp.sendRedirect("adminpage.html");
				 
			} catch (IOException e) {
				logger.error(Constants.EXCEPTION, e);
			}
			
		} else {
			logger.error("Issue with admin create operation. Redirecting for error page.");
			
			try {
				resp.sendRedirect(Constants.ERRORPAGE);
			} catch (IOException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		}
	}
	
	/**
	 * Method to populate the login object.
	 * 
	 * @param req The h+ttp servlet request
	 * @param operation The string operation, not null if you want to create a new operation
	 * @return Login The populated login object
	 */
	public Login populateLoginInfo(HttpServletRequest req, String operation) {
		Login loginRequest = null;
		
		if (isNotNull(req.getParameter(Constants.LOGIN_PASSWORD))
				&& isNotNull(req.getParameter(Constants.EMAIL))) {
			loginRequest = new Login();
			loginRequest.setEmail(req.getParameter(Constants.EMAIL));
			loginRequest.setPassword(req.getParameter(Constants.LOGIN_PASSWORD));

			if (null!=operation && operation.equalsIgnoreCase(Constants.CREATE_LOGIN)
					&& isNotNull(req.getParameter(Constants.CONFIRM_LOGIN_PASSWORD))) {
				loginRequest.setConfirmPassword(req.getParameter(Constants.CONFIRM_LOGIN_PASSWORD));
			}
		}
		return loginRequest;
	}

	/**
	 * Method to check if the parameter is null or not.
	 * 
	 * @param parameter The string parameter to be tested
	 * @return boolean The indicaters TRUE/FALSE
	 */
	private boolean isNotNull(String parameter) {
		return (null==parameter)?true:false;
	}
}
