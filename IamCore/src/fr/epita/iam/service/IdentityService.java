package fr.epita.iam.service;

import java.util.List;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.dao.DaoInterface;
import fr.epita.iam.dao.IdentitiesDao;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityException;
import fr.epita.iam.exceptions.SearchIdentityException;

/**
 * The service class for the identity class.
 * 
 * @author raaool
 *
 */
public class IdentityService {
	
	/** The dao interface. */
	private DaoInterface dao;
	
	/**
	 * The constructor.
	 */
	public IdentityService() {
		dao=new IdentitiesDao();
	}
	
	/**
	 * Method to persist an object into the database table IDENTITIES.
	 * 
	 * @param identity The identity to be persisted
	 * @return boolean TRUE/FALSE
	 * @throws IdentityException The exception related to the create operation
	 */
	public boolean createIdentity(Identity identity) throws IdentityException {
		return dao.updateDeleteAndInsert(identity, Constants.CREATE_OPERATION);
	}
	
	/**
	 * Method to update an identity into the database table IDENTITIES.
	 * 
	 * @param identity The identity to be modified
	 * @return boolean TRUE/FALSE
	 * @throws IdentityException The exception related to the update operation
	 */
	public boolean updateIdentity(Identity identity) throws IdentityException {
		return dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);
	}
	
	/**
	 * Method to delete an identity from the database table IDENTITIES.
	 * 
	 * @param identity The identity to be deleted
	 * @return boolean TRUE/FALSE
	 * @throws IdentityException The exception related to the delete operation.
	 */
	public boolean deleteIdentity(Identity identity) throws IdentityException {
		return dao.updateDeleteAndInsert(identity, Constants.DELETE_OPERATION);
	}
	
	/**
	 * Method to search an identity from the database table IDENTITIES.
	 * 
	 * @param identity The identity to be searched.
	 * @return List The list of identities.
	 * @throws SearchIdentityException The exception related to the search operation.
	 */
	public List<Identity> searchIdentity(Identity identity) throws SearchIdentityException {
		return dao.getIdentities(identity);
	}
}
