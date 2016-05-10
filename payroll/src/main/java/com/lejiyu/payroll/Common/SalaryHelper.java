package com.lejiyu.payroll.Common;

import java.math.BigDecimal;

import com.lejiyu.payroll.Entity.Salary;

public class SalaryHelper {

	public static BigDecimal CalculateExpectSalary(BigDecimal departmentBaseSalary, BigDecimal seniorityBaseSalary,
			BigDecimal levelBaseSalary, int seniority, int level) {
		BigDecimal expectSalary = new BigDecimal(0.00);
		BigDecimal senioritySalary = seniorityBaseSalary.multiply(BigDecimal.valueOf(seniority));
		BigDecimal levelSalary = levelBaseSalary.multiply(BigDecimal.valueOf(level));
		expectSalary = departmentBaseSalary.add(senioritySalary).add(levelSalary);
		return expectSalary;
	}

	public static BigDecimal CalculateActuallySalary(Salary salary) throws Exception{
		BigDecimal actuallySalary = new BigDecimal(0.00);
		if (salary.getFine()==null) {
			salary.setFine(BigDecimal.valueOf(0));
		}
		if (salary.getOvertimeSalary()==null) {
			salary.setOvertimeSalary(BigDecimal.valueOf(0));
		}
		if (salary.getExpectSalary()==null) {
			throw new Exception("该员工没有基础工资");
		}
		actuallySalary = actuallySalary.add(salary.getExpectSalary());
		actuallySalary = actuallySalary.add(salary.getOvertimeSalary());
		actuallySalary = actuallySalary.subtract(salary.getFine());
		return actuallySalary;
	}
}
