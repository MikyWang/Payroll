package com.lejiyu.payroll.Dao;

import java.util.List;

import com.lejiyu.payroll.Entity.Employee;

public interface EmployeeMapper {
	int deleteByPrimaryKey(Long employNumber);

	int insert(Employee record);

	int insertSelective(Employee record);

	Employee selectByPrimaryKey(Long employNumber);

	int updateByPrimaryKeySelective(Employee record);

	int updateByPrimaryKey(Employee record);

	List<Employee> selectByEmployeeName(String employName);

	List<Employee> selectAll();
}