package StepImplementations;

import java.util.logging.LogManager;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DriverFactory.DriverFactory;
import ObjectRepository.loginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_StepDef {

	DriverFactory driverFactory;
	private loginPage login;
	WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(Login_StepDef.class);

	public Login_StepDef() {
		this.driver = DriverFactory.getDriver(); // Initialize WebDriver properly
		this.driverFactory = new DriverFactory();
		this.login = new loginPage(driver);
	}

	@When("^I navigate to the login page$")
	public void I_navigate_to_the_login_page() {

		String currentURL = driver.getCurrentUrl();
		String expURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

		if (currentURL.equals(expURL)) {
			logger.info("User is on Login page as expected. URL matched: {}", currentURL);
		} else {
			logger.warn("Unexpected URL. Expected: {}, Found: {}", expURL, currentURL);
		}
	}

	@Then("^I should see the email textbox and password textbox displayed$")
	public void I_should_see_the_email_textbox_and_password_textbox_displayed() {

		if (login.getEmailfield().isDisplayed() && login.getPasswordfield().isDisplayed()) {
			logger.info("Email and Password textboxes are displayed as expected.");
		} else {
			logger.error("Either Email or Password textbox is not displayed!");
		}
	}

	@When("^I enter email '(.+)' and password '(.+)'$")
	public void I_enter_email_and_password(String email, String password) {

		login.getEmailfield().sendKeys(email);
		logger.info("Entered email: {}", email);

		login.getPasswordfield().sendKeys(password);
		logger.info("Entered password: {}", password); // You may want to mask this in real projects
	}

	@Then("^I click on login button$")
	public void I_click_on_login_button() {

		if (login.getLoginBtn().isDisplayed() && login.getLoginBtn().isEnabled()) {
			logger.info("Login button is displayed and enabled.");
		} else {
			logger.error("Login button is either not displayed or disabled!");
		}

		login.getLoginBtn().click();
		logger.info("Clicked on login button.");
	}
}
