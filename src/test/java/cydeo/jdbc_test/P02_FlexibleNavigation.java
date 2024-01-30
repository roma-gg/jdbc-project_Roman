package cydeo.jdbc_test;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class P02_FlexibleNavigation {
    String dbUrl = "jdbc:oracle:thin:@3.80.242.86:1521:XE";
    String dbPass = "hr";
    String dbUserName = "hr";

    @Test
    void flexibleNavigation() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPass);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet table = statement.executeQuery("select * from EMPLOYEES");

        table.absolute(10);

        System.out.println(table.getString(1) + " " + table.getString(2));

        System.out.println(table.getRow());

        System.out.println("======");
        table.last();
        System.out.println(table.getRow());

        table.previous();
        System.out.println(table.getRow());

        table.beforeFirst();
        System.out.println(table.getRow());

        table.close();
        statement.close();
        connection.close();


    }

}
