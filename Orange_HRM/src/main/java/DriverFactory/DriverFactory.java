package DriverFactory;

import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import Utility.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    /**
     * Initializes WebDriver based on browser and execution type (local/remote)
     * 
     * @param browser  - browser name ("chrome", "firefox")
     * @param isRemote - true if running on Selenium Grid, false for local
     * @return WebDriver instance
     */
    public static WebDriver initDriver(String browser, boolean isRemote) {
        try {
            log.info("Initializing WebDriver for browser: {}", browser);

            if (isRemote) {
                log.info("Running tests on remote WebDriver Grid.");
                URL gridUrl = new URL(ConfigReader.get("gridUrl")); // gridUrl must be in config.properties

                switch (browser.toLowerCase()) {
                    case "chrome":
                        ChromeOptions chromeOptionsRemote = new ChromeOptions();
                        chromeOptionsRemote.addArguments("--start-maximized");
                        driver.set(new RemoteWebDriver(gridUrl, chromeOptionsRemote));
                        log.info("Chrome browser launched on Selenium Grid.");
                        break;

                    case "firefox":
                        FirefoxOptions firefoxOptionsRemote = new FirefoxOptions();
                        driver.set(new RemoteWebDriver(gridUrl, firefoxOptionsRemote));
                        log.info("Firefox browser launched on Selenium Grid.");
                        break;

                    default:
                        throw new IllegalArgumentException("Browser not supported for remote: " + browser);
                }

            } else {
                switch (browser.toLowerCase()) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptionsLocal = new ChromeOptions();
                        chromeOptionsLocal.addArguments("--start-maximized");
                        driver.set(new ChromeDriver(chromeOptionsLocal));
                        log.info("Chrome browser launched locally.");
                        break;

                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions firefoxOptionsLocal = new FirefoxOptions();
                        driver.set(new FirefoxDriver(firefoxOptionsLocal));
                        log.info("Firefox browser launched locally.");
                        break;

                    default:
                        throw new IllegalArgumentException("Browser not supported: " + browser);
                }
            }

        } catch (Exception e) {
            log.error("Failed to initialize WebDriver for browser: " + browser, e);
            throw new RuntimeException("Driver initialization failed: " + e.getMessage());
        }
        return getDriver();
    }

    /**
     * Gets the current WebDriver instance from ThreadLocal
     * 
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the WebDriver and removes it from ThreadLocal
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Closing the browser...");
            driver.get().quit();
            driver.remove();
            log.info("Browser closed successfully.");
        }
    }
}
