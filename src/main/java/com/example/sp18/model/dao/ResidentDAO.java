package com.example.sp18.model.dao;

import com.example.sp18.model.transport.ListDTO;
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

//    public static List<ResidentDTO> residents = new ArrayList(Arrays.asList(
//            new ResidentDTO("Paulo", "Nakashima", "paulo@vila.com", "19-08-1976", new BigDecimal("100.0"), 1, "21281714828", pe.encode("123"), Set.of("ROLE_USER", "ROLE_ADMIN"))
//    ));
//
//    public void updateUser(ResidentDTO user) {
//        residents.remove(user);
//        residents.add(user);
//    }

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

    public List<ListDTO> listResidents() throws SQLException {
        Connection connection = new ConnectionFactoryJDBC().getConnection();
        Statement stmt = connection.createStatement();
        stmt.execute("select * from residents");
        ResultSet rs = stmt.getResultSet();

        List<ListDTO> residents = new ArrayList<>();

        while (rs.next()) {
            ListDTO resident = getListDTO(rs);
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
        resident.setEmail(rs.getString("email"));
        resident.setDob(rs.getString("dob"));
        resident.setCost(rs.getBigDecimal("cost"));
        resident.setId(rs.getInt("id"));
        resident.setPassword(pe.encode(rs.getString("password")));
        resident.setCpf(rs.getString("cpf"));
        return resident;
    }

    public ListDTO getListDTO(ResultSet rs) throws SQLException {
        ListDTO resident = new ListDTO();
        resident.setFirstName(rs.getString("first_name"));
        resident.setLastName(rs.getString("last_name"));
        resident.setId(rs.getInt("id"));
        return resident;
    }


    public Boolean create(ResidentDTO resident) throws SQLException {
//        return residents.add(resident);

        try (Connection connection = new ConnectionFactoryJDBC().getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("insert into residents values (default, ?, ?, ?, ?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, resident.getFirstName());
            pStmt.setString(2, resident.getLastName());
            pStmt.setString(3, resident.getEmail());
            pStmt.setString(4, resident.getDob());
            pStmt.setBigDecimal(5, resident.getCost());
            pStmt.setString(6, pe.encode(resident.getPassword()));
            pStmt.setString(7, resident.getCpf());
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

    public Boolean deleteUser(String id) throws SQLException {
        try (Connection connection = new ConnectionFactoryJDBC().getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("delete from residents where id = CAST(? AS INTEGER)");
            pStmt.setString(1, id);
            pStmt.execute();
            ResultSet rs = pStmt.getResultSet();
            return true;
        }
    }

}
