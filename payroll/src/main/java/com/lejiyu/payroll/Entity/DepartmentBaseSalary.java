package com.lejiyu.payroll.Entity;

public class DepartmentBaseSalary {
    private String departmentId;

    private Long departmentBaseSalary;

    private Long seniorityBaseSalary;

    private Long levelBaseSalary;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public Long getDepartmentBaseSalary() {
        return departmentBaseSalary;
    }

    public void setDepartmentBaseSalary(Long departmentBaseSalary) {
        this.departmentBaseSalary = departmentBaseSalary;
    }

    public Long getSeniorityBaseSalary() {
        return seniorityBaseSalary;
    }

    public void setSeniorityBaseSalary(Long seniorityBaseSalary) {
        this.seniorityBaseSalary = seniorityBaseSalary;
    }

    public Long getLevelBaseSalary() {
        return levelBaseSalary;
    }

    public void setLevelBaseSalary(Long levelBaseSalary) {
        this.levelBaseSalary = levelBaseSalary;
    }
}