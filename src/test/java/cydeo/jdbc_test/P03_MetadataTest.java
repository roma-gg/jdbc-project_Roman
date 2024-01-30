package cydeo.jdbc_test;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P03_MetadataTest {
    String dbUrl = "jdbc:oracle:thin:@3.80.242.86:1521:XE";
    String dbPass = "hr";
    String dbUserName = "hr";

    @Test
    public void dbMetaData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPass);
        DatabaseMetaData dbMetaData = connection.getMetaData();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet table = statement.executeQuery("select * from EMPLOYEES");

        System.out.println(dbMetaData.getUserName());
        System.out.println(dbMetaData.getDriverName());
        System.out.println(dbMetaData.getDriverVersion());
        System.out.println(dbMetaData.getDatabaseProductName());
        System.out.println(dbMetaData.getDatabaseProductVersion());
        System.out.println("========");

        ResultSetMetaData tableMetaData = table.getMetaData();
        int columnsQuantity = tableMetaData.getColumnCount();
        for (int i = 1; i <= columnsQuantity; i++) {
            System.out.println(tableMetaData.getColumnName(i));
        }

        while (table.next()) {
            for (int i = 1; i < columnsQuantity; i++) {
                System.out.print(tableMetaData.getColumnName(i) + ": " + table.getString(i) + " | ");
            }
            System.out.println();
        }




        table.close();
        statement.close();
        connection.close();
    }
}
