package buihanh.hooks;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import buihanh.driver.DriverFactory;
import buihanh.helpers.CaptureHelpers;
import buihanh.helpers.ExcelHelpers;
import buihanh.helpers.Helpers;
import buihanh.keywords.WebUI;
import buihanh.report.TelegramManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import buihanh.utils.ConfigReader;
import buihanh.utils.EmailSendUtils;
import buihanh.utils.ZipUtils;

public class MyHooks {
	WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		Properties prop = new ConfigReader().intializeProperties();
		driver = DriverFactory.initializeBrowser(prop.getProperty("browser"));
		driver.get(prop.getProperty("url"));
	}
	
	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			CaptureHelpers.captureScreenshot(driver, scenario.getName());
			 final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", "Screenshot Failed");	            
		}		
		driver.quit();
	}
	
	@BeforeAll
	public static void before_all() {
		System.out.println("================ BEFORE ALL ================");
		//CaptureHelpers.startRecord("video");
	}

	@AfterAll
	public static void after_all() {
		System.out.println("================ AFTER ALL ================");
//		CaptureHelpers.stopRecord();
//		ZipUtils.zip();
//	    EmailSendUtils.sendEmail();
//	    TelegramManager.sendMessageText();
//		TelegramManager.sendFilePath();
	
	}
}
