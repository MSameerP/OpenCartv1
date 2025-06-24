package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") WebElement msgLoginFailed;
	
	public void sendEmail(String email) {
		txtEmailAddress.sendKeys(email);
	}
	
	public void sendPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public boolean isErrorMessageDisplayed() {
		try {
			return(msgLoginFailed.isDisplayed());
		} catch (Exception e) {
			return false; 
		}
		
	}
	
	

}
