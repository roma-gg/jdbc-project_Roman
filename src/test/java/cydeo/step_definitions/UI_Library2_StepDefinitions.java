package cydeo.step_definitions;

import cydeo.pages.Books_Page;
import cydeo.pages.BorrowingBooks_Page;
import cydeo.pages.Home_Page;
import cydeo.utilities.DB_Utility;
import cydeo.utilities.Driver;
import cydeo.utilities.UI_Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class UI_Library2_StepDefinitions {

    Books_Page booksPage = new Books_Page();

    @Given("the {string} on the home page")
    public void theOnTheHomePage(String userType) {
        UI_Utility.login(userType);
    }

    @When("the librarian gets borrowed books number")
    public void theLibrarianGetsBorrowedBooksNumber() {
        var pom = new Home_Page();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));

        wait.until(ExpectedConditions.visibilityOf(pom.getBorrowedBooksQuantity()));
    }

    @Then("borrowed books number information must match with DB")
    public void borrowedBooksNumberInformationMustMatchWithDB() {
        var pom = new Home_Page();
        var UIbooksQuantity = pom.getBorrowedBooksQuantity().getText();

        DB_Utility.createConnectionLibraryDB();
        DB_Utility.runQuery("select count(*) from book_borrow\n" +
                                "where is_returned = 0");
        var DBbooksQuantity = DB_Utility.getCellValue(1, 1);

        DB_Utility.closeConnection();

        Assert.assertEquals(UIbooksQuantity, DBbooksQuantity);

    }

    @When("the user navigates to {string} page")
    public void theUserNavigatesToPage(String button) {
        var pom = new Home_Page();
        pom.getButton(button).click();
    }

    @And("the user clicks book categories")
    public void theUserClicksBookCategories() {
        booksPage.getBooksCategoriesDropDown().click();
    }

    @Then("verify book categories must match book_categories table from db")
    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb() {
        var UIbookCategories = new ArrayList<String>();

        //removing default unselectable option "ALL" with null value
        for (WebElement bookCategory : booksPage.getBookCategories()) {
            if (!bookCategory.getText().equals("ALL"))
                UIbookCategories.add(bookCategory.getText());
        }

        DB_Utility.createConnectionLibraryDB();
        DB_Utility.runQuery("select name from book_categories;");
        var DBbookCategories = DB_Utility.getColumnDataAsList(1);

        Assert.assertEquals(UIbookCategories, DBbookCategories);
    }

    @When("the user searches for {string} book")
    public void theUserSearchesForBook(String book) {
        booksPage.getSearchInput().sendKeys(book);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBePresentInElement(booksPage.getFirstBookName(), book));
    }

    @And("the user clicks edit book button")
    public void theUserClicksEditBookButton() {
        booksPage.getFirstBookEditButton().click();
    }

    @Then("book information must match the Database")
    public void bookInformationMustMatchTheDatabase() {
        Assert.assertTrue(booksPage.isSameInDBAndUIBook());
    }

    @When("the librarian click to add book")
    public void theLibrarianClickToAddBook() {
        booksPage.getAddBookButton().click();
    }

    @And("the librarian enter book name {string}")
    public void theLibrarianEnterBookName(String bookName) {
        booksPage.getEditBookName().sendKeys(bookName);
        UI_Utility.putCompanyDetails("name", bookName);
    }

    @When("the librarian enter ISBN {string}")
    public void theLibrarianEnterISBN(String isbn) {
        booksPage.getEditBookISBN().sendKeys(isbn);
        UI_Utility.putCompanyDetails("isbn", isbn);
    }

    @And("the librarian enter year {string}")
    public void theLibrarianEnterYear(String year) {
        booksPage.getEditBookYear().sendKeys(year);
        UI_Utility.putCompanyDetails("year", year);
    }

    @When("the librarian enter author {string}")
    public void theLibrarianEnterAuthor(String author) {
        booksPage.getEditBookAuthor().sendKeys(author);
        UI_Utility.putCompanyDetails("author", author);
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String category) {
        var select = new Select(booksPage.getEditBookCategorySelection());
        select.selectByVisibleText(category);
        UI_Utility.putCompanyDetails("category", category);
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
        booksPage.getSaveChangesButton().click();
    }

    @Then("verify {string} message is displayed")
    public void verifyMessageIsDisplayed(String message) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(booksPage.getBookCreatedMessage()));

        var actualMessage = booksPage.getBookCreatedMessage().getText();

        Assert.assertEquals(message, actualMessage);
    }

    @And("verify {string} information must match with DB")
    public void verifyInformationMustMatchWithDB(String bookName) {
        Assert.assertTrue(booksPage.isSameInDBAndUIBook(bookName));
        UI_Utility.clearBookDetails();
    }

    @When("the user clicks Borrow Book")
    public void theUserClicksBorrowBook() {
        booksPage.getFirstBookEditButton().click();
    }

    @Then("verify that book {string} is shown in Borrowing Books page")
    public void verifyThatBookIsShownInBorrowingBooksPage(String book) {
        booksPage.getBorrowingBooksPageButton().click();
        var pom = new BorrowingBooks_Page();
        var listOfBooks = pom.getBorrowedBooksNames();

        boolean isInBorrowed = false;
        for (String bookName : listOfBooks) {
            if (bookName.equals(book))
                isInBorrowed = true;
        }

        Assert.assertTrue(isInBorrowed);
    }

    @When("the user returns {string} book")
    public void theUserReturnsBook(String bookName) {
        var listOfReturnButtons = Driver.getDriver().findElements(
                                    By.xpath("//td[text()='" + bookName + "']/preceding-sibling::td/a"));

        //clicking last return button as it should be last borrowed book
        listOfReturnButtons.get(listOfReturnButtons.size() - 1).click();
    }
}
