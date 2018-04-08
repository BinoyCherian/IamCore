package fr.epita.iam.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.epita.iam.dao.LoginDao;
import fr.epita.iam.dao.LoginDAOInterface;
import fr.epita.iam.datamodel.Login;
import fr.epita.iam.service.LoginService;

public class RealTestForLoginOperations {
	
	public void testAdmin() {
		Login login=new Login();
		LoginDAOInterface loginDao= new LoginDao();
		
		login.setEmail("root@iamcore.com");
		login.setPassword("ROOT");
		
		boolean loginSucess= loginDao.checkLogin(login);
		assertTrue(loginSucess);
	}
	
	public void testCreateAdmin() {
		Login login=new Login();
		LoginDAOInterface loginDao= new LoginDao();
		
		login.setEmail("tester@iamcore.com");
		login.setPassword("test");
		
		boolean createStatus=loginDao.createAdmin(login);
		assertTrue(createStatus);
	}
	
	@Test
	public void testDifferentPasswordsAtService()
	{
		Login login=new Login();
		login.setEmail("tester@iamcore.com");
		login.setPassword("test");
		login.setConfirmPassword("bool");
		LoginService loginService=new LoginService();
		boolean status=loginService.createLogin(login);
		assertFalse(status);
	}
}
