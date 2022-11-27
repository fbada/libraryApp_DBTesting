Feature: As a data consumer, I want UI and DB book information are match.

  @TS4-171 @db
  Scenario: Verify book information with DB
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    When I open book "Chordeiles minor"
    Then book information must match the Database

