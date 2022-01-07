package com.viladevinhouse.model.transport;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


// Classe utilizada para detalhamento de um habitante:
// - Trazer pelo ID do habitante
// - Retornar na rota todos os atributos de um habitante menos o seu ID

public class IdDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private BigDecimal cost;
    private String cpf;
    private String password;

    private Set<String> roles = new HashSet<>();

    public IdDTO() {
    }

    public IdDTO(String firstName, String lastName, String email, String dob, BigDecimal cost, String cpf, String password, Set<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.cost = cost;
        this.cpf = cpf;
        this.password = password;
        this.roles = roles;
    }

    public IdDTO(String firstName, String lastName, String email, String dob, BigDecimal cost, String cpf, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.cost = cost;
        this.cpf = cpf;
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

