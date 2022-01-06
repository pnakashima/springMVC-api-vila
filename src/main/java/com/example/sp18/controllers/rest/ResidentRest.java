package com.example.sp18.controllers.rest;

import com.example.sp18.controllers.service.ResidentService;
import com.example.sp18.model.transport.IdDTO;
import com.example.sp18.model.transport.ListDTO;
import com.example.sp18.model.transport.ReportDTO;
import com.example.sp18.model.transport.ResidentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/residents")
public class ResidentRest {

    private final ResidentService residentService;

    public ResidentRest(ResidentService residentService) {
        this.residentService = residentService;
    }


    // Listar todos os habitantes (exibe nome e id apenas)
    @GetMapping("/list")
    public List<ListDTO> listResidents() throws SQLException {
        return residentService.listResidents();
    }

    // Listar os habitantes por nome (exibe nome e id apenas)
    @GetMapping("/filter")
    public List<ListDTO> listByName(@RequestParam("name") String name) throws SQLException {
        return residentService.filteredList(name);
    }

    // Listar os habitantes por data de nascimento trazendo pelo mês (exibe nome e id apenas)
    @GetMapping("/filter-month")
    public List<ListDTO> listMonth(@RequestParam("month") String month) throws SQLException {
        return residentService.filteredMonthList(month);
    }

    // Listar os habitantes com idade igual ou superior a X anos
    @GetMapping("/filter-age")
    public List<ResidentDTO> listAge(@RequestParam("age") String age) throws SQLException, ParseException {
        return residentService.filteredAgeList(age);
    }

    // Cadastro de habitantes
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createNewResident(@RequestBody ResidentDTO resident) throws SQLException, ParseException {
        Boolean response = this.residentService.create(resident);
        if (response == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Detalhamento de um habitante (todos os atributos menos o ID)
    @GetMapping("/{id}")
    public IdDTO getResidentById(@PathVariable Integer id) throws SQLException {
        return residentService.getResidentById(id);
    }

    // Deletar um habitante
    @PostMapping("/delete")
    public ResponseEntity<HttpStatus> deleteResident(@RequestParam("id") String id) throws SQLException {
        Boolean response = this.residentService.deleteUser(id);
        if (response == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Relatório financeiro da vila
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/report")
    public ReportDTO getReport() throws SQLException {
        return residentService.getReport();
    }












    @GetMapping("/get")
    public List<ResidentDTO> getResidents() throws SQLException {
        System.out.println("Listando moradores...");
        return residentService.getResidents();
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
    public Double getBudget() {
        return residentService.getBudget();
    }






}
