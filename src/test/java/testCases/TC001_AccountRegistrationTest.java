package testCases;

import pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups= {"Regression", "Master"})
	public void verify_account_registration() {
		try {
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("***clicked on myaccount***");
		hp.clickRegister();
		logger.info("***clicked on Register***");

		AccountregistrationPage regpage = new AccountregistrationPage(driver);

		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomtelephonennumber());

		String password = randompassword();
		regpage.setpassword(password);
		regpage.setconfrmpassword(password);
		regpage.setprivacypolicy();
		regpage.clickoncontinue();
		
		logger.info("***clicked on continue***");
		
		String cnf_message = regpage.getconfrmmessage();
		if(cnf_message.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		
		else {
			logger.error("test failed");
			logger.debug("debug logs");
			Assert.assertTrue(false, "failed due to mismatch in confirmation message");
		}
		//Assert.assertEquals(cnf_message, "Your Account Has Been Created!!");
		logger.info("***Account created successfully***");		}
		catch(Exception e) {
			
			Assert.fail("failed due to: "+e.getMessage());
			
		}
	}

}
