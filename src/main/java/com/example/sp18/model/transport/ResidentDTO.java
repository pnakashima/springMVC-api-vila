package com.example.sp18.model.transport;

import java.math.BigDecimal;

public class ResidentDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal cost;
    private Integer id;

    public ResidentDTO() {
    }

    public ResidentDTO(String firstName, String lastName, Integer age, BigDecimal cost, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.cost = cost;
        this.id = id;
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
}
