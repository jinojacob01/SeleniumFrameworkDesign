package AutomationTraining.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTraining.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;

	public void loginApplication(String useremail, String password) {

		userEmail.sendKeys(useremail);
		userEmail.sendKeys(password);
		submit.click();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}