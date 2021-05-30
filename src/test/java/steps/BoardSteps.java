package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.BoardPage;
import pages.DashboardPage;
import support.BrowserConfig;

public class BoardSteps {

    public WebDriver driver = BrowserConfig.driver;
    protected DashboardPage dashboard = new DashboardPage(driver);
    protected BoardPage board = new BoardPage(driver);

    @Given("I create a new board")
    public void i_create_a_new_board() {
        dashboard.createNewBoard();
    }

    @And("I rename the board to {string}")
    public void i_rename_the_board_to(String boardName) {
        board.renameBoardTo(boardName);
    }

    @When("I share the board with user2")
    public void i_share_the_board_with_user2() {
        board.shareBoardWithUser2();
    }

    @When("I navigate to the board")
    public void i_navigate_to_the_board() {
        dashboard.navigateToBoard();
    }

    @And("I perform a cleanup of stale boards")
    public void i_perform_a_cleanup_of_stale_boards() {
        dashboard.deleteStaleBoards();
    }

}
