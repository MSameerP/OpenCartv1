package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		logger.info("*******  Starting TC001_AccountRegistrationTest  *******");
		
		try {
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on Account link");
			hp.clickRegister();
			logger.info("Clicked on Register link");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("Providing Customer Details");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String pass = randomAlphaNumeric();
			regpage.setPasword(pass);
			regpage.setCnfPassword(pass);
			regpage.clickChkBox();
			regpage.clickContinue();
			
			logger.info("Validating Expected message");
			String confmsg = regpage.getConfirmationMessage();
			if(confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}else {
				logger.error("Test Failed....");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
			
		} catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("*******  Finished TC001_AccountRegistrationTest  *******");
		
	}
	


}
