package com.example.sp18.controllers.service;

import com.example.sp18.model.dao.ResidentDAO;
import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResidentService {

    private ResidentDAO residentDAO;
    private Double villageBudget;

    public ResidentService(ResidentDAO residentDAO, @Value("${villageBudget:0}") Double villageBudget) {
        this.residentDAO = residentDAO;
        this.villageBudget = villageBudget;
    }

    public List<ResidentDTO> getResidents() {

        List<ResidentDTO> residents = this.residentDAO.getResidents();

        if (residents.isEmpty()) {
            System.out.println("Não foram encontrados residentes.");
        }

        return residents;
    }

    public Boolean create(ResidentDTO resident) {
        if (resident == null) {
            throw new IllegalArgumentException("O residente está nulo");
//            System.out.println("O residente está nulo");
        }
        return this.residentDAO.create(resident);
    }

    public Double getBudget() {
        return this.villageBudget;
    }

    public BigDecimal getResidentsCost() {

        return getResidents().stream()
                .map(ResidentDTO::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public ResidentDTO getResidentById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O id está nulo");
        }

        Optional<ResidentDTO> residentOpt = getResidents().stream()
                .filter(resident -> resident.getId() == id).findFirst();

        if (residentOpt.isPresent()) {
            return residentOpt.get();
        }

        throw new NoSuchElementException("Morador não encontrado!");
    }

    public List<ResidentDTO> filteredList(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("O nome está vazio");
        }

        List<ResidentDTO> residentOpt = getResidents().stream()
                .filter(resident -> resident.getFirstName().equalsIgnoreCase(name)).collect(Collectors.toList());

        if (!residentOpt.isEmpty()) {
            return residentOpt;
        }

        return new ArrayList<>();
    }

//    public List<ResidentDTO> filteredList(String name) {
//        if (name==null) {
//            return
//        }
//    }
}
