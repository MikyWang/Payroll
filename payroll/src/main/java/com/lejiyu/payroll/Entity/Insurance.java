package com.lejiyu.payroll.Entity;

import java.math.BigDecimal;

public class Insurance {
    private Long employeeId;

    private BigDecimal medical;

    private BigDecimal endowment;

    private BigDecimal unemployment;

    private BigDecimal inductriaInjury;

    private BigDecimal birth;

    private BigDecimal accumulationFund;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getMedical() {
        return medical;
    }

    public void setMedical(BigDecimal medical) {
        this.medical = medical;
    }

    public BigDecimal getEndowment() {
        return endowment;
    }

    public void setEndowment(BigDecimal endowment) {
        this.endowment = endowment;
    }

    public BigDecimal getUnemployment() {
        return unemployment;
    }

    public void setUnemployment(BigDecimal unemployment) {
        this.unemployment = unemployment;
    }

    public BigDecimal getInductriaInjury() {
        return inductriaInjury;
    }

    public void setInductriaInjury(BigDecimal inductriaInjury) {
        this.inductriaInjury = inductriaInjury;
    }

    public BigDecimal getBirth() {
        return birth;
    }

    public void setBirth(BigDecimal birth) {
        this.birth = birth;
    }

    public BigDecimal getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(BigDecimal accumulationFund) {
        this.accumulationFund = accumulationFund;
    }
}