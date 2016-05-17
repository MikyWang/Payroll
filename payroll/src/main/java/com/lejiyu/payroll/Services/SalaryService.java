package com.lejiyu.payroll.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.stereotype.Service;

import com.lejiyu.payroll.Common.SalaryHelper;
import com.lejiyu.payroll.Dao.DepartmentBaseSalaryMapper;
import com.lejiyu.payroll.Dao.EmployeeMapper;
import com.lejiyu.payroll.Dao.EmployeeWorkMapper;
import com.lejiyu.payroll.Dao.InsuranceMapper;
import com.lejiyu.payroll.Dao.SalaryMapper;
import com.lejiyu.payroll.Entity.DepartmentBaseSalary;
import com.lejiyu.payroll.Entity.Employee;
import com.lejiyu.payroll.Entity.EmployeeWork;
import com.lejiyu.payroll.Entity.Insurance;
import com.lejiyu.payroll.Entity.Salary;

@Service
public class SalaryService {

	@Resource
	SalaryMapper salaryMapper;

	@Resource
	EmployeeMapper employeeMapper;

	@Resource
	EmployeeWorkMapper employeeWorkMapper;

	@Resource
	DepartmentBaseSalaryMapper departmentBaseSalaryMapper;
	
	@Resource
	InsuranceMapper insuranceMapper;

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

	public List<Map<String, Object>> getEmployeeSalarys() throws Exception {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<Employee> employees = employeeMapper.selectAll();
		if (employees == null) {
			throw new Exception("没有员工");
		}
		for (Employee employee : employees) {
			Salary salary = salaryMapper.selectByPrimaryKey(employee.getEmployNumber());
			if (salary == null) {
				throw new Exception("员工没有对应的工资表");
			}
			EmployeeWork employeeWork = employeeWorkMapper.selectByPrimaryKey(employee.getEmployNumber());
			if (employeeWork == null) {
				throw new Exception("员工没有工龄");
			}
			DepartmentBaseSalary departmentBaseSalary = departmentBaseSalaryMapper
					.selectByPrimaryKey(employee.getDepartmentNumber());
			if (departmentBaseSalary == null) {
				throw new Exception("部门没有工资表");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("employee", employee);
			map.put("salary", salary);
			map.put("employeeWork", employeeWork);
			map.put("departmentBaseSalary", departmentBaseSalary);
			maps.add(map);
		}
		return maps;
	}

	public void updateEmployeeSalary(Map<String, Object> map) throws Exception {
		String employeeNumber = map.get("employNumber").toString();
		EmployeeWork employeeWork = employeeWorkMapper.selectByPrimaryKey(Long.valueOf(employeeNumber));
		if (employeeWork == null) {
			throw new Exception("没有该员工工龄表");
		}
		Salary salary = salaryMapper.selectByPrimaryKey(Long.valueOf(employeeNumber));
		if (salary == null) {
			throw new Exception("该员工没有工资");
		}
		Employee employee = employeeMapper.selectByPrimaryKey(Long.valueOf(employeeNumber));
		if (employee == null) {
			throw new Exception("没有该员工");
		}
		DepartmentBaseSalary departmentBaseSalary = departmentBaseSalaryMapper
				.selectByPrimaryKey(employee.getDepartmentNumber());
		employeeWork.setEmployeeLevel(Integer.valueOf(map.get("level").toString()));
		employeeWork.setEmployeeSeniority(Integer.valueOf(map.get("seniority").toString()));
		salary.setFine(BigDecimal.valueOf(Long.valueOf(map.get("fine").toString())));
		salary.setOvertimeSalary(BigDecimal.valueOf(Long.valueOf(map.get("overtimeSalary").toString())));
		BigDecimal expectSalary = SalaryHelper.CalculateExpectSalary(
				BigDecimal.valueOf(departmentBaseSalary.getDepartmentBaseSalary()),
				BigDecimal.valueOf(departmentBaseSalary.getSeniorityBaseSalary()),
				BigDecimal.valueOf(departmentBaseSalary.getLevelBaseSalary()), employeeWork.getEmployeeSeniority(),
				employeeWork.getEmployeeLevel());
		salary.setExpectSalary(expectSalary);
		Insurance insurance=SalaryHelper.CalculateInsurance(expectSalary);
		insurance.setEmployeeId(employee.getEmployNumber());
		salary.setActuallySalary(SalaryHelper.CalculateActuallySalary(salary));
		insuranceMapper.updateByPrimaryKeySelective(insurance);
		employeeWorkMapper.updateByPrimaryKeySelective(employeeWork);
		salaryMapper.updateByPrimaryKeySelective(salary);
	}

}
