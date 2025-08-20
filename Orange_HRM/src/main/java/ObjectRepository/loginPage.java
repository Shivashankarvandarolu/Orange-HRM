package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.driverutility;

public class loginPage {

	private WebDriver driver;
	private driverutility utility;

	public loginPage(WebDriver driver) {
		this.driver = driver;
		this.utility = new driverutility(driver);
		PageFactory.initElements(driver, this); // initializes @FindBy elements

	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement emailtextbox;

	public WebElement getEmailfield() {
		utility.checkElementVisibility(emailtextbox);
		return emailtextbox;
	}

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordtxtbox;

	public WebElement getPasswordfield() {
		utility.checkElementVisibility(passwordtxtbox);
		return passwordtxtbox;
	}

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;

	public WebElement getLoginBtn() {
		utility.checkElementVisibility(loginBtn);
		return loginBtn;
	}
}
