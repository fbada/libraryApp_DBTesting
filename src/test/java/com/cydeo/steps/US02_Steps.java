package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class US02_Steps {
    DashBoardPage dashBoardPage = new DashBoardPage();
    String borrowedBooks = null;

    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
    borrowedBooks = dashBoardPage.borrowedBooksNumber.getText();

    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");
        String actBorrowedBooks =  DB_Util.getCellValue(1,1);
        Assert.assertEquals(borrowedBooks,actBorrowedBooks);

        System.out.println("booksBorrowed = " + borrowedBooks);
        System.out.println("DB_Util.getFirstRowFirstColumn() = " + DB_Util.getCellValue(1,1));

    }


}
