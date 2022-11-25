package AutomationTraining.PageObjects;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.github.dockerjava.api.model.Config;

import AutomationTraining.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder = 'Select Country']")
	WebElement Country;

	@FindBy(css = "[class*='results'] button")
	List<WebElement> suggestion;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	@FindBy(css = "tbody tr:nth-child(3) td [class = 'ng-star-inserted']")
	List<WebElement> orders;
	
	@FindBy(css=".hero-primary")
	WebElement conirmMessage;

	public void selectCountry(String country) {
		Country.sendKeys(country);
		WebElement sug = suggestion.stream().filter(s -> s.getText().equalsIgnoreCase(country)).findFirst()
				.orElse(null);
		sug.click();
	}

	public void placeTheOrder() throws InterruptedException {
		scrollWindow();
		waitForElementToBeClickable(placeOrder);
		Thread.sleep(1000);
		placeOrder.click();
	}
	
	public String getConfirmationMessage() {
		return conirmMessage.getText();
	}
	

	public void getOrderId() {
		orders.stream().forEach(s -> System.out.println(s.getText()));
	}

}
