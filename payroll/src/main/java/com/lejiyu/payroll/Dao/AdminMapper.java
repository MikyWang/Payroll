package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Long adminNumber);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long adminNumber);

    Admin selectByEmployeeNumber(Long employNumber);
    
    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}