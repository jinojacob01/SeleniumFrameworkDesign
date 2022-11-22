package AutomationTraining.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.Debug;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTraining.PageObjects.LandingPage;
import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import AutomationTraining.PageObjects.cartPage;
import AutomationTraining.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "Purchase")

	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		String country = "India";
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("password"));
		pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		ShoppingCart sc = pc.goToCart();
		boolean match = sc.VerifyCartProduct(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage pp = sc.CheckOut();
		pp.selectCountry(country);
		pp.placeTheOrder();
		pp.getOrderId();

	}
	


	@Test(dependsOnMethods = "submitOrder")

	public void OrderHistoryTest() {
		ProductCatalogue pc = lp.loginApplication("testuser123@mail.com", "TestUser@123");
		cartPage cp = pc.goToOrders();
		Assert.assertTrue(cp.VerifyOrdersPage(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\AutomationTraining\\data\\PurchaseOrder.json");
//		System.out.println(data.size());
//		for (HashMap<String, String> element : data) {
//			return new Object[][] { { element }};
//		}
//		return null;

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider  // for reference, this method has been updated with data from json file
//	public Object[][] getData() {
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "testuser123@mail.com");
//		map.put("password", "TestUser@123");
//		map.put("productName", "ADIDAS ORIGINAL");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "testuser123456@mail.com");
//		map1.put("password", "TestUser@123");
//		map1.put("productName", "ZARA COAT 3");
//
//		return new Object[][] { { map }, { map1 } };
//	}

}
