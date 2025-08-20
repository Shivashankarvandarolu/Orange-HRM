package Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class driverutility {

	private WebDriver driver;

	public driverutility(WebDriver driver) {
		this.driver = driver;
	}

	public boolean checkElementVisibility(WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
