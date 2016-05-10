package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.EmployeeWork;

public interface EmployeeWorkMapper {
    int deleteByPrimaryKey(Long employeeNumber);

    int insert(EmployeeWork record);

    int insertSelective(EmployeeWork record);

    EmployeeWork selectByPrimaryKey(Long employeeNumber);

    int updateByPrimaryKeySelective(EmployeeWork record);

    int updateByPrimaryKey(EmployeeWork record);
}