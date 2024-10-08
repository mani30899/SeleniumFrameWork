package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditAccountPage extends BasePage {

	public EditAccountPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Edit Account']")
	public WebElement edit_Account;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement edit_Continue;

	@FindBy(css = ".alert.alert-success.alert-dismissible")
	public WebElement success_Msg;

	public void clickEditAccountButton() {
		edit_Account.click();
	}

	public void clickEditAccountContinueButton() {
		edit_Continue.click();
	}

	public String succuessMsg() {
		try {
			return (success_Msg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
