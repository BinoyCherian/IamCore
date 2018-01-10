package fr.epita.iam.test;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epita.iam.constants.Constants;
import fr.epita.iam.dao.IdentitiesDao;
import fr.epita.iam.datamodel.Identity;

/**
 * Enterprise test class contaning the test methods.
 * insert and delete operations are configured to run at the beginning and at the end using @AfterClass and @BeforeClass.
 * Therefore a successful run of all the test cases will delete all records from the table
 * 
 * @author raaool
 *
 */
public class RealTestSqlOperations{
	
	@BeforeClass
	public static void insertOperation() {
		IdentitiesDao dao=new IdentitiesDao();
		
		Identity identity=new Identity();
		
		identity.setDisplayName("Binoy");
		identity.setEmail("cherian2ams@gmail.com");
		identity.setUid("11136209");
		
		boolean updated=dao.updateDeleteAndInsert(identity, Constants.CREATE_OPERATION);
		
			assertTrue(updated);
		
		if(updated) {
			System.out.println("update success");
		}else {
			System.out.println("some error");
		}
	}
	
	@Test
	public void updateOperationBothEmailAndName() {
		IdentitiesDao dao=new IdentitiesDao();
		
		Identity identity=new Identity();
		
		identity.setDisplayName("Binoy Cherian");
		identity.setEmail("cherian2ams@gmail.com");
		identity.setUid("11136209");
		
		boolean updated=dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);
		assertTrue(updated);
		
		if(updated) {
			System.out.println("update success");
		}else {
			System.out.println("some error");
		}
	}
	
	@Test
	public void updateOperationOnlyEmail() {
		IdentitiesDao dao=new IdentitiesDao();
		
		Identity identity=new Identity();
		
		identity.setEmail("cherianbinoy@ymail.com");
		identity.setUid("11136209");
		
		boolean updated=dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);
		assertTrue(updated);
		
		if(updated) {
			System.out.println("update success");
		}else {
			System.out.println("some error");
		}
	}

	@Test
	public void updateOperationOnlyDisplayName() {
		IdentitiesDao dao=new IdentitiesDao();
		
		Identity identity=new Identity();
		
		identity.setDisplayName("CHERIAN Binoy");
		identity.setUid("11136209");
		
		boolean updated=dao.updateDeleteAndInsert(identity, Constants.UPDATE_OPERATION);
		assertTrue(updated);
		if(updated) {
			System.out.println("update success");
		}else {
			System.out.println("some error");
		}
	}
	
	
	@AfterClass
	public static void deleteOperation() {
		
		Identity identity =new Identity();
		identity.setUid("11136209");
		
		boolean executed= new IdentitiesDao().deleteIdentity(identity);
		assertTrue(executed);
		
	}
}
