package fr.epita.iam.dao;

import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityException;
import fr.epita.iam.exceptions.SearchIdentityException;

/**
 * The interface for the Identity database related operations.
 * 
 * @author raaool
 *
 */
public interface DaoInterface {
	
	/**
	 * The abstract operation to add an identity.
	 * @param identity The Identity in the request to be added.
	 * @return boolean TRUE/FALSE
	 */
	boolean addIdentity(Identity identity);
	
	/**
	 * The abstract operation to delete an identity.
	 * 
	 * @param identity The Identity in the request to be deleted.
	 * @return boolean TRUE/FALSE
	 */
	boolean deleteIdentity(Identity identity);
	
	/**
	 * The abstract operation to update, delete and insert an identity into the IDENTITIES table.
	 * @param identity The Identity in the request to be updated,deleted or inserted.
	 * @param operation The operation to be performed. The value "create" inserts, value "update" updates and "delete" deletes. 
	 * @return TRUE/FALSE
	 * @throws IdentityException The exception related to update, insert or create database operation
	 */
	boolean updateDeleteAndInsert(Identity identity, String operation) throws IdentityException;
	
	/**
	 * The abstract operation to search for identities.
	 * 
	 * @param identity The Identity in the request to be searched.
	 * @return List The list of Identities.
	 * @throws SearchIdentityException 
	 */
	List<Identity> getIdentities(Identity identity) throws SearchIdentityException;
}
