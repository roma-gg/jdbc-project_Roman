package cydeo.pages;

import cydeo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BorrowingBooks_Page {
    public BorrowingBooks_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//tr//td[2]")
    private List<WebElement> allBooksNames;

    public List<String> getBorrowedBooksNames() {
        List<String> booksNames = new ArrayList<>();
        for (WebElement book : allBooksNames) {
            booksNames.add(book.getText());
        }

        return booksNames;
    }
}
