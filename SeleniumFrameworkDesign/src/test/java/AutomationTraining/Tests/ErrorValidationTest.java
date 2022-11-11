package AutomationTraining.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import AutomationTraining.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test

	public void LoginErrorValidation() throws InterruptedException, IOException {
		lp.loginApplication("testuser123@mail.com", "TestUser@");
		Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

	}

	@Test

	public void submitOrder() throws InterruptedException, IOException {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue pc = lp.loginApplication("testuser123456@mail.com", "TestUser@123");
		pc.getProductList();
		pc.addProductToCart(productName);
		ShoppingCart sc = pc.goToCart();
		boolean match = sc.VerifyCartProduct("ADIDAS");
		Assert.assertFalse(match);

	}

}
