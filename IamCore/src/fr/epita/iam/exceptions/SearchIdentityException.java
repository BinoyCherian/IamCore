/**
 * 
 */
package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;

/**
 * Exception related to the search operation.
 * 
 * @author raaool
 *
 */
public class SearchIdentityException extends Exception {
	
	/** The serial version uid. */
	private static final long serialVersionUID = 6314301050822548637L;
	
	/** The idenity which could not be searched. */
	private final Identity searchIdentity;
	
	/**
	 * The constructor.
	 * 
	 * @param cause The cause of the exception.
	 * @param identity The identity
	 */
	public SearchIdentityException(Exception cause, Identity identity) {
		searchIdentity=identity;
		this.initCause(cause);
	}

	@Override
	public String getMessage() {
		return "A problem occurred while searching for Idenity: "+searchIdentity;
	}
}
