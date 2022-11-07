package AutomationTraining;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationTraining.PageObjects.LandingPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		String productName = "ADIDAS ORIGINAL";
		LandingPage lp = new LandingPage(driver);
//		lp.goTo();
		
		lp.loginApplication("testuser123@mail.com", "TestUser@123");
		ProductCatalogue pc = new ProductCatalogue(driver);
		List<WebElement> products =  pc.getProductList();
		pc.addProductToCart(productName);
		

		
		
		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
		List<WebElement> cart = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean cp = cart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(cp);
//		System.out.println("Success");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			Thread.sleep(1000);

			WebElement element = driver.findElement(By.cssSelector(".subtotal"));
			Actions actions = new Actions(driver);
			actions.scrollToElement(element).perform();
			Thread.sleep(1000);
//			jse.executeScript("window.scrollBy(0,3000)");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".totalRow button"))))
					.click();
//			wait.until(ExpectedConditions.elementToBeClickable(
//				driver.findElement(By.xpath("//div[contains(@class,'subtotal')]/ul/li[3]/button")))).click();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("Waiting 8 seconds");
			Thread.sleep(8000);
		}
//		driver.findElement(By.cssSelector(".subtotal ul li button")).click();
		driver.findElement(By.cssSelector("[placeholder = 'Select Country']")).sendKeys("Ind");
		List<WebElement> suggestion = driver.findElements(By.cssSelector("[class*='results'] button"));
		WebElement sug = suggestion.stream().filter(s -> s.getText().equalsIgnoreCase("India")).findFirst()
				.orElse(null);
		sug.click();
//		System.out.println("reached");
		Thread.sleep(1000);
		jse.executeScript("window.scrollBy(0,2000)");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".action__submit"))))
				.click();
		List<WebElement> orders = driver
				.findElements(By.cssSelector("tbody tr:nth-child(3) td [class = 'ng-star-inserted']"));
		orders.stream().forEach(s -> System.out.println(s.getText()));
	}

}
