package com.lejiyu.payroll.Dao;

import com.lejiyu.payroll.Entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentNumber);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String departmentNumber);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}