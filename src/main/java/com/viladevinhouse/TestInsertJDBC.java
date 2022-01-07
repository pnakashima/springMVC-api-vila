package com.viladevinhouse;

import java.sql.*;

public class TestInsertJDBC {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/vila",
                "postgres",
                "admin");

        Statement stmt = connection.createStatement();
        boolean execute = stmt.execute("insert into residents values (default, 'Joao', 'Silva', 'joao@vila.com', 31, 200, '345');", Statement.RETURN_GENERATED_KEYS);

        execute = stmt.execute("select * from residents");

        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            Integer age = rs.getInt("age");
            Double cost = rs.getDouble("cost");
            String password = rs.getString("password");

            System.out.println("first name: " + firstName
                    + " | last name: " + lastName
                    + " | email : " + email
                    + " | age : " + age
                    + " | cost: " + cost
                    + " | password: " + password);
        }

        stmt.close();
        connection.close();
    }

}
