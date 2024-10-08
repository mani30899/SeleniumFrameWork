package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClassTest;
import utilities.DataProviders;

public class LoginTestWithDp extends BaseClassTest {

	@Test(dataProvider = "Dp", dataProviderClass = DataProviders.class)
	void testLogin(String email, String password, String exp) throws InterruptedException {
		logger.info("****testLogin method start*****");
		try {
			LoginPage lp = new LoginPage(driver);
			logger.info("*****LoginPage object is created******");
			lp.setEmailId(email);
			logger.info("*****Entered email ID*****");
			lp.setLoginPassword(password);
			logger.info("*****Enter password******");
			lp.loginClick();
			MyAccountPage ma = new MyAccountPage(driver);
			if (exp.equalsIgnoreCase("valid")) {
				if (ma.isMyAccountPageExists()) {
					Assert.assertTrue(true);
					ma.logoutButtonClick();
				} else {
					Assert.fail();
				}
			}

			if (exp.equalsIgnoreCase("invalid")) {
				if (ma.isMyAccountPageExists()) {
					ma.logoutButtonClick();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("****testLogin method ended*****");
	}
}
