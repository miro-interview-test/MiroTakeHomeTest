package pages;

import org.openqa.selenium.*;
import support.Utilities;

import java.util.List;

public class DashboardPage {
    public WebDriver driver;
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement newBoardPlusButton() { return driver.findElement(By.className("serviceCard__tile-3JdsV")); }
    private WebElement createBoardButton() { return driver.findElement(By.cssSelector("button.rtb-btn--primary.rtb-btn--medium")); }
    private WebElement templatePickerCloseButton() { return driver.findElement(By.cssSelector("[data-autotest-id='template-picker__close-button']")); }

    private List<WebElement> listOfBoards() { return driver.findElements(By.className("board-item-container")); }
    private List<WebElement> menuItems() { return driver.findElements(By.className("context-menu__item")); }
    private WebElement confirmBoardDeleteButton() { return driver.findElement(By.cssSelector("[data-autotest-id=\"confirmation-modal__submit-button\"]")); }


    public void createNewBoard() {
        newBoardPlusButton().click(); // Click on board + button
        createBoardButton().click(); // Click on create board button
        templatePickerCloseButton().click(); // Dismiss the template picker
    }

    public void navigateToBoard() {
        listOfBoards().get(0).click(); // Click on the first board
        driver.navigate().refresh(); // Refresh the page to remove notifications before screenshot
    }

    public void deleteStaleBoards() {
        for (WebElement board : listOfBoards()) {
            Utilities.rightClick(driver, board);
            menuItems().get(menuItems().size() - 1).click(); // Last menu item is Delete, no better locator :(
            confirmBoardDeleteButton().click();
        }
    }
}
