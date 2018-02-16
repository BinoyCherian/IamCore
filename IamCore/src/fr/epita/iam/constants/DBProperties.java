package fr.epita.iam.constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.epita.logger.Logger;

/**
 * Enterprise data class to load the properties for dao classes.
 * 
 * @author raaool
 *
 */
public class DBProperties {
	
	/** The properties object */
	static Properties properties;
	
	/** The inputstream object */
	static InputStream inputStream;
	
	/** The logger. */
	private static final Logger logger = new Logger(DBProperties.class);

	/**
	 * Private Constructor.
	 */
	private DBProperties() {
		
	}
	
	/**
	 * Method to initialise the properties from the resource folder.
	 * This is a two way property load. The method tries to load the property from the
	 * system property conf, if for unexpected reasons the stream is not loaded
	 * the method loads the input stream from the db.properties file from the resource folder.
	 * 
	 * @return Properties The database properties
	 */
	public static Properties initialiseProperties() {

		properties = new Properties();
		
		try {
			if (System.getProperty(Constants.CONF) == null) {
				logger.error("System property needs to be set.");
			}
			
			inputStream = new FileInputStream(System.getProperty(Constants.CONF));
		} catch (IOException e) {
			logger.error(Constants.EXCEPTION, e);
		}

		if (inputStream == null) {
			logger.info("Loading from the local project file");
			inputStream = DBProperties.class.getClassLoader().getResourceAsStream("db.properties");

			if (inputStream == null) {
				logger.error("Could not find the property file from the project space");
			}
		}
		
		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				logger.error(Constants.EXCEPTION, e);
			}
		} else {
			logger.error("Input stream could not be loaded from both system property or the resource folder in the "
					+ "project space");
		}

		return properties;
	}
}
