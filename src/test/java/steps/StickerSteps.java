package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.BoardPage;
import pages.DashboardPage;
import support.BrowserConfig;

import java.io.IOException;

public class StickerSteps {

    public WebDriver driver = BrowserConfig.driver;
    protected DashboardPage dashboard = new DashboardPage(driver);
    protected BoardPage board = new BoardPage(driver);

    @When("I add a new sticker")
    public void i_add_a_new_sticker() throws InterruptedException, IOException {
        board.addNewSticker();
    }

    @Then("I should see the sticker")
    public void i_should_see_the_sticker() throws IOException {
        board.verifyStickerPresence();
    }
}
