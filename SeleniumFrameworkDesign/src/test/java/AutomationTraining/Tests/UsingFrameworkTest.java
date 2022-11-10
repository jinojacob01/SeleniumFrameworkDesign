package AutomationTraining.Tests;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.Debug;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTraining.PageObjects.LandingPage;
import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import AutomationTraining.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingFrameworkTest extends BaseTest {

	@Test

	public void submitOrder() throws InterruptedException, IOException {

		String productName = "ADIDAS ORIGINAL";
		String country = "India";
		LandingPage lp = lauchApplication();
		ProductCatalogue pc = lp.loginApplication("testuser123@mail.com", "TestUser@123");
		pc.getProductList();
		pc.addProductToCart(productName);
		ShoppingCart sc = pc.goToCart();
		boolean match = sc.VerifyCartProduct(productName);
		Assert.assertTrue(match);
		PaymentPage pp = sc.CheckOut();
		pp.selectCountry(country);
		pp.placeTheOrder();
		pp.getOrderId();
		driver.close();

	}
}
