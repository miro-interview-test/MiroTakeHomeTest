package support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserConfig {
    public static WebDriver driver;
    public String docker;
    public String headless;

    @Before
    public void beforeEach() {
        docker = System.getenv("DOCKER");
        headless = System.getenv("HEADLESS");
        ChromeOptions options = new ChromeOptions();
        if (docker.equals("true")) {
            options.addArguments("--disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security, can run chrome as Root user
            options.addArguments("--headless"); // Bypass OS security, can run chrome as Root user
            options.addArguments("--window-size=1080,720"); // Setting large window size
        } else {
            if (headless.equals("true")) {
                options.addArguments("--headless"); // Bypass OS security, can run chrome as Root user
                options.addArguments("--window-size=1080,720"); // Setting large window size
            }
        }
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // Cleanup Stale Screenshot Files
        String projectPath = System.getProperty("user.dir");
        new File(projectPath+"/src/test/resources/screenshots/user1Board.png").delete();
        new File(projectPath+"/src/test/resources/screenshots/user2Board.png").delete();
    }

    @After
    public void afterEach(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                scenario.log("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots
                        .getMessage());
            }
        }
        driver.quit();
    }

}
