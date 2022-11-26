package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BorrowBooksPage extends BasePage {

    public BorrowBooksPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public WebElement borrowBook(String bookName) {
        String xpath = "//td[text()='"+bookName+"']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
}

