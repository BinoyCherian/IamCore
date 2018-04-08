package fr.epita.iam.service;

/**
 * Enterprise class housing common utility methods.
 * 
 * @author raaool
 *
 */
public class Reuse {
	
	/**
	 * Method to check if the parameter is null or not.
	 * 
	 * @param parameter The string parameter to be tested
	 * @return boolean The indicaters TRUE/FALSE
	 */
	public static boolean isNotNull(String parameter) {
		return (null!=parameter)?true:false;
	}
}
