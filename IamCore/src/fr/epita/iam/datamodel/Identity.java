/**
 * 
 */
package fr.epita.iam.datamodel;

import java.util.Map;

/**
 * Enterprise data object holding Identity Information.
 * 
 * @author raaool
 *
 */
/**
 * @author raaool
 *
 */
public class Identity {
	
	/** The display name */
	private String displayName;
	
	/** The user id */
	private String uid;
	
	/** The email */
	private String email;
	

	/**
	 * Gets the display name
	 * 
	 * @return String The display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name
	 * 
	 * @param displayName The display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the user id
	 * 
	 * @return uid The user id
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the user id
	 * 
	 * @param uid The user id
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	
	/**
	 * Gets the e-mail address
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the e-mail address
	 * 
	 * @param email The e-mail address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Identity [displayName=" + displayName + ", uid=" + uid + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identity other = (Identity) obj;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
}
