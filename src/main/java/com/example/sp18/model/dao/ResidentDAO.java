package com.example.sp18.model.dao;

import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ResidentDAO {

    public static List<ResidentDTO> residents = new ArrayList(Arrays.asList(
            new ResidentDTO("Paulo","Nakashima", 45, new BigDecimal("100.0"), 1),
            new ResidentDTO("Joao","Silva", 31, new BigDecimal("200.0"), 2),
            new ResidentDTO("Maria","Souza", 26, new BigDecimal("300.0"), 3)
    ));

//    public static List<ResidentDTO> newResidents = new ArrayList<>();
//
//    public ResidentDAO() {
//        newResidents.addAll(residents);
//    }

    public List<ResidentDTO> getResidents(){
        return residents;
    }

    public Boolean create(ResidentDTO resident) {
        return residents.add(resident);
    }

}
