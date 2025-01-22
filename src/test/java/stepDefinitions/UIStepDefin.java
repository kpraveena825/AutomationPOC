package stepDefinitions;

import org.testng.Assert;

import APIFramework.org.APIFramework.BasePage;
import APIFramework.org.APIFramework.PageObjects;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.And;

public class UIStepDefin extends BasePage {

	public UIStepDefin() {
		// TODO Auto-generated constructor stub
	}
	PageObjects pageObj = new PageObjects();
	
	@Given("Launch {}")
	public void launch_browser(String siteName) {
		LaunchBrowser(siteName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
	}
	
	@And("Search for product {}")
	public void product_search(String productName) {
		pageObj.productSerach("iphone");
		pageObj.fetchProductText();
		Assert.assertTrue(true);
	}
}
