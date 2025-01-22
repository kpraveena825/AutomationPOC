package APIFramework.org.APIFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
	static WebDriver driver;

	public static void initialize() {
		System.setProperty("webdriver.chrome.driver", "C://WebDriver//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			initialize();
		}
		return driver;

	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
