package com.lejiyu.payroll.Entity;

import java.math.BigDecimal;

public class Salary {
    private String employNumber;

    private BigDecimal expectSalary;

    private BigDecimal actuallySalary;

    private BigDecimal fine;

    private Integer overtime;

    private BigDecimal overtimeSalary;

    private Integer officeDay;

    public String getEmployNumber() {
        return employNumber;
    }

    public void setEmployNumber(String employNumber) {
        this.employNumber = employNumber == null ? null : employNumber.trim();
    }

    public BigDecimal getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(BigDecimal expectSalary) {
        this.expectSalary = expectSalary;
    }

    public BigDecimal getActuallySalary() {
        return actuallySalary;
    }

    public void setActuallySalary(BigDecimal actuallySalary) {
        this.actuallySalary = actuallySalary;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public BigDecimal getOvertimeSalary() {
        return overtimeSalary;
    }

    public void setOvertimeSalary(BigDecimal overtimeSalary) {
        this.overtimeSalary = overtimeSalary;
    }

    public Integer getOfficeDay() {
        return officeDay;
    }

    public void setOfficeDay(Integer officeDay) {
        this.officeDay = officeDay;
    }
}