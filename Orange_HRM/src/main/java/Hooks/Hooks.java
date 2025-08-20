package Hooks;

import DriverFactory.DriverFactory;
import Utility.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void setUp() {
		String browser = System.getProperty("browser", ConfigReader.get("browser"));
		String remote = System.getProperty("remote", ConfigReader.get("remote"));
		boolean isRemote = remote != null && remote.trim().equalsIgnoreCase("true");
		DriverFactory.initDriver(browser, isRemote);
		DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
	}

	@After
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
