package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US04_Steps {
    BookPage bookPage = new BookPage();
    String bookNameGlobal;

    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        bookNameGlobal = bookName;
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        //select name, author,year from books where name='Chordeiles minor';
        String query = "select name, author,year from books where name='"+bookNameGlobal+"';";
        DB_Util.runQuery(query);

        String actBookName = DB_Util.getCellValue(1, 1);
        Assert.assertEquals(bookNameGlobal, actBookName);
    }

}
