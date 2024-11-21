package com.saucedemo.tests;

import com.saucedemo.utils.ScreenshotManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @Step("Pre-condition - set up browser and environment")
    @BeforeMethod(alwaysRun = true)
    @Parameters({"env", "browser", "width", "height"})
    public void setUp(@Optional("prod") String env, @Optional("chrome") String browser, int width, int height) {
        setUpBrowser(browser);
        setUpEnvironment(env);
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setUpBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private void setUpEnvironment(String env) {
        switch (env.toLowerCase()) {
            case "prod":
                driver.get("https://www.saucedemo.com");
                break;
            case "dev":
                driver.get("https://dev.saucedemo.com");
                break;
            case "test":
                driver.get("https://test.saucedemo.com");
                break;
            default:
                throw new IllegalArgumentException("Unsupported environment: " + env);
        }
    }

    @Step("Post-condition - quit driver and take screenshot if test fails")
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotManager.takeScreenshot(driver, result.getName());
        }

        if (driver != null)
            driver.quit();
    }
}
