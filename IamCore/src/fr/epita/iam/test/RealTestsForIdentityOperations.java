package fr.epita.iam.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.dao.DaoInterface;
import fr.epita.iam.dao.IdentitiesDao;
import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityException;
import fr.epita.iam.exceptions.SearchIdentityException;
import fr.epita.logger.Logger;

/**
 * Enterprise test class contaning the test methods. insert and delete
 * operations are configured to run at the beginning and at the end
 * using @AfterClass and @BeforeClass. Therefore a successful run of all the
 * test cases will delete all records from the table
 * 
 * @author raaool
 *
 */
public class RealTestsForIdentityOperations {

	/** The dao interface. */
	static DaoInterface dao;

	/** The logger. */
	private static final Logger logger = new Logger(RealTestsForIdentityOperations.class);

	/** The uid to be used for all the test cases below*/
	private static final String ID = "11136209";

	@BeforeClass
	public static void insertOperation() {
		dao = new IdentitiesDao();
		Identity identity = new Identity();

		identity.setDisplayName("Binoy");
		identity.setEmail("cherian2ams@gmail.com");
		identity.setUid(ID);

		boolean updated;
		try {
			updated = dao.updateDeleteAndInsert(identity, Constants.CREATE_OPERATION);
			assertTrue(updated);

			if (updated) {
				logger.info("update success");
			} else {
				logger.error("some error");
			}
		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}

	@Test
	public void updateOperationBothEmailAndName() {

		Identity identity = new Identity();

		identity.setDisplayName("Binoy Cherian");
		identity.setEmail("cherian2ams@gmail.com");
		identity.setUid(ID);

		boolean updated;
		try {
			updated = dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);

			assertTrue(updated);

			if (updated) {
				logger.info("update success");
			} else {
				logger.error("some error");
			}
		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}

	@Test
	public void updateOperationOnlyEmail() {

		Identity identity = new Identity();

		identity.setEmail("cherianbinoy@ymail.com");
		identity.setUid(ID);

		boolean updated;
		try {
			updated = dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);
			assertTrue(updated);

		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}

	@Test
	public void updateOperationOnlyDisplayName() {

		Identity identity = new Identity();

		identity.setDisplayName("CHERIAN Binoy");
		identity.setUid(ID);

		boolean updated;
		try {
			updated = dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);
			assertTrue(updated);
		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}

	@Test
	public void searchOperation() {
		Identity identity = new Identity();
		identity.setUid(ID);

		List<Identity> listOfidentities;
		try {
			listOfidentities = dao.getIdentities(identity);
			assertTrue(!listOfidentities.isEmpty());
		} catch (SearchIdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}

	@AfterClass
	public static void deleteOperation() {

		Identity identity = new Identity();
		identity.setUid(ID);

		boolean executed;
		try {
			executed = dao.updateDeleteAndInsert(identity, Constants.DELETE_OPERATION);
			assertTrue(executed);

		} catch (IdentityException e) {
			logger.error(Constants.EXCEPTION, e);
		}
	}
}
