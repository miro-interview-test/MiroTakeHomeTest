package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Utilities;
import java.io.IOException;
import java.util.List;

public class BoardPage {
    public WebDriver driver;
    public BoardPage(WebDriver driver) { this.driver = driver; }

    // Title Elements
    private WebElement boardTitle() { return driver.findElement(By.className("board-top-left-panel__title")); }
    private WebElement titleField() { return driver.findElement(By.cssSelector("[data-auto-test-id=\"board-info-modal-title\"]")); }
    private List<WebElement> notificationCloseButtons() { return driver.findElements(By.className("notification__close")); }

    // Share Elements
    private WebElement boardShareButton() { return driver.findElement(By.cssSelector("[data-autotest-id='board__share-button--desktop']")); }
    private WebElement teamShareButton() { return driver.findElement(By.cssSelector("[data-auto-test-id=\"shareMdButtonTeamContacts\"]")); }
    private WebElement teamMemberName() { return driver.findElement(By.className("filterable-list__item")); }
    private WebElement teamMemberChooseButton() { return driver.findElement(By.id("inviteFromTeamMdButtonChoose")); }
    private WebElement boardShareSendInvite() { return driver.findElement(By.cssSelector("[data-autotest-id='shareMdButtonSend']")); }
    private WebElement viewSuggestionPopupCancelButton() { return driver.findElement(By.className("rtb-btn--secondary-bordered")); }

    // Board Elements
    private WebElement toolbarStickerIcon() { return driver.findElement(By.className("toolbar__item--stickers")); }
    private List<WebElement> stickersList() { return driver.findElements(By.cssSelector(".toolbar__panel--stickers .toolbar__panel__item")); }
    private WebElement mainBodyElement() { return driver.findElement(By.tagName("body")); }
    private WebElement toolbarZoomResetTo100() { return driver.findElement(By.className("mini-map__value")); }
    private WebElement boardCanvas() { return driver.findElement(By.cssSelector("#pixiCanvasContainer canvas")); }
    private WebElement toastNotification() { return driver.findElement(By.cssSelector("[data-autotest-id=\"feed-toast\"]")); }

    public void renameBoardTo(String boardName) {
        boardTitle().click(); // Click on board title to edit
        titleField().clear(); // Using Javascript workaround as normal clear method doesn't seem to be working on this field
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByClassName(\"board-info-title\")[0].value=\"\"");
        titleField().sendKeys(boardName); // Enter new title
        Utilities.hitEscapeKey(driver); // Dismiss the edit title as its auto save
    }

    public void shareBoardWithUser2() {
        boardShareButton().click(); // Click on share board
        teamShareButton().click(); // Click on the share with team
        teamMemberName().click(); // Click on the pre-added user2 in team
        teamMemberChooseButton().click(); // Confirm choice of choosing team member
        boardShareSendInvite().click(); // Click on Send Invite button
        viewSuggestionPopupCancelButton().click(); // Dismiss the View Suggestion
    }

    public void addNewSticker() throws IOException {
        toolbarStickerIcon().click(); // Click on Sticker Icon
        stickersList().get(stickersList().size()-1).click(); // Get the last Sticker from List (Black for good contrast)
        mainBodyElement().click(); // Click on center of screen using body element
        if (notificationCloseButtons().size()>0) { notificationCloseButtons().get(0).click(); } // Close Slack notification if present
        driver.navigate().refresh(); // Refresh the page to remove all edit bars, can also do it by clicking cursor icon
        toolbarZoomResetTo100().click(); // Reset Zoom to 100%
        WebDriverWait wait = new WebDriverWait(driver, 10); // WebDriver wait object with 10 seconds wait
        wait.until(ExpectedConditions.textToBePresentInElement(toolbarZoomResetTo100(), "100%")); // Wait till zoom animation finishes
        Utilities.takeElementScreenshot(driver, boardCanvas(), "user1Board"); // Take element screenshot of only canvas element
    }

    public void verifyStickerPresence() throws IOException {
        WebDriverWait wait = new WebDriverWait(driver, 10); // WebDriver wait object with 10 seconds wait
        Assert.assertTrue(boardTitle().isDisplayed()); // Verify user of correct board by title
        toolbarZoomResetTo100().click(); // Reset Zoom to 100%
        wait.until(ExpectedConditions.textToBePresentInElement(toolbarZoomResetTo100(), "100%")); // Wait till zoom animation finishes
        Assert.assertTrue(toolbarStickerIcon().isDisplayed()); // Assert Board elements are loaded by verifying something from toolbar
        Utilities.takeElementScreenshot(driver, boardCanvas(), "user2Board"); // Take element screenshot of only canvas element
        Double differencePercent = Utilities.compareImagePixels(); // Get difference percentage
        System.out.println(differencePercent);
        Assert.assertTrue(differencePercent<0.5); // Assert 2 pages should not have variance more than 0.5%
    }
}
