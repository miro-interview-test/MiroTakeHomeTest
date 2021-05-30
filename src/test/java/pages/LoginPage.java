package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.Utilities;

import java.util.Properties;

public class LoginPage {
    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement loginEmail() { return driver.findElement(By.id("email")); }
    private WebElement loginPassword() { return driver.findElement(By.id("password")); }
    private WebElement loginButton() { return driver.findElement(By.className("signup__submit")); }

    public void loginToMiro(String userType) {
        Properties prop = Utilities.loadUserProperties();
        String email = prop.getProperty(userType+"Email");
        String password = prop.getProperty(userType+"Password");
        loginEmail().clear();
        loginEmail().sendKeys(email);
        loginPassword().sendKeys(password);
        loginButton().click();
    }

}
