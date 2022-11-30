package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03_Steps {
    BookPage bookPage = new BookPage();
    List<String> uiList;
    List<String> databaseList;

    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        uiList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        uiList.remove(0);
        System.out.println("actualCategories = " + uiList);

    }

    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
        String query = "select name from book_categories";
        DB_Util.runQuery(query);
        databaseList = DB_Util.getColumnDataAsList(1);
        System.out.println("databaseList = " + databaseList);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        Assert.assertEquals(uiList, databaseList);
    }

}
