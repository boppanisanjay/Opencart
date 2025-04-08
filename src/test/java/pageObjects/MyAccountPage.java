package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver){
		super(driver);
	}

	// locators
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement myAccount_title;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement myAccount_logout;
	
	//action methods
	
	public boolean myAccountpage_exists() {
		return (myAccount_title.isDisplayed());
	}
	
	public void myAccount_logout() {
		myAccount_logout.click();
	}
	

}
