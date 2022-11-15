package AutomationTraining.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AutomationTraining.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> OrderProducts;


	public boolean VerifyOrdersPage(String productName) {
		boolean match = OrderProducts.stream().anyMatch(OrderProduct -> OrderProduct.getText().equalsIgnoreCase(productName));
		return match;
	}


}
