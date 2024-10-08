package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClassTest;

public class LoginTestVerify extends BaseClassTest {

	@Test()
	void testLogin() throws InterruptedException {
		logger.info("****testLogin method start*****");
		try {
			LoginPage lp = new LoginPage(driver);
			logger.info("*****LoginPage object is created******");
			lp.setEmailId(prot.getProperty("email"));
			logger.info("*****Entered email ID*****");
			lp.setLoginPassword(prot.getProperty("password"));
			logger.info("*****Enter password******");
			lp.loginClick();
			MyAccountPage ma = new MyAccountPage(driver);
			if (ma.isMyAccountPageExists()) {
				ma.logoutButtonClick();
				Assert.assertTrue(true);
			} else {
				Assert.fail();
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("****testLogin method ended*****");
	}

}
