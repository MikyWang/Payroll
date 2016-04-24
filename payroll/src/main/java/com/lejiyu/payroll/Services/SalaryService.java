package com.lejiyu.payroll.Services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lejiyu.payroll.Dao.SalaryMapper;
import com.lejiyu.payroll.Entity.Salary;

@Service
public class SalaryService {

	@Resource
	SalaryMapper salaryMapper;

	public Salary selectSalary(Long employNumber) {
		return salaryMapper.selectByPrimaryKey(employNumber);
	}

}
