package com.example.sp18.model.transport;

import java.math.BigDecimal;

public class ReportDTO {

    private BigDecimal difference;
    private BigDecimal total;
    private BigDecimal budget;
    private ResidentDTO mostExpensiveResident;

    public ReportDTO() {
    }

    public ReportDTO(BigDecimal difference, BigDecimal total, BigDecimal budget, ResidentDTO mostExpensiveResident) {
        this.difference = difference;
        this.total = total;
        this.budget = budget;
        this.mostExpensiveResident = mostExpensiveResident;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public ResidentDTO getMostExpensiveResident() {
        return mostExpensiveResident;
    }

    public void setMostExpensiveResident(ResidentDTO mostExpensiveResident) {
        this.mostExpensiveResident = mostExpensiveResident;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "difference=" + difference +
                ", total=" + total +
                ", budget=" + budget +
                ", mostExpensiveResident=" + mostExpensiveResident +
                '}';
    }
}
