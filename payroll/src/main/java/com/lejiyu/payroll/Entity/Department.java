package com.lejiyu.payroll.Entity;

public class Department {
    private String departmentNumber;

    private String departmentName;

    private Integer departmentSize;

    private Long departmentManager;

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber == null ? null : departmentNumber.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getDepartmentSize() {
        return departmentSize;
    }

    public void setDepartmentSize(Integer departmentSize) {
        this.departmentSize = departmentSize;
    }

    public Long getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(Long departmentManager) {
        this.departmentManager = departmentManager;
    }
}