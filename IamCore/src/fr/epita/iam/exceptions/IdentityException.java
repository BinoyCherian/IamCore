package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;

public class IdentityException extends Exception {
	
	/** The serial version uid. */
	private static final long serialVersionUID = 2635054502481150045L;
	
	/** The identity which has the issue. */
	private final Identity faultyIdentity;
	
	private String operation;
	
	/**
	 * The constructor.
	 * 
	 * @param cause The cause of the exception.
	 * @param identity The identity
	 */
	public IdentityException(Exception cause, Identity identity, String operation) {
		this.faultyIdentity=identity;
		this.initCause(cause);
		this.operation=operation;
	}
	
	@Override
	public String getMessage() {
		return "A problem occurred while "+ operation +" the Identity: "+faultyIdentity;
	}

}
