package com.example.sp18.model.transport;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ResidentDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal cost;
    private Integer id;
    private String email;
    private String password;

    private Set<String> roles = new HashSet<>();

    public ResidentDTO() {
    }

    public ResidentDTO(String firstName, String lastName, Integer age, BigDecimal cost, Integer id, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cost = cost;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public ResidentDTO(String firstName, String lastName, Integer age, BigDecimal cost, Integer id, String email, String password, Set<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cost = cost;
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResidentDTO that = (ResidentDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
