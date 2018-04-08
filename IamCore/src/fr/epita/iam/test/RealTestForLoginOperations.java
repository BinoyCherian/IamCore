package fr.epita.iam.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.epita.iam.dao.LoginDao;
import fr.epita.iam.dao.LoginDAOInterface;
import fr.epita.iam.datamodel.Login;

public class RealTestForLoginOperations {
	
	@Test
	public void testAdmin() {
		Login login=new Login();
		LoginDAOInterface loginDao= new LoginDao();
		
		login.setEmail("root@iamcore.com");
		login.setPassword("ROOT");
		
		boolean loginSucess= loginDao.checkLogin(login);
		assertTrue(loginSucess);
	}

}
