package fr.epita.iam.constants;

import java.io.FileInputStream;
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
	 * This is a two way property load. The method tries to load the property from the
	 * system property conf, if for unexpected reasons the stream is not loaded
	 * the method loads the input stream from the db.properties file from the resource folder.
	 * 
	 * @return Properties The database properties
	 */
	public static Properties initialiseProperties() {

		dbProperties = new Properties();
		
		try {
			inputStream = new FileInputStream(System.getProperty("conf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Issue while loading the property from system property: conf");
			e.printStackTrace();
		}

		if (inputStream == null) {
			System.out.println("Loading from the local project file");
			inputStream = DBProperties.class.getClassLoader().getResourceAsStream("db.properties");

			if (inputStream.equals(null)) {
				System.out.println("Could not find the property file from the project space");
			}
		}
		
		if (inputStream != null) {
			try {
				dbProperties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Input stream could not be loaded from both system property or the resource folder in the "
					+ "project space");
		}

		return dbProperties;
	}
}
