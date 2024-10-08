package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.EditAccountPage;
import pageObjects.LoginPage;
import testBase.BaseClassTest;

public class EditAccountTest extends BaseClassTest {

	@Test
	public void testLogin() throws InterruptedException{
		logger.info("*****testLogin method is start******");
		try {
		LoginPage lp = new LoginPage(driver);
		logger.info("*****LoginPage object is created******");
		lp.setEmailId(prot.getProperty("email"));
		logger.info("*****Entered email ID*****");
		lp.setLoginPassword(prot.getProperty("password"));
		logger.info("*****Enter password******");
		lp.loginClick();
		EditAccountPage edictAcc = new EditAccountPage(driver);
		logger.info("*****EditAccountPage object is created******");
		Thread.sleep(2000);
		edictAcc.clickEditAccountButton();
		Thread.sleep(2000);
		edictAcc.clickEditAccountContinueButton();
		Thread.sleep(2000);
		Assert.assertFalse(edictAcc.succuessMsg().isEmpty());
		}catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("******testLogin method is end*********");
	}

}
