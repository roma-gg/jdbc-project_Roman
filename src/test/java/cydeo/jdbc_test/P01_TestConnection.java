package cydeo.jdbc_test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class P01_TestConnection {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@3.80.242.86:1521:XE";
        String dbPass = "hr";
        String dbUserName = "hr";
        var connection = DriverManager.getConnection(dbUrl, dbUserName, dbPass);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select FIRST_NAME, LAST_NAME, SALARY from EMPLOYEES");

        int rowsCount = 0;
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2));
            rowsCount++;
        }
        System.out.println(rowsCount);


        result.close();
        statement.close();
        connection.close();

    }


}
