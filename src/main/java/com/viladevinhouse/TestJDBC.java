package com.viladevinhouse;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/vila",
                "postgres",
                "admin");
//        System.out.println("Conectado!");

        Statement stmt = connection.createStatement();
        boolean execute = stmt.execute("select * from residents");
//        System.out.println(execute);

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
                                + " | password: " + password
                                + " | class: " + rs.getClass());
        }

        stmt.close();
        connection.close();
    }
}
