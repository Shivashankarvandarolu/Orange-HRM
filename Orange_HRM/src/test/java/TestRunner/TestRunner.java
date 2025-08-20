package TestRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "StepImplementations", "Hooks" }, plugin = { "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // Extent report adapter
}, tags = "@login_test", // <-- Correct placement of tag
		monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeClass(alwaysRun = true)
	@Parameters({"browser", "remote"})
	public void setUpParams(String browser, String remote) {
		if (browser != null && !browser.isEmpty()) {
			System.setProperty("browser", browser);
		}
		if (remote != null && !remote.isEmpty()) {
			System.setProperty("remote", remote);
		}
	}

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
