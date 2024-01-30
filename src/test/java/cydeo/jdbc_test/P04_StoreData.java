package cydeo.jdbc_test;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class P04_StoreData {
    String dbUrl = "jdbc:oracle:thin:@3.80.242.86:1521:XE";
    String dbPass = "hr";
    String dbUserName = "hr";

    @Test
    public void storeData() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPass);
        DatabaseMetaData dbMetaData = connection.getMetaData();

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet table = statement.executeQuery("select * from COUNTRIES");
        ResultSetMetaData tableMetaData = table.getMetaData();

        List<LinkedHashMap<String, String>> countries = new LinkedList<>();
        while (table.next()) {
            var country = new LinkedHashMap<String, String>();
            for (int i = 1; i <= tableMetaData.getColumnCount(); i++) {
                country.put(tableMetaData.getColumnName(i), table.getString(i));
            }

            countries.add(country);
        }

        for (HashMap<String, String> country : countries) {
            for (String key : country.keySet()) {
                System.out.print(country.get(key) + " ");
            }
            System.out.println();
        }





        table.close();
        statement.close();
        connection.close();
    }

}
