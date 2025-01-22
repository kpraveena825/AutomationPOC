package APIFramework.org.APIFramework;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjects extends BasePage {

	@FindBy(xpath = "//input[@class=\"Pke_EE\"]")
	WebElement serachInputbox;

	public static By searchBox = By.xpath("//input[@class=\"Pke_EE\"]");

	public static By searchButton = By.xpath("//button[@class=\"_2iLD__\"]");
	public static By listOfProduct = By.xpath("//div[@class=\"KzDlHZ\"]");
	public static By priceOfProduct = By.xpath("//div[@class=\"Nx9bqj _4b5DiR\"]");

	public void productSerach(String product) {
		sendTextElement(searchBox, product);
		clickOnElement(searchButton);
	}

	public void fetchProductText() {
		Map<String, String> map = new HashMap<String, String>(); 
		int size = driver.findElements(listOfProduct).size();
		for (int i = 0; i <= size - 1; i++) {
			map.put(driver.findElement(listOfProduct).getText(), driver.findElement(priceOfProduct).getText()); 
		    System.out.println(map);																								
		}
	}
	
}
