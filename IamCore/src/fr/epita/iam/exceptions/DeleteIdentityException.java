package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;

public class DeleteIdentityException extends Exception {
	
	/** The serial version uid. */
	private static final long serialVersionUID = 3892606183232879467L;
	
	/** The identity which has the issue. */
	private final Identity faultyIdentity;
	
	/**
	 * The constructor.
	 * 
	 * @param cause the exception cause
	 * @param identity The identity
	 */
	public DeleteIdentityException(Exception cause, Identity identity) {
		this.faultyIdentity=identity;
		this.initCause(cause);
	}
	
	@Override
	public String getMessage() {
		return "A problem occurred while deleting the Identity: "+faultyIdentity;
	}

}
