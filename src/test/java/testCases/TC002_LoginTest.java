package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void login() {
		try {
			logger.info("*********  Starting TC_002_LoginTest *******");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked MyAccount");
			hp.clickLogin();
			logger.info("Clicked MyAccount");
			
			logger.info("Providing login details");
			LoginPage lp = new LoginPage(driver);
			lp.sendEmail(p.getProperty("email"));
			lp.sendPassword(p.getProperty("password"));
			lp.clickLogin();
			
			logger.info("Verifying Myaccount Page");
			MyAccountPage map = new MyAccountPage(driver);
			boolean target = map.isMyAccountPageExist();
			Assert.assertTrue(target);
			
		} catch (Exception e) {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		logger.info("*********  Finished TC_002_LoginTest *******");
	}

}
