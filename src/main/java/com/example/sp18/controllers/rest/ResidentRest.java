package com.example.sp18.controllers.rest;

import com.example.sp18.controllers.service.ResidentService;
import com.example.sp18.model.transport.ReportDTO;
import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/residents")
public class ResidentRest {

    private final ResidentService residentService;

    public ResidentRest(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/list")
    public List<ResidentDTO> getResidents() throws SQLException {
        return residentService.getResidents();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createNewResident(@RequestBody ResidentDTO resident ) throws SQLException {
        Boolean response = this.residentService.create(resident);
        if (response==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/filter")
    public List<ResidentDTO> list(@RequestParam("name") String name) throws SQLException {
        return residentService.filteredList(name);
    }

    @GetMapping("/{id}")
    public ResidentDTO getResidentById(@PathVariable Integer id) throws SQLException {
        return residentService.getResidentById(id);
    }

    @GetMapping("/username")
    public ResidentDTO getResident(@RequestParam("username") String username) throws SQLException {
        return residentService.getResident(username);
    }

    @GetMapping("/cost")
    public BigDecimal getResidentsCost() throws SQLException {
        return residentService.getResidentsCost();
    }

    @GetMapping("/budget")
    public Double getBudget(){
        return residentService.getBudget();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/report")
    public ReportDTO getReport() throws SQLException {
        return residentService.getReport();
    }

}
