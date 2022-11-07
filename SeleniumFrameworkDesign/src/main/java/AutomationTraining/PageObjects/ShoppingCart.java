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

public class ShoppingCart extends AbstractComponents {

	WebDriver driver;

	public ShoppingCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink*=cart]")
	WebElement goToCart;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public void goToCart() {
		goToCart.click();
	}

	public void VerifyCartProduct(String productName) {
		boolean cp = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cp);
	}

	public void CheckOut() {

		waitForElementToBeClickable(checkOut);
		checkOut.click();

	}

}
