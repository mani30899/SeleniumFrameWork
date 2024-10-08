package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	public WebElement myAccountHeader;

	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	public WebElement logoutButton;

	public boolean isMyAccountPageExists() {
		try {

			return myAccountHeader.isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

	public void logoutButtonClick() {
		logoutButton.click();
	}

}
