Feature: As a student, I should be able to borrow a book

	@TS4-161 @db
		Scenario: Student borrow new book
			Given the user logged in as "student"
		    And the user navigates to "Books" page
		    And the user searches book name called "Head First Java"
		    When the user clicks Borrow Book
		    Then verify that book is shown in "Borrowing Books" page
		    And verify logged student has same book in database