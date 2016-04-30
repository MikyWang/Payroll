package com.lejiyu.payroll.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lejiyu.payroll.Common.LoginType;
import com.lejiyu.payroll.Dao.AdminMapper;
import com.lejiyu.payroll.Dao.DepartmentMapper;
import com.lejiyu.payroll.Dao.EmployeeMapper;
import com.lejiyu.payroll.Dao.SalaryMapper;
import com.lejiyu.payroll.Dao.UserMapper;
import com.lejiyu.payroll.Entity.Admin;
import com.lejiyu.payroll.Entity.Department;
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

	@Resource
	DepartmentMapper departmentMapper;

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
		map.put("loginUser", selectUser);
		return map;
	}

	public Employee selectEmployee(Long employeeNumber) {
		return employeeMapper.selectByPrimaryKey(employeeNumber);
	}

	public void deleteEmployee(long employeeNumber) throws Exception {
		Employee employee = employeeMapper.selectByPrimaryKey(employeeNumber);
		if (employee == null) {
			throw new Exception("该员工不存在");
		}
		Department department = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
		if (department == null) {
			throw new Exception("该员工不在此部门!");
		}
		User user = userMapper.selectByPrimaryKey(employeeNumber);
		if (user == null) {
			throw new Exception("没有该用户");
		}
		employeeMapper.deleteByPrimaryKey(employeeNumber);
		department.setDepartmentSize(department.getDepartmentSize() - 1);
		departmentMapper.updateByPrimaryKeySelective(department);
		userMapper.deleteByPrimaryKey(employeeNumber);
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
			User user = getAccount(employee.getEmployNumber());
			if (salary == null) {
				salary = new Salary();
				salary.setEmployNumber(employee.getEmployNumber());
			}
			map.put("account", user);
			map.put("employee", employee);
			map.put("salary", salary);
			empInformation.add(map);
		}
		return empInformation;
	}

	public void powerDown(long employNumber) throws Exception {
		Admin admin = adminMapper.selectByEmployeeNumber(employNumber);
		if (admin == null) {
			throw new Exception("该用户不是管理员!");
		}
		userMapper.deleteByPrimaryKey(admin.getAdminNumber());
		adminMapper.deleteByPrimaryKey(admin.getAdminNumber());
		Department department = departmentMapper.selectByEmployeeNumber(employNumber);
		if (department == null) {
			throw new Exception("该用户没有部门！");
		}
		department.setDepartmentManager(null);
		departmentMapper.updateByPrimaryKey(department);
	}

	public void powerUp(long employeeNumber) throws Exception {
		Employee employee = employeeMapper.selectByPrimaryKey(employeeNumber);
		if (employee == null) {
			throw new Exception("该用户不是员工");
		}
		Admin admin = addAdmin(employee);
		Department department = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
		if (department == null) {
			throw new Exception("该员工没有部门!");
		}
		department.setDepartmentManager(admin.getEmployeeNumber());
		departmentMapper.updateByPrimaryKeySelective(department);
		User userT = userMapper.selectByPrimaryKey(employeeNumber);
		if (userT == null) {
			throw new Exception("该员工没有账号");
		}
		User user = new User();
		user.setUserId(admin.getAdminNumber());
		user.setPassword(userT.getPassword());
		user.setPower(LoginType.admin);
		userMapper.insertSelective(user);
	}

	public Admin addAdmin(Employee employee) throws Exception {
		Admin admin = new Admin();
		admin.setAdminName(employee.getEmployName());
		admin.setEmployeeNumber(employee.getEmployNumber());
		admin.setDepartmentName(employee.getDepartmentName());
		admin.setDepartmentNumber(employee.getDepartmentNumber());
		adminMapper.insert(admin);
		return adminMapper.selectByEmployeeNumber(employee.getEmployNumber());
	}

	public User getAccount(long employNumber) throws Exception {
		User user = userMapper.selectByPrimaryKey(employNumber);
		if (user == null) {
			throw new Exception("员工" + employNumber + "没有配置登录账号!");
		}
		user.setPassword("");
		Admin admin = adminMapper.selectByEmployeeNumber(employNumber);
		if (admin != null) {
			user.setPower(LoginType.admin);
		}
		return user;
	}

	public Admin selectAdmin(Long adminNumber) {
		return adminMapper.selectByPrimaryKey(adminNumber);
	}

}
