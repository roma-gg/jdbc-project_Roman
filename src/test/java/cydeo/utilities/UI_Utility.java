package cydeo.utilities;

import cydeo.pages.Login_Page;

import java.util.HashMap;
import java.util.Map;

public class UI_Utility {
    public static void login (String userType) {
        var email = getUserEmail(userType);
        var password = ConfigurationReader.getProperty("userPassword");

        var pom = new Login_Page();
        pom.login(email, password);
    }

    private static String getUserEmail(String userType) {
        if (userType.equals("librarian"))
            return ConfigurationReader.getProperty("librarianEmail");
        else if (userType.equals("student"))
            return ConfigurationReader.getProperty("studentEmail");
        else
            throw new IllegalArgumentException();
    }

    private static Map<String, String> bookDetails = new HashMap<>();
    public static void putCompanyDetails(String key, String value) {
        bookDetails.put(key, value);
    }

    public static Map<String, String> getBookDetails() {
        return bookDetails;
    }

    public static void clearBookDetails() {
        bookDetails.clear();
    }
}
