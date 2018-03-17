package fr.epita.iam.service;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author raaool
 *
 */
public interface DBConnectionInterface {
	Connection getDbConnection(Properties properties);
}
