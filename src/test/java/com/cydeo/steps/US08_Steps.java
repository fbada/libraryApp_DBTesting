package com.cydeo.steps;

import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class US08_Steps {
    UsersPage usersPage = new UsersPage();
    String user = ConfigurationReader.getProperty("librarian_username");

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        usersPage.searchUser.sendKeys(user);
        BrowserUtil.waitFor(2);
        usersPage.editUser.click();
    }

    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String string, String string2) {
        BrowserUtil.waitFor(2);
        Select select = new Select(usersPage.statusDropdown);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), string);
        select.selectByVisibleText(string2);
        BrowserUtil.waitFor(2);

    }

    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        usersPage.saveChanges.click();
        BrowserUtil.waitFor(2);
    }

    @Then("{string} message should appear")
    public void message_should_appear(String string) {
        String act = usersPage.toastMessage.getText();
        Assert.assertEquals(string, act);
    }

    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {

        String query = "select status from users\n" +
                "where email = 'librarian43@library';";
        DB_Util.runQuery(query);

        String dBvalue = DB_Util.getFirstRowFirstColumn();
        System.out.println("dBvalue for STATUS = " + dBvalue);
    }

    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String string, String string2) {

        BrowserUtil.waitFor(2);
        Select select = new Select(usersPage.userStatusDropdown);
        select.selectByVisibleText(string);
        BrowserUtil.waitFor(2);
        usersPage.searchUser.sendKeys(user);
        BrowserUtil.waitFor(2);
        usersPage.editUser.click();
        BrowserUtil.waitFor(2);
        Select sel = new Select(usersPage.statusDropdown);
        sel.selectByVisibleText(string2);
        BrowserUtil.waitFor(2);
        usersPage.saveChanges.click();
        String act = usersPage.toastMessage.getText();
        BrowserUtil.waitFor(2);

        String query = "select status from users\n" +
                "where email = 'librarian43@library';";
        DB_Util.runQuery(query);

        String dBvalue = DB_Util.getFirstRowFirstColumn();
        System.out.println("dBvalue for STATUS = " + dBvalue);

        Assert.assertEquals(string2, dBvalue);
    }

}
