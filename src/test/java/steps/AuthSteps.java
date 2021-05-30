package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import support.BrowserConfig;

public class AuthSteps {

    public WebDriver driver = BrowserConfig.driver;
    protected LoginPage login = new LoginPage(driver);

    @Given("I am on miro login page")
    public void i_am_on_miro_login_page() {
        driver.get("https://miro.com/login/");
    }

    @Given("I login to miro as {string}")
    public void i_login_to_miro_as(String string) {
        login.loginToMiro(string);
    }

    @Then("I logout from miro")
    public void i_logout_from_miro() {
        driver.get("https://miro.com/logout");
    }

}
