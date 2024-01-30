package cydeo.pages;

import cydeo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home_Page {
    public Home_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "borrowed_books")
    private WebElement borrowedBooksQuantity;
    private By borrowedBooksQuantityLocator = By.id("borrowed_books");

    @FindBy(xpath = "//a[@href='#books']")
    private WebElement booksPageLink;

    @FindBy(id = "navbarDropdown")
    private WebElement profileDropDown;

    @FindBy(xpath = "//a[contains(text(), 'Log Out')]")
    private WebElement logoutButton;

    public void logout() {
        profileDropDown.click();
        logoutButton.click();
    }



    public WebElement getButton(String button) {
        if (button.equals("Books"))
            return booksPageLink;
        else
            throw new IllegalArgumentException();
    }

    public WebElement getBorrowedBooksQuantity() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(borrowedBooksQuantityLocator, "0")));
        return borrowedBooksQuantity;
    }
}
