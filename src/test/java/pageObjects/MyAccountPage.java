package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@id='content']//h2[normalize-space()='My Account']") WebElement msgConfirmationHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement btnLogout;
	@FindBy(xpath="//a[normalize-space()='Continue']") WebElement btnContinue;
	
	
	public boolean isMyAccountPageExist() {
		try {
			return (msgConfirmationHeading.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		btnLogout.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	

}
