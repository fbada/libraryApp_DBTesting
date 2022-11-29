@homework @db @wip
Feature: User Management
Scenario: Inactive users count should be same with database

Given the user logged in as "librarian"
And the user navigates to "Users" page
When the user changes status "ACTIVE" to "INACTIVE"
Then the user should see same number of "INACTIVE" user count in database