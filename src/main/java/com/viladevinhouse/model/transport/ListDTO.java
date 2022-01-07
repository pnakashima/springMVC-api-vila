package com.viladevinhouse.model.transport;

import java.util.Objects;

public class ListDTO {

    private String firstName;
    private String lastName;
    private Integer id;

    public ListDTO() {
    }


    public ListDTO(String firstName, String lastName, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListDTO that = (ListDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
