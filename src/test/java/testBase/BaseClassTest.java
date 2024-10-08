package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClassTest {

	public static WebDriver driver;
	public Logger logger;
	public Properties prot;

	@BeforeClass
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		logger = LogManager.getLogger(this.getClass());
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		prot = new Properties();
		prot.load(file);
		

		// loading config files

		if (prot.getProperty("Execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.print("No matching os");

				return;
			}

			switch (br.toLowerCase()) {

			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid browser");
				return;

			}
			driver = new RemoteWebDriver(new URL("http://192.168.207.226:4444"), capabilities);

		}
		if (prot.getProperty("Execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {

			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser");
				return;

			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(prot.getProperty("appUrl"));
		driver.manage().window().maximize();
	}

	public String captureScreeShot(String tname) {
		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss");
		Date date = new Date();
		String currentData = timeStamp.format(date);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File SourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "//screenshots//" + tname + "_" + currentData + ".png";
		File tagerFile = new File(targetFilePath);
		SourceFile.renameTo(tagerFile);

		return targetFilePath;
	}

	@AfterClass
	public void testDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
