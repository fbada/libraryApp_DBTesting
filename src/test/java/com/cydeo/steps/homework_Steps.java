package com.cydeo.steps;

import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class homework_Steps {
    UsersPage usersPage = new UsersPage();
    String userNums;

    @When("the user changes status {string} to {string}")
    public void the_user_changes_status_to(String string, String string2) {

        Select sel = new Select(usersPage.activeInactiveDropdown);
        sel.selectByVisibleText(string2);
        BrowserUtil.waitFor(2);

        BrowserUtil.scrollToElement(usersPage.userNums);
        String[] users = usersPage.userNums.getText().split(" ");
        userNums = users[5];
        System.out.println(userNums);

    }

    @Then("the user should see same number of {string} user count in database")
    public void the_user_should_see_same_number_of_user_count_in_database(String string) {

        String query = "select  count(*) from users\n" +
                "where status='INACTIVE';";
        DB_Util.runQuery(query);
        String dbINactiveUsrs = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(userNums, dbINactiveUsrs);
    }
}
