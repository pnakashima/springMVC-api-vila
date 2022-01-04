package com.example.sp18.controllers.service;

import com.example.sp18.model.dao.ConnectionFactoryJDBC;
import com.example.sp18.model.dao.ResidentDAO;
import com.example.sp18.model.dao.UserSpringSecurity;
import com.example.sp18.model.transport.IdDTO;
import com.example.sp18.model.transport.ListDTO;
import com.example.sp18.model.transport.ReportDTO;
import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResidentService implements UserDetailsService {

    private ResidentDAO residentDAO;
    private Double villageBudget;

    public ResidentService(ResidentDAO residentDAO, @Value("${villageBudget:0}") Double villageBudget) {
        this.residentDAO = residentDAO;
        this.villageBudget = villageBudget;
    }

    public List<ResidentDTO> getResidents() throws SQLException {

        List<ResidentDTO> residents = this.residentDAO.getResidents();

        if (residents.isEmpty()) {
            System.out.println("Não foram encontrados residentes.");
        }

        return residents;
    }

    public List<ListDTO> listResidents() throws SQLException {

        List<ListDTO> residents = this.residentDAO.listResidents();

        if (residents.isEmpty()) {
            System.out.println("Não foram encontrados residentes.");
        }

        return residents;
    }

    public Boolean create(ResidentDTO resident) throws SQLException {
        if (resident == null) {
            throw new IllegalArgumentException("O residente está nulo");
        }
        return this.residentDAO.create(resident);
    }

    public Double getBudget() {
        return this.villageBudget;
    }

    public BigDecimal getResidentsCost() throws SQLException {

        return getResidents().stream()
                .map(ResidentDTO::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public IdDTO getResidentById(Integer id) throws SQLException {
        if (id == null) {
            throw new IllegalArgumentException("O id está nulo");
        }

//        Optional<ResidentDTO> residentOpt = getResidents().stream()
//                .filter(resident -> resident.getId() == id).findFirst();
//
//        if (residentOpt.isPresent()) {
//            return residentOpt.get();
//        }

        Connection connection = new ConnectionFactoryJDBC().getConnection();

        PreparedStatement pStmt = connection.prepareStatement("select * from residents where id=?");
        pStmt.setInt(1, id);
        pStmt.execute();

        ResultSet rs = pStmt.getResultSet();

        if (rs.next()) {
            return this.residentDAO.getIdDTO(rs);
        }

        pStmt.close();
        connection.close();

        throw new NoSuchElementException("Morador não encontrado!");
    }

    public List<ResidentDTO> filteredList(String name) throws SQLException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("O nome está vazio");
        }

        return residentDAO.filteredList(name);
    }

    public ReportDTO getReport() throws SQLException {

        ReportDTO report = new ReportDTO();

        BigDecimal budget = new BigDecimal(getBudget());
        report.setBudget(budget);

        BigDecimal diff = budget.subtract(getResidentsCost());
        report.setDifference(diff);

        report.setTotal(getResidentsCost());

        Optional<ResidentDTO> maxCost = getResidents().stream().max(Comparator.comparing(ResidentDTO::getCost));

//        BigDecimal max = BigDecimal.ZERO;
//        ResidentDTO mostExpensiveResident;
//        for (ResidentDTO resident: getResidents()) {
//            if (resident.getCost().doubleValue() > max.doubleValue()) {
//                mostExpensiveResident = resident;
//                max = resident.getCost();
//            }
//        }

        report.setMostExpensiveResident(maxCost.get());

        return report;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResidentDTO residentDTO = null;
        try {
            residentDTO = getResident(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (residentDTO==null){
            throw new UsernameNotFoundException(username);
        }
        return new UserSpringSecurity(residentDTO.getEmail(),
                residentDTO.getPassword(),
                residentDTO.getRoles());
    }

    public ResidentDTO getResident(String username) throws SQLException {
        return residentDAO.getResident(username);
    }

    public static UserSpringSecurity authenticated() {
        try{
            return new UserSpringSecurity(
                    (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    null, new ArrayList<>());
        } catch (Exception e){
            return null;
        }
    }

//    public void updateUser(ResidentDTO user){
//        residentDAO.updateUser(user);
//    }

    public Boolean deleteUser(String id) throws SQLException {
        return residentDAO.deleteUser(id);
    }


}
