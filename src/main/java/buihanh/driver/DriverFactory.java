package buihanh.driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
public class DriverFactory {

	static WebDriver driver;

	public static WebDriver initializeBrowser(String browserName) {
		if (browserName.equals("chrome")) {
			driver = initChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = initFirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = initEdgeDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}
		return driver;

	}

	public static WebDriver getDriver() {
		return driver;
	}

	// Khoi tao cau hinh cua cac Browser
	public static WebDriver initChromeDriver() {
		//System.out.println("Launching Chrome browser...");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	public static WebDriver initFirefoxDriver() {
		//System.out.println("Launching Firefox browser...");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	public static WebDriver initEdgeDriver() {
		//System.out.println("Launching Firefox browser...");
		WebDriverManager.edgedriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	public static void quit() {
		DriverFactory.getDriver().quit();
    }


}
