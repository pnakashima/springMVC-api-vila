package com.example.sp18.model.dao;

import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class ResidentDAO {

    private static BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

    public static List<ResidentDTO> residents = new ArrayList(Arrays.asList(
            new ResidentDTO("Paulo", "Nakashima", 45, new BigDecimal("100.0"), 1, "paulo@vila.com", pe.encode("123"), Set.of("ROLE_USER", "ROLE_ADMIN")),
            new ResidentDTO("Joao", "Silva", 31, new BigDecimal("200.0"), 2, "joao@vila.com", pe.encode("345")),
            new ResidentDTO("Maria", "Souza", 26, new BigDecimal("300.0"), 3, "maria@vila.com", pe.encode("567"))
    ));

    public void updateUser(ResidentDTO user) {
        residents.remove(user);
        residents.add(user);
    }

//    public List<ResidentDTO> getResidents(){
//        return residents;
//    }

    public List<ResidentDTO> getResidents() throws SQLException {
        Connection connection = new ConnectionFactoryJDBC().getConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("select * from residents");
        ResultSet rs = stmt.getResultSet();

        List<ResidentDTO> residents = new ArrayList<>();

        while (rs.next()) {
            ResidentDTO resident = getResidentDTO(rs);
            residents.add(resident);
        }

        stmt.close();
        connection.close();

        return residents;
    }

    public ResidentDTO getResidentDTO(ResultSet rs) throws SQLException {
        ResidentDTO resident = new ResidentDTO();
        resident.setFirstName(rs.getString("first_name"));
        resident.setLastName(rs.getString("last_name"));
        resident.setAge(rs.getInt("age"));
        resident.setCost(rs.getBigDecimal("cost"));
        resident.setId(rs.getInt("id"));
        resident.setEmail(rs.getString("email"));
        resident.setPassword(pe.encode(rs.getString("password")));
        return resident;
    }


    public Boolean create(ResidentDTO resident) throws SQLException {
//        return residents.add(resident);

        try (Connection connection = new ConnectionFactoryJDBC().getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("insert into residents values (default, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, resident.getFirstName());
            pStmt.setString(2, resident.getLastName());
            pStmt.setString(3, resident.getEmail());
            pStmt.setInt(4, resident.getAge());
            pStmt.setBigDecimal(5, resident.getCost());
            pStmt.setString(6, pe.encode(resident.getPassword()));
            pStmt.execute();

            ResultSet rs = pStmt.getGeneratedKeys();

            while (rs.next()) {
                int id = rs.getInt(1);
                resident.setId(id);
            }
            return true;
        }

    }


    public ResidentDTO getResident(String username) throws SQLException {
        try (Connection connection = new ConnectionFactoryJDBC().getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("select * from residents where email=?");
            pStmt.setString(1, username);
            pStmt.execute();
            ResultSet rs = pStmt.getResultSet();
            if (rs.next()) {
                return getResidentDTO(rs);
            }
        }
        return null;
//        return residents.stream().filter(resident -> resident.getEmail().equals(username)).findFirst().get();
    }

    public List<ResidentDTO> filteredList(String name) throws SQLException {

        List<ResidentDTO> residents = new ArrayList<>();

        try (Connection connection = new ConnectionFactoryJDBC().getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("select * from residents where first_name LIKE ?");
            pStmt.setString(1, name + "%");
            pStmt.execute();
            ResultSet rs = pStmt.getResultSet();
            while (rs.next()) {
                residents.add(getResidentDTO(rs));
            }
        }

        if (!residents.isEmpty()) {
            return residents;
        }

        return new ArrayList<>();
    }


}
