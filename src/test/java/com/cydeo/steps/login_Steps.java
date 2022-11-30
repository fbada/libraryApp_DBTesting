package com.cydeo.steps;

import com.cydeo.pages.LoginPage;
import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class login_Steps {
    LoginPage loginPage = new LoginPage();
    UsersPage usersPage = new UsersPage();
    String actUIData;

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String string, String string2) {
        loginPage.login(string, string2);
        actUIData = string;
    }

    @When("user gets username  from user fields")
    public void user_gets_username_from_user_fields() {
        usersPage.clickModule("Users").click();

    }

    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {
        usersPage.searchUser.sendKeys(actUIData);
        Assert.assertTrue(usersPage.checkUsername(actUIData).isDisplayed());
        String query = "select email from users\n" +
                "where email = '" + actUIData + "';";

        DB_Util.runQuery(query);

        String expData = DB_Util.getFirstRowFirstColumn();

        System.out.println("expData = " + expData);
        System.out.println("actUIData = " + actUIData);
        Assert.assertEquals(expData, actUIData);


    }
}
