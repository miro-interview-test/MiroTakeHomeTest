Feature: As a user, I verify stickers are shared with another user if my board is shared

Background:
  Given I am on miro login page
  And I login to miro as "user1"
  And I perform a cleanup of stale boards

Scenario:
  Given I create a new board
  And I rename the board to "Board"
  And I share the board with user2
  When I add a new sticker
  Then I logout from miro
  Given I am on miro login page
  And I login to miro as "user2"
  When I navigate to the board
  Then I should see the sticker