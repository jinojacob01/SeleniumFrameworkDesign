package AutomationTraining;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
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
		
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	}

}
