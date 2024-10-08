package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClassTest;

public class ExtendReportManager implements ITestListener {

	private ExtentSparkReporter sparkReporter; // UI of the Report
	private ExtentReports extent; // Populate common info on the report
	private ExtentTest test; // Updating the status of the result

	String repName;

	public void onStart(ITestContext context) {

		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
		Date date = new Date();
		String currentData = timeStamp.format(date);
		repName = "Test Report-" + currentData + ".html";
		String reportPath = System.getProperty("user.dir") + "\\reports\\" + repName;

		sparkReporter = new ExtentSparkReporter(reportPath);

		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Selenium Test Results");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operation System", os);

		String broswer = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", broswer);

		extent.setSystemInfo("Author", "Manikandan");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));

		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		// Create a test instance for each test
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test case passed is: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		if (test != null) {
			test.log(Status.FAIL, "Test case failed is: " + result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.INFO, "Test case failed cause is: " + result.getThrowable().getMessage());

			try {
				String imgPath = new BaseClassTest().captureScreeShot(result.getName());
				test.addScreenCaptureFromPath(imgPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("ExtentTest instance is null for failed test: " + result.getName());
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case skipped is: " + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush(); // Ensure all logs are written to the report at the end

//	    try {
//	        // Construct the report path
//	        String reportPath = System.getProperty("user.dir") + "/reports/" + repName;
//
//	        // Create a URL for the report
//	        URL url = new File(reportPath).toURI().toURL(); // Use File to create a proper URL
//
//	        // Create an instance of ImageHtmlEmail
//	        ImageHtmlEmail email = new ImageHtmlEmail();
//
//	        // Set SMTP server details (replace with your actual SMTP server)
//	        email.setHostName("smtp.gmail.com"); // For Gmail
//	        email.setSmtpPort(587); // Use port 587 for TLS
//	        email.setAuthenticator(new DefaultAuthenticator("your_email@gmail.com", "your_app_password")); // Use App Password if necessary
//	        email.setStartTLSEnabled(true); // Enable STARTTLS
//
//	        // Set recipient and sender details
//	        email.setFrom("mnmnkandan@gmail.com", "Manikandan N");
//	        email.setSubject("Test Email with Inline Image");
//	        
//	        // Corrected recipient email address
//	        email.addTo("manikandanvaken@gmail.com"); // Change this to the actual recipient
//
//	        // Set a valid body text for the email
//	        email.setMsg("Please find the attached test report.");
//
//	        // Attach the report
//	        email.attach(url, repName, "Please check");
//
//	        // Send the email
//	        email.send();
//
//	        System.out.println("Email sent successfully!");
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
	}
}