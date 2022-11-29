package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class US06_Steps {
    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        BrowserUtil.waitForClickablility(bookPage.addBook, 3).click();

    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String string) {
        bookPage.bookName.sendKeys(string);
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {
        bookPage.isbn.sendKeys(string);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {
        bookPage.year.sendKeys(string);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String string) {
        bookPage.author.sendKeys(string);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String string) {
        Select select = new Select(bookPage.dropDownGenre);
        select.selectByVisibleText(string);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        BrowserUtil.waitForClickablility(bookPage.saveBook, 3).click();
    }

    @Then("the librarian verify new book by {string}")
    public void the_librarian_verify_new_book_by(String string) {
        Assert.assertTrue(bookPage.createdVerifyTxt.isDisplayed());

    }


    @Then("the librarian verify new book from database by {string}")
    public void theLibrarianVerifyNewBookFromDatabaseBy(String title) {

        String query="select name from books where name = '"+title+"'";
        DB_Util.runQuery(query);

        String expTitle = title,
                actTile = DB_Util.getCellValue(1, 1);

        System.out.println("actTile = " + actTile);
        System.out.println("expTitle = " + expTitle);

        Assert.assertEquals(actTile, expTitle);


    }


}