package AutomationTraining.Tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestOriginal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("testuser123@mail.com");
		driver.findElement(By.id("userPassword")).sendKeys("TestUser@123");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
//		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'col-lg-4')]"));
//		for(WebElement product : products) {
//			
//			if(product.getText().contains("IPHONE")) {
//				product.findElement(By.xpath(".//div/div/button[2]")).click();
//				System.out.println(product.getText());
//				break;
//			}
//		}

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		Thread.sleep(1000);
		WebElement prod1 = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); will take more time, replaced by the below line
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
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