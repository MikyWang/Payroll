package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.Salary;

public interface SalaryMapper {
    int deleteByPrimaryKey(Long employNumber);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Long employNumber);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);
}