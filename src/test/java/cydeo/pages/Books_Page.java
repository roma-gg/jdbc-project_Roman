package cydeo.pages;

import cydeo.utilities.DB_Utility;
import cydeo.utilities.Driver;
import cydeo.utilities.UI_Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Books_Page {
    public Books_Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "book_categories")
    private WebElement booksCategoriesDropDown;

    @FindBy(xpath = "//select[@id='book_categories']/option")
    private List<WebElement> bookCategories;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchInput;

    @FindBy(xpath = "//tr//td/a")
    private WebElement firstBookEditButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//div[@class='toast-message']")
    private WebElement bookCreatedMessage;

    @FindBy(xpath = "//table//th[@data-name='U.full_name']")
    private WebElement borrowedByColumnHead;

    @FindBy(xpath = "//td/a")
    private WebElement borrowFirstBookButton;

    @FindBy(xpath = "//a[contains(@href, 'borrowing')]")
    private WebElement borrowingBooksPageButton;

    public WebElement getEditBookName() {
        return editBookName;
    }

    public WebElement getEditBookISBN() {
        return editBookISBN;
    }

    public WebElement getEditBookYear() {
        return editBookYear;
    }

    public WebElement getEditBookAuthor() {
        return editBookAuthor;
    }

    public WebElement getEditBookCategorySelection() {
        return editBookCategorySelection;
    }

    public WebElement getEditBookCategory() {
        return editBookCategory;
    }

    public WebElement getEditBookDescription() {
        return editBookDescription;
    }

    //need to retrieve attribute "value" to get text
    @FindBy(xpath = "//input[@name='name']")
    private WebElement editBookName;

    //need to retrieve attribute "value" to get text
    @FindBy(xpath = "//input[@name='isbn']")
    private WebElement editBookISBN;

    @FindBy(xpath = "//input[@name='year']")
    private WebElement editBookYear;

    @FindBy(xpath = "//input[@name='author']")
    private WebElement editBookAuthor;

    @FindBy(id = "book_group_id")
    private WebElement editBookCategorySelection;

    //need to retrieve value
    @FindBy(xpath = "//select[@id='book_group_id']//option[@selected='selected']")
    private WebElement editBookCategory;

    @FindBy(id = "description")
    private WebElement editBookDescription;

    @FindBy(xpath = "//td[3]")
    private WebElement firstBookName;

    @FindBy(xpath = "//section[@id='books']//a[contains(@href, 'add')]")
    private WebElement addBookButton;

    public boolean isSameInDBAndUIBook() {
        //checking each book from DB in case of duplicates
        for (var bookDB : getBooksAndItsDetails_DB()) {
            boolean isSame = true;
            var bookUI = getBookDetails_UI();
            //comparing each detail of book from DB and book from UI
            for (String detail : bookUI.keySet()) {
                if (!bookDB.get(detail).equals(bookUI.get(detail)))
                    isSame = false;
            }

            if (isSame)
                return true;
        }

        return false;
    }

    // comparing list of books from DB based on bookName parameter,
    // and comparing with book from UI saved in UI_UtilClass saved in previous steps
    public boolean isSameInDBAndUIBook(String bookName) {
        //checking each book from DB in case of duplicates
        for (var bookDB : getBooksAndItsDetails_DB(bookName)) {
            boolean isSame = true;
            var bookUI = UI_Utility.getBookDetails();

            //comparing each detail of book from DB and book from UI
            for (String detail : bookUI.keySet()) {
                var UIbookDetail = bookUI.get(detail);
                var DBbookDetail = bookDB.get(detail);
                //cheching if UIbookDetail not null as in DB there are more details than in UI, such as ID etc
                if (UIbookDetail != null &&
                    !DBbookDetail.equals(UIbookDetail))
                    isSame = false;
            }

            if (isSame)
                return true;
        }

        return false;
    }

    private Map<String, String> getBookDetails_UI() {
        HashMap<String, String> bookDetails = new HashMap<>();
        bookDetails.put("name", editBookName.getAttribute("value"));
        bookDetails.put("isbn", editBookISBN.getAttribute("value"));
        bookDetails.put("year", editBookYear.getAttribute("value"));
        bookDetails.put("author", editBookAuthor.getAttribute("value"));
        bookDetails.put("book_category_id", editBookCategory.getAttribute("value"));
        bookDetails.put("description", editBookDescription.getText());

        return bookDetails;
    }

    private List<Map<String, String>> getBooksAndItsDetails_DB() {
        DB_Utility.createConnectionLibraryDB();

        var bookName = editBookName.getAttribute("value");
        DB_Utility.runQuery("select * from books\n" +
                            "where name = '" + bookName + "';");
        
        var listOfBooks = new ArrayList<Map<String, String>>();
        int booksQuantity = DB_Utility.getRowCount();
        for (int i = 1; i <= booksQuantity; i++) {
            listOfBooks.add(DB_Utility.getRowMap(i));
        }

        DB_Utility.closeConnection();

        return listOfBooks;
    }

    private List<Map<String, String>> getBooksAndItsDetails_DB(String bookName) {
        DB_Utility.createConnectionLibraryDB();

        DB_Utility.runQuery("select b.name as name, isbn, year, author, c.name as category\n" +
                                "from books b left join book_categories c\n" +
                                "on b.book_category_id = c.id\n" +
                                "where b.name = '" + bookName + "';");

        var listOfBooks = new ArrayList<Map<String, String>>();
        int booksQuantity = DB_Utility.getRowCount();
        for (int i = 1; i <= booksQuantity; i++) {
            listOfBooks.add(DB_Utility.getRowMap(i));
        }

        DB_Utility.closeConnection();

        return listOfBooks;
    }

    public WebElement getBooksCategoriesDropDown() {
        return booksCategoriesDropDown;
    }

    public List<WebElement> getBookCategories() {
        return bookCategories;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getFirstBookEditButton() {
        return firstBookEditButton;
    }

    public WebElement getFirstBookName() {
        return firstBookName;
    }

    public WebElement getAddBookButton() {
        return addBookButton;
    }

    public WebElement getSaveChangesButton() {
        return saveChangesButton;
    }

    public WebElement getBookCreatedMessage() {
        return bookCreatedMessage;
    }

    public void sortBooksByBorrowedByAsc() {
        var sortedInOrder = borrowedByColumnHead.getAttribute("aria-sort");
        if (sortedInOrder.equals("descending"))
            borrowedByColumnHead.click();
    }

    public WebElement getBorrowFirstBookButton() {
        return borrowFirstBookButton;
    }

    public WebElement getBorrowingBooksPageButton() {
        return borrowingBooksPageButton;
    }
}
