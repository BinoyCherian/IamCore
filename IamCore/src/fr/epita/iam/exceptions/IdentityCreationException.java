package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;

/**
 * Exception class to indicate an issue with the identity creation.
 * 
 * @author raaool
 *
 */
public class IdentityCreationException extends Exception{
	
	/** The serial version id. */
	private static final long serialVersionUID = 6687087889994626679L;
	
	/** The identity which has the issue. */
	private final Identity faultyIdentity;
	
	/**
	 * The constructor.
	 * 
	 * @param cause The cause of the exception
	 * @param identity The identity
	 */
	public IdentityCreationException(Exception cause, Identity identity) {
		this.faultyIdentity=identity;
		this.initCause(cause);
	}
	
	@Override
	public String getMessage() {
		return "A problem occurred while creating the Identity: "+faultyIdentity;
	}

}
