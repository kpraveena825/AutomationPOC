package APIFramework.org.APIFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected static WebDriver driver;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	WebDriverWait wait;

	protected String env;

	public BasePage() {
		driver = DriverManager.getDriver();
	}

	public void LaunchBrowser(String siteName) {
		if (siteName.equals("Flipkart")) {
			driver.get("https://www.flipkart.com/");
		} else if (siteName.equals("Amazon")) {
			driver.get("https://www.amazon.com/");
		} else {
			System.out.println("Site mentioned is not matchng with the defined sites");
		}
		driver.manage().window().maximize();
	}

	public boolean elementClick(By ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
		return true;
	}

	public boolean sendTextElement(By ele, String text) {
		try {

			driver.findElement(ele).click();
			driver.findElement(ele).sendKeys(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean clickOnElement(By ele) {
		try {
			driver.findElement(ele).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
