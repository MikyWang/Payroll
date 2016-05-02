package com.lejiyu.payroll.Dao;

import java.util.List;

import com.lejiyu.payroll.Entity.Department;

public interface DepartmentMapper {
	int deleteByPrimaryKey(String departmentNumber);

	int insert(Department record);

	int insertSelective(Department record);

	Department selectByPrimaryKey(String departmentNumber);

	Department selectByEmployeeNumber(long employeeNumber);

	Department selectByDepartmentName(String departmentName);

	int updateByPrimaryKeySelective(Department record);

	int updateByPrimaryKey(Department record);

	List<Department> selectAll();
}