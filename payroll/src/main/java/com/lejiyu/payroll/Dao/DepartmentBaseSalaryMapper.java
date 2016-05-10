package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.DepartmentBaseSalary;

public interface DepartmentBaseSalaryMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(DepartmentBaseSalary record);

    int insertSelective(DepartmentBaseSalary record);

    DepartmentBaseSalary selectByPrimaryKey(String departmentId);

    int updateByPrimaryKeySelective(DepartmentBaseSalary record);

    int updateByPrimaryKey(DepartmentBaseSalary record);
}