@db
Feature: As a librarian, I want to know borrowed books number

  @TS4-167 @libraryTestSuite
  Scenario: Verify the total amount of borrowed books
    Given the user logged in as "librarian"
    When user take borrowed books number
    Then borrowed books number information must match with DB