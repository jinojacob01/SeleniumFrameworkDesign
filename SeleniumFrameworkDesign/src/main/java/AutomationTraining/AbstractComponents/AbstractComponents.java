package AutomationTraining.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	public void waitForElementToAppearBy(By FindBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));

	}

	public void waitForElementToDisappearBy(WebElement FindBy) {
		wait.until(ExpectedConditions.invisibilityOf(FindBy));
	}

}
