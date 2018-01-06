package fr.epita.iam.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Enterprise data class to load the properties for dao classes.
 * 
 * @author raaool
 *
 */
public class DBProperties {
	
	/** The properties object */
	static Properties dbProperties;
	
	/** The inputstream object */
	static InputStream inputStream;

	
	/**
	 * Method to initialise the properties from the resource folder.
	 * 
	 * @return Properties The database properties
	 */
	public static Properties initialiseProperties() {

		dbProperties = new Properties();
		inputStream = DBProperties.class.getClassLoader().getResourceAsStream("db.properties");
		try {

			if (inputStream == null) {
				System.out.println("unable to find properties file");
			} else {
				dbProperties.load(inputStream);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dbProperties;
	}
}
