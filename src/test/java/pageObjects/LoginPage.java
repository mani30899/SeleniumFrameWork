package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


	public LoginPage(WebDriver driver) {

		super(driver);

	}

	// Locator

	@FindBy(id = "input-email")
	public WebElement user_email;

	@FindBy(id = "input-password")
	public WebElement user_password;

	@FindBy(xpath = "//input[@value='Login']")
	public WebElement user_login_button;
	
	//methods

	public void setEmailId(String mailId) {
		user_email.sendKeys(mailId);
	}

	public void setLoginPassword(String password) {
		user_password.sendKeys(password);
	}

	public void loginClick() {
		user_login_button.click();
	}
	
}
