package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(name = "name")
    public WebElement bookName;

    @FindBy(xpath = "//thead//tr//th[contains(@aria-label,'Borrowed')]/.")
    public WebElement sortByAscBorrower;

    @FindBy(xpath = "//input[@name='author']")
    public WebElement author;

    @FindBy(xpath = "//div[@class='toast toast-success']")
    public WebElement successBorrowBanner;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "description")
    public WebElement description;

    @FindBy(xpath = "//span[normalize-space()='Borrowing Books']")
    public WebElement borrowBookModule;

    @FindBy(xpath = "//a[.=' Add Book']")
    public WebElement addBook;

    @FindBy(xpath = "//select[@id='book_group_id']")
    public WebElement dropDownGenre;

    @FindBy(xpath = "//button[contains(.,'changes')]")
    public WebElement saveBook;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement createdVerifyTxt;

    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement searchTitle(String title){

        String locator = "//td[normalize-space()='"+title+"']";
                return Driver.getDriver().findElement(By.xpath(locator));
    }

}
