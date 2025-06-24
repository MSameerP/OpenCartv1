package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid  - Login success - test pass - logout
 * Data is valid  - Login fail - test fail
 * Data is invalid  - Login fail - test pass
 * Data is invalid  - Login success - test fail - logout
 */

public class TC003_LoginDDT extends BaseClass{
	
	
	 @Test(dataProvider="LoginData",dataProviderClass = DataProviders.class,groups="Datadriven")
	 public void verify_loginDDT(String email, String pass, String exp) {
		 try {
			    logger.info("*********  Starting TC_003_LoginDDT *******");
			 
			    //HomePage
			 	HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				logger.info("Clicked MyAccount");
				hp.clickLogin();
				logger.info("Clicked Login");
				
				//Login Page
				logger.info("Providing login details");
				LoginPage lp = new LoginPage(driver);
				lp.sendEmail(email);
				lp.sendPassword(pass);
				lp.clickLogin();
				
				logger.info("Verifying Myaccount Page");
				MyAccountPage map = new MyAccountPage(driver);
				boolean target = map.isMyAccountPageExist();
				
				if(exp.equalsIgnoreCase("valid")) {
					if(target==true) {
						map.clickLogout();
						logger.info("Valid credentials given and it got logged in successfully, working as expected...");
						Assert.assertTrue(true);
					}else {
						Assert.fail();
					}
				}
				
				if(exp.equalsIgnoreCase("invalid")) {
					if(target==true) {
						map.clickLogout();
						Assert.assertTrue(false);
					}else {
						logger.info("Invalid credentials given and it didnt loggin, working as expected...");
						Assert.assertTrue(true);
					}
				}
			
		} catch (Exception e) {
			Assert.fail();
		}
		
		 logger.info("*********  Finished TC_003_LoginDDT *******");

	 }

}
