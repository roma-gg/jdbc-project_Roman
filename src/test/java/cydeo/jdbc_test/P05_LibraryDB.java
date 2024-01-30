package cydeo.jdbc_test;

import cydeo.utilities.DB_Utility;
import org.junit.jupiter.api.Test;

public class P05_LibraryDB {

    @Test
    public void verifyBooksQuantity() {
        DB_Utility.createConnectionLibraryDB();
        DB_Utility.runQuery("select count(*) from books");

        String booksCount = DB_Utility.getFirstRowFirstColumn();
        System.out.println(booksCount);
    }
}
