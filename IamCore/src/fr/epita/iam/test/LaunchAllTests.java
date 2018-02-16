package fr.epita.iam.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import fr.epita.logger.Logger;

/**
 * Enterprise test class to check the state of the sql db operations.
 * The class launches all the tests from the {@link RealTestsForIdentityOperations} class 
 * 
 * @author raaool
 *
 */
public class LaunchAllTests {
	
	/** The logger. */
	private static final Logger logger = new Logger(LaunchAllTests.class);


	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(RealTestsForIdentityOperations.class);

		// check failures

		if (result.getFailureCount() == 0) {
			logger.info("All Tests passed");
		} else {
			for (Failure failure : result.getFailures()) {
				logger.error(failure.toString());
			}
		}
	}
}
