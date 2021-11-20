package com.example.sp18.model.dao;

import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class ResidentDAO {

    private static BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

    public static List<ResidentDTO> residents = new ArrayList(Arrays.asList(
            new ResidentDTO("Paulo","Nakashima", 45, new BigDecimal("100.0"), 1, "paulo@vila.com", pe.encode("123"), Set.of("ROLE_USER", "ROLE_ADMIN")),
            new ResidentDTO("Joao","Silva", 31, new BigDecimal("200.0"), 2, "joao@vila.com", pe.encode("345")),
            new ResidentDTO("Maria","Souza", 26, new BigDecimal("300.0"), 3, "maria@vila.com", pe.encode("567"))
    ));

    public void updateUser(ResidentDTO user){
        residents.remove(user);
        residents.add(user);
    }

    public List<ResidentDTO> getResidents(){
        return residents;
    }

    public Boolean create(ResidentDTO resident) {
        return residents.add(resident);
    }


    public ResidentDTO getResident(String username) {
        return residents.stream().filter(resident -> resident.getEmail().equals(username)).findFirst().get();
    }
}
