package com.lejiyu.payroll.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lejiyu.payroll.Common.LoginType;
import com.lejiyu.payroll.Common.RaiseStatus;
import com.lejiyu.payroll.Dao.AdminMapper;
import com.lejiyu.payroll.Dao.DepartmentMapper;
import com.lejiyu.payroll.Dao.EmployeeMapper;
import com.lejiyu.payroll.Dao.RequireRaiseMapper;
import com.lejiyu.payroll.Dao.SalaryMapper;
import com.lejiyu.payroll.Dao.UserMapper;
import com.lejiyu.payroll.Entity.Admin;
import com.lejiyu.payroll.Entity.Department;
import com.lejiyu.payroll.Entity.Employee;
import com.lejiyu.payroll.Entity.RequireRaise;
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
	RequireRaiseMapper requireRaiseMapper;

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

	public Employee selectEmployee(Long employeeNumber) throws Exception {
		Employee employee = employeeMapper.selectByPrimaryKey(employeeNumber);
		if (employee == null) {
			throw new Exception("没有该员工!");
		}
		return employee;
	}

	@Transactional
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

	@Transactional
	public User addEmployee(HttpServletRequest request) throws Exception {
		Department department = departmentMapper.selectByPrimaryKey(request.getParameter("departmentNumber"));
		if (department == null) {
			throw new Exception("没有这个部门!");
		}
		department.setDepartmentSize(department.getDepartmentSize() + 1);
		Employee employee = new Employee();
		employee.setEmployName(request.getParameter("employeeName"));
		employee.setSex(request.getParameter("sex"));
		employee.setDepartmentNumber(department.getDepartmentNumber());
		employee.setDepartmentName(department.getDepartmentName());
		employee.setEducation(request.getParameter("education"));
		employeeMapper.insert(employee);
		List<Employee> employees = employeeMapper.selectByEmployeeName(employee.getEmployName());
		if (employees.isEmpty()) {
			throw new Exception("添加失败!");
		}
		Salary salary = new Salary();

		salary.setEmployNumber(employees.get(0).getEmployNumber());
		BigDecimal expectSalary = BigDecimal.valueOf(Double.valueOf(request.getParameter("expectSalary")));
		if (expectSalary == null) {
			throw new Exception("请输入数字");
		}
		salary.setExpectSalary(expectSalary);

		User user = new User();
		user.setUserId(employees.get(0).getEmployNumber());
		user.setPassword("123456");
		user.setPower(LoginType.employee);
		departmentMapper.updateByPrimaryKey(department);
		salaryMapper.insertSelective(salary);
		userMapper.insertSelective(user);

		return user;
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

	@Transactional
	public void powerDown(long employNumber) throws Exception {
		Admin admin = adminMapper.selectByEmployeeNumber(employNumber);
		if (admin == null) {
			throw new Exception("该用户不是管理员!");
		}
		Department department = departmentMapper.selectByEmployeeNumber(employNumber);
		if (department == null) {
			throw new Exception("该用户没有部门！");
		}
		department.setDepartmentManager(null);
		userMapper.deleteByPrimaryKey(admin.getAdminNumber());
		adminMapper.deleteByPrimaryKey(admin.getAdminNumber());
		departmentMapper.updateByPrimaryKey(department);
	}

	@Transactional
	public void powerUp(long employeeNumber) throws Exception {
		Employee employee = employeeMapper.selectByPrimaryKey(employeeNumber);
		if (employee == null) {
			throw new Exception("该用户不是员工");
		}
		Department department = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
		if (department == null) {
			throw new Exception("该员工没有部门!");
		}
		if (department.getDepartmentManager() != null) {
			throw new Exception("一个部门只能拥有一个管理员!");
		}
		User userT = userMapper.selectByPrimaryKey(employeeNumber);
		if (userT == null) {
			throw new Exception("该员工没有账号");
		}
		Admin admin = addAdmin(employee);
		department.setDepartmentManager(admin.getEmployeeNumber());
		departmentMapper.updateByPrimaryKeySelective(department);
		User user = new User();
		user.setUserId(admin.getAdminNumber());
		user.setPassword(userT.getPassword());
		user.setPower(LoginType.admin);
		userMapper.insertSelective(user);
	}

	@Transactional
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

	@Transactional
	public void saveEmployee(Map<String, Object> map) throws Exception {
		Employee employee = employeeMapper.selectByPrimaryKey(Long.valueOf(map.get("employNumber").toString()));
		Salary salary = salaryMapper.selectByPrimaryKey(Long.valueOf(map.get("employNumber").toString()));
		if (employee == null) {
			throw new Exception("没有该员工!");
		}
		if (salary == null) {
			throw new Exception("该员工没有工资表");
		}
		if (!employee.getDepartmentName().equals(map.get("departmentName").toString())) {
			Department departmentNow = departmentMapper.selectByDepartmentName((String) map.get("departmentName"));
			Department departmentOld = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
			if (departmentNow == null || departmentOld == null) {
				throw new Exception("没有该部门");
			}
			departmentNow.setDepartmentSize(departmentNow.getDepartmentSize() + 1);
			departmentOld.setDepartmentSize(departmentOld.getDepartmentSize() - 1);
			employee.setDepartmentNumber(departmentNow.getDepartmentNumber());
			employee.setDepartmentName(departmentNow.getDepartmentName());
			departmentMapper.updateByPrimaryKeySelective(departmentNow);
			departmentMapper.updateByPrimaryKeySelective(departmentOld);
		}
		Admin admin = adminMapper.selectByEmployeeNumber(employee.getEmployNumber());
		employee.setEmployName((String) map.get("employName"));
		employee.setEducation((String) map.get("education"));
		if (admin != null) {
			admin.setAdminName(employee.getEmployName());
			adminMapper.updateByPrimaryKey(admin);
		}
		employeeMapper.updateByPrimaryKeySelective(employee);
		salary.setExpectSalary(BigDecimal.valueOf(Float.valueOf(map.get("expMoney").toString())));
		salary.setFine(BigDecimal.valueOf(Float.valueOf(map.get("fine").toString())));
		salary.setOfficeDay((int) map.get("officeDay"));
		salary.setOvertime((int) map.get("overtime"));
		salary.setOvertimeSalary(BigDecimal.valueOf(Float.valueOf(map.get("overtimeSalary").toString())));
		salary.setActuallySalary(salary.getExpectSalary().add(salary.getOvertimeSalary()).subtract(salary.getFine()));
		salaryMapper.updateByPrimaryKeySelective(salary);
	}

	public List<Department> selectDepartments() throws Exception {
		List<Department> departments = departmentMapper.selectAll();
		if (departments.isEmpty()) {
			throw new Exception("没有部门!");
		}
		return departments;
	}

	@Transactional
	public void createDepartment(Department department) throws Exception {
		Department departmentOld = departmentMapper.selectByDepartmentName(department.getDepartmentName());
		if (departmentOld != null) {
			throw new Exception("该部门已存在！");
		}
		if (department.getDepartmentManager() != null) {
			Admin admin = adminMapper.selectByEmployeeNumber(department.getDepartmentManager());
			if (admin != null) {
				throw new Exception("一个管理员最多只能管理一个部门");
			}
			Employee employee = employeeMapper.selectByPrimaryKey(department.getDepartmentManager());
			if (employee == null) {
				throw new Exception("没有此员工！");
			}
			User user = userMapper.selectByPrimaryKey(employee.getEmployNumber());
			if (user == null) {
				throw new Exception("此员工没有账号!");
			}
			department.setDepartmentSize(1);
			department.setDepartmentManager(null);
			departmentMapper.insertSelective(department);
			department = departmentMapper.selectByDepartmentName(department.getDepartmentName());
			departmentOld = departmentMapper.selectByPrimaryKey(employee.getDepartmentNumber());
			if (departmentOld != null) {
				departmentOld.setDepartmentSize(departmentOld.getDepartmentSize() - 1);
				departmentMapper.updateByPrimaryKeySelective(departmentOld);
			}
			employee.setDepartmentNumber(department.getDepartmentNumber());
			employee.setDepartmentName(department.getDepartmentName());
			employeeMapper.updateByPrimaryKeySelective(employee);
			powerUp(employee.getEmployNumber());
		} else {
			department.setDepartmentSize(0);
			departmentMapper.insertSelective(department);
		}
	}

	public Map<String, Object> getDepartmentByName(String departmentName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Department department = departmentMapper.selectByDepartmentName(departmentName);
		if (department == null) {
			throw new Exception("没有该部门!");
		}
		map.put("department", department);
		Employee employee = employeeMapper.selectByPrimaryKey(department.getDepartmentManager());
		if (employee == null) {
			throw new Exception("没有该管理员!");
		}
		map.put("departmentManagerName", employee.getEmployName());
		return map;
	}

	public void saveEmployeeOne(Employee employee) throws Exception {
		Employee employeeOld = employeeMapper.selectByPrimaryKey(employee.getEmployNumber());
		if (employeeOld == null) {
			throw new Exception("没有该员工!");
		}
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	public void requireRaise(HttpServletRequest request, HttpSession session) throws Exception {
		RequireRaise raise = new RequireRaise();
		if (session.getAttribute("user") == null) {
			throw new Exception("请先登录!");
		}
		Employee employee = (Employee) session.getAttribute("user");
		Department department = departmentMapper.selectByEmployeeNumber(employee.getEmployNumber());
		if (department == null) {
			throw new Exception("未知部门!");
		}
		raise.setRequirerId(employee.getEmployNumber());
		raise.setManagerId(department.getDepartmentManager());
		raise.setRequireMoney(Long.valueOf(request.getParameter("requireMoney").toString()));
		raise.setContent(request.getParameter("content").toString());
		raise.setStatus(RaiseStatus.Create);
		requireRaiseMapper.insertSelective(raise);
	}

	public List<RequireRaise> getRaises() throws Exception {
		List<RequireRaise> raises = requireRaiseMapper.selectAll();
		if (raises.isEmpty()) {
			throw new Exception("没有信息!");
		}
		return raises;
	}

}
