package com.lejiyu.payroll.Entity;

public class EmployeeWork {
    private Long employeeNumber;

    private Integer employeeSeniority;

    private Integer employeeLevel;

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Integer getEmployeeSeniority() {
        return employeeSeniority;
    }

    public void setEmployeeSeniority(Integer employeeSeniority) {
        this.employeeSeniority = employeeSeniority;
    }

    public Integer getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(Integer employeeLevel) {
        this.employeeLevel = employeeLevel;
    }
}