package fr.epita.iam.datamodel;

/**
 * Enterprise data object representing the login credentials.
 * 
 * @author raaool
 *
 */
public class Login {
	
	/** The e-mail */
	private String email;
	
	/** The password */
	private String password;

	/**
	 * Gets the e-mail.
	 * 
	 * @return String The e-mail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the e-mail.
	 * 
	 * @param email The e-mail address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 * 
	 * @return String The password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password The password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
