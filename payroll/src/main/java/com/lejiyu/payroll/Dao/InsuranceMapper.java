package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.Insurance;

public interface InsuranceMapper {
    int deleteByPrimaryKey(Long employeeId);

    int insert(Insurance record);

    int insertSelective(Insurance record);

    Insurance selectByPrimaryKey(Long employeeId);

    int updateByPrimaryKeySelective(Insurance record);

    int updateByPrimaryKey(Insurance record);
}