package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US04_Steps {
    BookPage bookPage = new BookPage();
    String bookNameGlobal;
    List<String> uidata;

    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        bookNameGlobal = bookName;
        bookPage.search.sendKeys(bookName);
        BrowserUtil.waitFor(3);
        uidata = bookPage.getBookInfo();
        System.out.println(uidata);

    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        //select name, author,year from books where name='Chordeiles minor';
        String query = "select b.isbn as ISBN, b.name as Book_Name, b.author as Author,bc.name as Category,  b.year as Year\n" +
                "from books b\n" +
                "inner join book_categories bc on b.book_category_id = bc.id\n" +
                "where b.name ='" + bookNameGlobal + "'\n" +
                "limit 1;";

        DB_Util.runQuery(query);

        List<String> dbData = DB_Util.getRowDataAsList(1);
        System.out.println("dbData = " + dbData);

        Assert.assertEquals(uidata, dbData);

    }

}
