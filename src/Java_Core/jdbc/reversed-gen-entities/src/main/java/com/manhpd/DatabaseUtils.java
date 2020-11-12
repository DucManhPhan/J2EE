package com.manhpd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    public static List<String> getDatabaseNames(String filter) throws SQLException {
        List<String> dbNames = new ArrayList<>();

        String databaseURL = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "password";

        Connection connection = DriverManager.getConnection(databaseURL, username, password);

        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet result = metadata.getCatalogs();

        while (result.next()) {
            String aDBName = result.getString(1);
            if (aDBName.contains(filter)) {
                dbNames.add(aDBName);
            }
        }

        connection.close();

        return dbNames;
    }

}
