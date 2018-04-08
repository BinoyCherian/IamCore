package fr.epita.iam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityException;
import fr.epita.iam.exceptions.SearchIdentityException;
import fr.epita.iam.service.IdentityService;
import fr.epita.logger.Logger;

public class IdentityController extends HttpServlet {
	
	/** The serial version id. */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	private static final Logger logger = new Logger(IdentityController.class);
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ((req.getRequestURL() + "").contains(Constants.CREATE_OPERATION)) {
			createData(req, resp);
		}else if((req.getRequestURL() + "").contains(Constants.SEARCH_OPERATION)) {
			searchData(req, resp);
		}else if((req.getRequestURL() + "").contains(Constants.UPDATE_OPERATION)) {
			updateData(req, resp);
		}else if((req.getRequestURL() + "").contains(Constants.DELETE_OPERATION)) {
			deleteData(req, resp);
		}
	}
	
	/**
	 * The create method for the controller
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	public void createData(HttpServletRequest req, HttpServletResponse resp) {
		Identity identity = populateIdentity(req);
		boolean status = false;
		
		IdentityService identityService = new IdentityService();
		try {
			status = identityService.createIdentity(identity);
		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		try {
			if (status) {
				resp.sendRedirect(Constants.SUCCESSPAGE);
			} else {
				resp.sendRedirect(Constants.ERRORPAGE);
			}
		} catch (IOException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}
	
	/**
	 * Method to handle the search operation.
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	public void searchData(HttpServletRequest req, HttpServletResponse resp) {
		List<Identity> identities = null;
		
		Identity identity=populateIdentity(req);

		IdentityService identityService = new IdentityService();
		try {
			 identities = identityService.searchIdentity(identity);
		} catch (SearchIdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		try {
			if (identities != null && !identities.isEmpty()) {
				HttpSession session = req.getSession(true);
				session.setAttribute("identities", identities);
				resp.sendRedirect("results.jsp");
			} else {
				resp.sendRedirect(Constants.ERRORPAGE);
			}
		} catch (IOException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}
	
	/**
	 * The method for updating the data.
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	public void updateData(HttpServletRequest req, HttpServletResponse resp) {
		Identity identity = new Identity();
		identity.setDisplayName(req.getParameter(Constants.DISPLAY_NAME_DB_COLOUMN));
		identity.setEmail(req.getParameter(Constants.EMAIL_DB_COLOUMN));
		identity.setUid(req.getParameter(Constants.UID_DB_COLOUMN));
		
		boolean status = false;
		
		IdentityService identityService = new IdentityService();
		try {
			status = identityService.updateIdentity(identity);
		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		try {
			if (status) {
				resp.sendRedirect(Constants.SUCCESSPAGE);
			} else {
				resp.sendRedirect(Constants.ERRORPAGE);
			}
		} catch (IOException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}

	/**
	 * Method hoisting the delete operation.
	 * 
	 * @param req The http request
	 * @param resp The http response
	 */
	public void deleteData(HttpServletRequest req, HttpServletResponse resp) {
		Identity identity = new Identity();
		identity.setUid(req.getParameter(Constants.UID_DB_COLOUMN));
		boolean status = false;
		IdentityService identityService = new IdentityService();
		try {
			status = identityService.deleteIdentity(identity);
		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
		
		try {
			if (status) {
				resp.sendRedirect(Constants.SUCCESSPAGE);
			} else {
				resp.sendRedirect(Constants.ERRORPAGE);
			}
		} catch (IOException e) {
			logger.error(Constants.EXCEPTION, e);
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
		if((req.getParameter(Constants.DISPLAY_NAME_DB_COLOUMN).length()!=0))
			identity.setDisplayName(req.getParameter(Constants.DISPLAY_NAME_DB_COLOUMN));
		if((req.getParameter(Constants.EMAIL_DB_COLOUMN).length()!=0))
			identity.setEmail(req.getParameter(Constants.EMAIL_DB_COLOUMN));
		if((req.getParameter(Constants.UID_DB_COLOUMN).length()!=0))
			identity.setUid(req.getParameter(Constants.UID_DB_COLOUMN));
		
		return identity;
	}
}
