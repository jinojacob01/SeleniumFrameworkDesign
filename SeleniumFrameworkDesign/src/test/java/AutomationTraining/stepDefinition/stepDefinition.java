package AutomationTraining.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import AutomationTraining.PageObjects.LandingPage;
import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import AutomationTraining.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition extends BaseTest {

	LandingPage lp;
	ProductCatalogue pc;
	ShoppingCart sc;
	PaymentPage pp;

	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		lp = lauchApplication();

	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_password(String email, String password) {
		pc = lp.loginApplication(email, password);
	}

	@When("^I add product (.+) to the cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		pc.getProductList();
		pc.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_product_and_submit_the_order(String productName) throws InterruptedException {
		sc = pc.goToCart();
		boolean match = sc.VerifyCartProduct(productName);
		Assert.assertTrue(match);
		pp = sc.CheckOut();
		pp.selectCountry("India");
		pp.placeTheOrder();
	}

	@Then("{string} message is displayed on the confirmationpage")
	public void Confirmation_message_is_displayed(String string) {
		String confirmationMessage = pp.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.quit();
	}

}
