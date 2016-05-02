package com.lejiyu.payroll.Services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lejiyu.payroll.Dao.SalaryMapper;
import com.lejiyu.payroll.Entity.Salary;

@Service
public class SalaryService {

	@Resource
	SalaryMapper salaryMapper;

	public Salary selectSalary(Long employNumber) throws Exception {
		Salary salary = salaryMapper.selectByPrimaryKey(employNumber);
		if (salary == null) {
			throw new Exception("该员工没有工资表！");
		}
		return salary;
	}

	public void deleteSalary(long employeeNumber) throws Exception {
		Salary salary = selectSalary(employeeNumber);
		if (salary == null) {
			throw new Exception("该员工没有工资表!");
		}
		salaryMapper.deleteByPrimaryKey(employeeNumber);
	}

	public void updateSalary(Salary salary) throws Exception {
		Salary salaryOld = salaryMapper.selectByPrimaryKey(salary.getEmployNumber());
		if (salaryOld == null) {
			throw new Exception("该员工没有工资表!");
		}
		salaryMapper.updateByPrimaryKeySelective(salary);
	}

}
