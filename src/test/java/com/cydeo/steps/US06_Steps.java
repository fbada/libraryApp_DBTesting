package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US06_Steps {
    BookPage bookPage = new BookPage();
    List<String> uidata = new ArrayList<>();
    List<String> uidataSearchRes = new ArrayList<>();
    List<String> DBdata = new ArrayList<>();
    String stringTitle;
    String stringISBN;
    String stringAuthor;
    String stringCategory;
    String year;


    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        BrowserUtil.waitForClickablility(bookPage.addBook, 3).click();

    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String string) {

        bookPage.bookName.sendKeys(string);
        stringTitle = string;
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {

        bookPage.isbn.sendKeys(string);
        stringISBN = string;
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {
        year = string;
        bookPage.year.sendKeys(string);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String string) {
        stringAuthor = string;
        bookPage.author.sendKeys(string);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String string) {
        Select select = new Select(bookPage.dropDownGenre);
        stringCategory = string;
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        BrowserUtil.waitForClickablility(bookPage.saveBook, 3).click();

        uidata.add(stringISBN);
        uidata.add(stringTitle);
        uidata.add(stringAuthor);
        uidata.add(stringCategory);
        uidata.add(year);

    }

    @Then("the librarian verify new book by {string}")
    public void the_librarian_verify_new_book_by(String string) {
        Assert.assertTrue(bookPage.createdVerifyTxt.isDisplayed());
        bookPage.search.sendKeys(stringTitle);
        BrowserUtil.waitFor(2);

        uidataSearchRes = bookPage.getBookInfo();
        System.out.println(uidataSearchRes);
        System.out.println(uidata);
        Assert.assertEquals(uidata, uidataSearchRes);

    }


    @Then("the librarian verify new book from database by {string}")
    public void theLibrarianVerifyNewBookFromDatabaseBy(String title) {

        String query = "select b.isbn as ISBN, b.name as Book_Name, b.author as Author,bc.name as Category,  b.year as Year\n" +
                "from books b\n" +
                "inner join book_categories bc on b.book_category_id = bc.id\n" +
                "where b.name ='" + stringTitle + "' and b.isbn ='" + stringISBN + "'\n" +
                "limit 1;";

        DB_Util.runQuery(query);


        DBdata = DB_Util.getRowDataAsList(1);
        Assert.assertEquals(DBdata, uidataSearchRes);

        System.out.println(DBdata);


    }


}