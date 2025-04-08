package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountregistrationPage extends BasePage{
	
	public AccountregistrationPage(WebDriver driver){
		super(driver);
	}
	
	//locate webelements
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confrmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chk_agree;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confrm_message;
	
	//Actions
	
	public void setFirstName(String firstname) {
		txt_firstname.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		txt_lastname.sendKeys(lastname);
	}
	
	public void setEmail(String email) {
		txt_email.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		txt_telephone.sendKeys(telephone);
	}
	
	public void setpassword(String password) {
		txt_password.sendKeys(password);
	}
	
	public void setconfrmpassword(String confrmpassword) {
		txt_confrmpassword.sendKeys(confrmpassword);
	}
	
	public void setprivacypolicy() {
		chk_agree.click();
	}
	
	public void clickoncontinue() {
		btn_continue.click();
	}
	
	public String getconfrmmessage() {
		return confrm_message.getText();	
	}
		
}

