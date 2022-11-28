package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.BorrowBooksPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US07_Steps {
    BookPage bookPage = new BookPage();
    BorrowBooksPage borrowBooksPage = new BorrowBooksPage();
    String bookName = null;

    @When("the user searches book name called {string}")
    public void the_user_searches_book_name_called(String string) {
        bookPage.search.sendKeys(string);
        bookName = string;
        BrowserUtil.waitFor(4);
    }

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        bookPage.sortByAscBorrower.click();
        BrowserUtil.waitFor(2);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 4).click();
        Assert.assertTrue(bookPage.successBorrowBanner.isDisplayed());
    }

    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String string) {
        bookPage.borrowBookModule.click();
        String actBookName = borrowBooksPage.borrowBook(bookName).getText();
        System.out.println("actBookName = " + actBookName);
        BrowserUtil.waitFor(2);
        Assert.assertTrue(borrowBooksPage.borrowBook(bookName).isDisplayed());
    }

    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        String query = "select full_name, b.name, bb.borrowed_date, bb.is_returned\n" +
                "from users u\n" +
                "         inner join book_borrow bb on u.id = bb.user_id\n" +
                "         inner join books b on bb.book_id = b.id\n" +
                "where full_name = '" + ConfigurationReader.getProperty("fullName_student") +
                "' and b.name = '" + bookName + "'\n" +
                "and bb.is_returned = 0\n" +
                "order by 3 desc;";

        DB_Util.runQuery(query);
        System.out.println("DB_Util.getFirstRowFirstColumn() = " + DB_Util.getFirstRowFirstColumn());
        System.out.println("DB_Util.getCellValue(1,2) = " + DB_Util.getCellValue(1, 2));

        Assert.assertEquals(ConfigurationReader.getProperty("fullName_student"), DB_Util.getFirstRowFirstColumn());
        Assert.assertEquals(bookName, DB_Util.getCellValue(1, 2));


    }
}
