package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_Logintest extends BaseClass {

	@Test(groups= {"Sanity", "Regression", "Master"})
	public void testlogin() {
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clicklogin();

			LoginPage lp = new LoginPage(driver);
			lp.set_inputemail(p.getProperty("email"));
			lp.set_inputpassword(p.getProperty("password"));
			lp.click_login();

			MyAccountPage myacc = new MyAccountPage(driver);
			boolean myaccountpage_status = myacc.myAccountpage_exists();
			if(myaccountpage_status) {
				Assert.assertTrue(true);
				logger.info("***My Account page is displayed***");
			}
			else {
				logger.info("***My Account page is not displayed***");
				Assert.fail();
			}
		}
		catch(Exception e) {
			Assert.fail("Test failed due to exception: " + e.getMessage());
			logger.info("***My Account page is not displayed***");
		}

	}


}
