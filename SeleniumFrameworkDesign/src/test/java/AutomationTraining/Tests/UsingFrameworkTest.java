package AutomationTraining.Tests;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import AutomationTraining.PageObjects.LandingPage;
import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingFrameworkTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = null;
		
		
		String productName = "ADIDAS ORIGINAL";
		String country = "India";
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
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
		driver.quit();
	}

}
