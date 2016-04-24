package com.lejiyu.payroll.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lejiyu.payroll.Common.LoginType;
import com.lejiyu.payroll.Dao.AdminMapper;
import com.lejiyu.payroll.Dao.EmployeeMapper;
import com.lejiyu.payroll.Dao.SalaryMapper;
import com.lejiyu.payroll.Dao.UserMapper;
import com.lejiyu.payroll.Entity.Admin;
import com.lejiyu.payroll.Entity.Employee;
import com.lejiyu.payroll.Entity.Salary;
import com.lejiyu.payroll.Entity.User;

@Service
public class UserService {

	@Resource
	UserMapper userMapper;

	@Resource
	EmployeeMapper employeeMapper;

	@Resource
	AdminMapper adminMapper;

	@Resource
	SalaryMapper salaryMapper;

	public Map<String, Object> selectUser(User user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User selectUser = userMapper.selectByPrimaryKey(user.getUserId());
		if (selectUser == null) {
			throw new Exception("此用户不存在");
		}
		if (!user.getPassword().equals(selectUser.getPassword())) {
			throw new Exception("密码错误");
		}
		if (!user.getPower().equals(selectUser.getPower())) {
			throw new Exception("该用户不是" + user.getPower());
		}
		map.put("power", selectUser.getPower());
		if (selectUser.getPower().equals(LoginType.admin)) {
			Admin admin = adminMapper.selectByPrimaryKey(selectUser.getUserId());
			if (admin == null) {
				throw new Exception("该用户不是管理员");
			}
			Employee employee = employeeMapper.selectByPrimaryKey(admin.getEmployeeNumber());
			map.put("user", employee);
		} else if (selectUser.getPower().equals(LoginType.employee)) {
			Employee employee = employeeMapper.selectByPrimaryKey(selectUser.getUserId());
			if (employee == null) {
				throw new Exception("没有这个员工");
			}
			map.put("user", employee);
		}
		return map;
	}

	public Employee selectEmployee(Long employeeNumber) {
		return employeeMapper.selectByPrimaryKey(employeeNumber);
	}

	public List<Map<String, Object>> getEmployees() throws Exception {
		List<Map<String, Object>> empInformation = new ArrayList<Map<String, Object>>();
		List<Employee> eList = employeeMapper.selectAll();
		if (eList == null || eList.size() < 1) {
			throw new Exception("该部门没有员工");
		}
		for (Employee employee : eList) {
			Map<String, Object> map = new HashMap<String, Object>();
			Salary salary = salaryMapper.selectByPrimaryKey(employee.getEmployNumber());
			if (salary == null) {
				salary = new Salary();
				salary.setEmployNumber(employee.getEmployNumber());
			}
			map.put("employee", employee);
			map.put("salary", salary);
			empInformation.add(map);
		}
		return empInformation;
	}

	public Admin selectAdmin(Long adminNumber) {
		return adminMapper.selectByPrimaryKey(adminNumber);
	}

}
