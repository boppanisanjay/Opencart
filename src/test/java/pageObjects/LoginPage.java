package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-email']") WebElement txt_inputemail;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_inputpassword;
	@FindBy(xpath="//input[@value='Login']") WebElement btn_login;
	
	public void set_inputemail(String email) {
		txt_inputemail.sendKeys(email);
	}
	
	public void set_inputpassword(String password) {
		txt_inputpassword.sendKeys(password);
	}
	
	public void click_login() {
		btn_login.click();
	}

}
