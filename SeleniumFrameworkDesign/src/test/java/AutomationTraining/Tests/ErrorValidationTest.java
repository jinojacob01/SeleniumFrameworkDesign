package AutomationTraining.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTraining.PageObjects.PaymentPage;
import AutomationTraining.PageObjects.ProductCatalogue;
import AutomationTraining.PageObjects.ShoppingCart;
import AutomationTraining.TestComponents.BaseTest;
import AutomationTraining.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)

	public void LoginErrorValidation() throws InterruptedException, IOException {
		lp.loginApplication("testuser123@mail.com", "TestUser@");
		Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

	}

	@Test

	public void VerifyOrder() throws InterruptedException, IOException {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue pc = lp.loginApplication("testuser123456@mail.com", "TestUser@123");
		pc.getProductList();
		pc.addProductToCart(productName);
		ShoppingCart sc = pc.goToCart();
		boolean match = sc.VerifyCartProduct("ADIDAS ORIGINAL");
		Assert.assertTrue(match);

	}

}
