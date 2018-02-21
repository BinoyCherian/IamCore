package fr.epita.iam.exceptions;

import fr.epita.iam.datamodel.Identity;

public class UpdateIdentityException extends Exception {
	
	/** The serial version uid. */
	private static final long serialVersionUID = 2635054502481150045L;
	
	/** The identity which has the issue. */
	private final Identity faultyIdentity;
	
	/**
	 * The constructor.
	 * 
	 * @param cause The cause of the exception.
	 * @param identity The identity
	 */
	public UpdateIdentityException(Exception cause, Identity identity) {
		this.faultyIdentity=identity;
		this.initCause(cause);
	}
	
	@Override
	public String getMessage() {
		return "A problem occurred while updating the Identity: "+faultyIdentity;
	}

}
