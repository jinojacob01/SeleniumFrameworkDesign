package AutomationTraining;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v104.browser.Browser;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationTraining.PageObjects.LandingPage;
import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingFrameworkTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		String productName = "ADIDAS ORIGINAL";
		String country = "India";
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
		lp.loginApplication("testuser123@mail.com", "TestUser@123");
		ProductCatalogue pc = new ProductCatalogue(driver);
		pc.getProductList();
		pc.addProductToCart(productName);
		ShoppingCart sc = new ShoppingCart(driver);
		sc.goToCart();
		sc.VerifyCartProduct(productName);
		sc.CheckOut();
		PaymentPage pp = new PaymentPage(driver);
		pp.selectCountry(country);
		pp.placeTheOrder();
		pp.getOrderId();
		driver.quit();
	}

}
