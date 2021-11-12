package com.example.sp18.controllers.rest;

import com.example.sp18.controllers.service.ResidentService;
import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/residents")
public class ResidentRest {

    private final ResidentService residentService;

    public ResidentRest(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/list")
    public List<ResidentDTO> getResidents() {
        return residentService.getResidents();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createNewResident(@RequestBody ResidentDTO resident ) {
        Boolean response = this.residentService.create(resident);
        if (response==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping("/filter")
//    public List<ResidentDTO> list(@RequestParam("name") String name) {
//        return residentService.filteredList(name);
//    }
//
//    @GetMapping("/{id}")
//    public ResidentDTO getResidentById(@PathVariable Integer id) {
//        return residentService.getResidentById(id);
//    }
//
    @GetMapping("/cost")
    public BigDecimal getResidentsCost(){
        return residentService.getResidentsCost();
    }

    @GetMapping("/budget")
    public Double getBudget(){
        return residentService.getBudget();
    }




}