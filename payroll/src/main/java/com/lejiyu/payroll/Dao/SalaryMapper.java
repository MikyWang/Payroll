package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.Salary;

public interface SalaryMapper {
    int deleteByPrimaryKey(String employNumber);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(String employNumber);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);
}