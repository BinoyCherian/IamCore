package fr.epita.iam.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Enterprise test class to check the state of the sql db operations.
 * The class launches all the tests from the {@link RealTestSqlOperations} class 
 * 
 * @author raaool
 *
 */
public class LaunchAllTests {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(RealTestSqlOperations.class);

		// check failures

		if (result.getFailureCount() == 0) {
			System.out.println("All Tests passed");
		} else {
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
		}
	}
}
