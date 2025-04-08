package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.*;

public class TC003_LoginDDT extends  BaseClass{ // LoginDDT = login data driven test
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"Sanity", "DataDriven"})
	public void login_ddt(String email, String password, String exp) {
		logger.info("***TC003 starting***");
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();

		LoginPage lp = new LoginPage(driver);
		lp.set_inputemail(email);
		lp.set_inputpassword(password);
		lp.click_login();

		MyAccountPage myacc = new MyAccountPage(driver);
		boolean myaccountpage_status = myacc.myAccountpage_exists();
		
		/* 
		 * valid creds - login success - pass
		 * 				 login fail   -- fail
		 * 
		 * invalid creds - login fail - pass
		 *                 login success - fail
		 * 
		 * */
		
		if (exp.equalsIgnoreCase("valid")) {
			if (myaccountpage_status) {
				logger.info("Login successful as expected.");
				myacc.myAccount_logout();
				Assert.assertTrue(true);
			} else {
				logger.warn("Login failed unexpectedly for valid credentials.");
				Assert.fail("Expected successful login but got failure.");
			}
		} else if (exp.equalsIgnoreCase("invalid")) {
			if (myaccountpage_status) {
				logger.warn("Login successful unexpectedly for invalid credentials.");
				myacc.myAccount_logout();
				Assert.fail("Expected login failure but got success.");
			} else {
				logger.info("Login failed as expected for invalid credentials.");
				Assert.assertTrue(true);
			}
		} else {
			logger.error("Invalid test data: Expected 'valid' or 'invalid' but got '" + exp + "'");
			Assert.fail("Invalid test case classification.");
		}
		/*if(exp.equalsIgnoreCase("valid")) {
			if(myaccountpage_status==true) {
				myacc.myAccount_logout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}			
		}
		
		if(exp.equalsIgnoreCase("invalid")) {
			if(myaccountpage_status==true) {
				myacc.myAccount_logout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		*/
		logger.info("***TC003 finished***");
	}
}
