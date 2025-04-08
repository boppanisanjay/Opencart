package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locate webelements
	@FindBy(xpath="//*[@title='My Account']")
	WebElement drpdwn_myaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement link_register;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement link_login;
	
	//Actions
	public void clickMyAccount() {
		drpdwn_myaccount.click();
	}
	
	public void clickRegister() {
		link_register.click();
	}
	
	public void clicklogin() {
		link_login.click();
	}

}
