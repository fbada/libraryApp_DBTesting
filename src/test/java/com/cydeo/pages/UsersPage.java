package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage extends BasePage {

    public UsersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchUser;

    @FindBy(xpath = "(//tbody//a[@role='button'])[1]")
    public WebElement editUser;

    @FindBy(id = "status")
    public WebElement statusDropdown;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(xpath = " //button[@type=\"submit\"]")
    public WebElement saveChanges;

    @FindBy(css = " .toast-message")
    public WebElement toastMessage;

    @FindBy(id = "user_status")
    public WebElement userStatusDropdown;

    @FindBy(css = ".dataTables_info")
    private WebElement userCount;

    public WebElement editUser(String email) {
        String xpath = "//td[.='" + email + "']/..//a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public  WebElement checkUsername(String user){
        String locator = "//td[normalize-space()='"+user+"']";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

    public WebElement clickModule(String module) {
        String xpath = "//span[normalize-space()='" + module + "']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
}
