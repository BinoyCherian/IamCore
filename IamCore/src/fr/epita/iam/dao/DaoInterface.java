package fr.epita.iam.dao;

import java.util.List;

import fr.epita.iam.datamodel.Identity;

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
	 */
	boolean updateDeleteAndInsert(Identity identity, String operation);
	
	/**
	 * The abstract operation to search for identities.
	 * 
	 * @param identity The Identity in the request to be searched.
	 * @return List The list of Identities.
	 */
	List<Identity> getIdentities(Identity identity);
}
