package cydeo.step_definitions;

import cydeo.utilities.ConfigurationReader;
import cydeo.utilities.DB_Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;

public class DB_Library2_StepDefinitions {
    @Given("Establish the database connection")
    public void establishTheDatabaseConnection() {
        DB_Utility.createConnectionLibraryDB();
    }

    @When("Execute query to get all IDs from users")
    public void executeQueryToGetAllIDsFromUsers() {
        DB_Utility.runQuery("select id from users");
    }

    @Then("verify all users has unique ID")
    public void verifyAllUsersHasUniqueID() {
        var ids = DB_Utility.getColumnDataAsList(1);
        var set = new HashSet<String>();
        set.addAll(ids);

        Assert.assertEquals(set.size(), ids.size());
        DB_Utility.closeConnection();
    }

    @When("Execute query to get all columns")
    public void executeQueryToGetAllColumns() {
        DB_Utility.runQuery("select * from users");
    }

    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult(List<String> expectedColumns) {
        var actualColumns = DB_Utility.getAllColumnNamesAsList();
        Assert.assertEquals(expectedColumns, actualColumns);
    }

    @When("I execute query to find most popular book genre")
    public void iExecuteQueryToFindMostPopularBookGenre() {
        DB_Utility.createConnectionLibraryDB();
        DB_Utility.runQuery("select c.name, count(*) as quanity\n" +
                "from book_borrow left join books\n" +
                "on book_borrow.book_id = books.id\n" +
                "left join book_categories c\n" +
                "on books.book_category_id = c.id\n" +
                "group by c.name\n" +
                "order by quanity desc;");
    }

    @Then("verify {string} is the most popular book genre.")
    public void verifyIsTheMostPopularBookGenre(String category) {
        var mostPopularCategory = DB_Utility.getCellValue(1, 1);
        Assert.assertEquals(category, mostPopularCategory);

        DB_Utility.closeConnection();
    }

    @And("verify logged student has same {string} book in database")
    public void verifyLoggedStudentHasSameBookInDatabase(String bookName) {
        DB_Utility.createConnectionLibraryDB();
        DB_Utility.runQuery("select users.id, users.full_name, books.name, returned_date, book_borrow.is_returned\n" +
                                "from books inner join book_borrow\n" +
                                "on books.id = book_borrow.book_id\n" +
                                "inner join users\n" +
                                "on book_borrow.user_id = users.id\n" +
                                "where books.name = '" + bookName + "'\n" +
                                "order by returned_date desc;");

        var studentIdBorrowed = DB_Utility.getCellValue(1, 1);
        // 1 - returned, 0 - isn't
        var borrowedBookRowNum = DB_Utility.getRowCount();
        var isReturned = Integer.parseInt(DB_Utility.getCellValue(borrowedBookRowNum, 5));

        var loggedStudentEmail = ConfigurationReader.getProperty("studentEmail");
        DB_Utility.runQuery("select id from users\n" +
                                "where email = '" + loggedStudentEmail + "';");
        var studentIdLogged = DB_Utility.getCellValue(1, 1);

        //checking if books borrowed
        Assert.assertEquals(isReturned, 0);
        //comparing logged student ID and student ID from borrowed book DB
        Assert.assertEquals(studentIdLogged, studentIdBorrowed);
    }
}
