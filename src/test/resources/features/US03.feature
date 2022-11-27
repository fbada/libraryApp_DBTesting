@db
Feature: As a data consumer, I want UI and DB book categories are match.

  @TS4-169
  Scenario: verify book categories with DB
    Given the user logged in as "librarian"
    And the user navigates to "Books" page
    And I take all book categories in UI
    And I execute query to get book categories
    Then verify book categories must match book_categories table from db