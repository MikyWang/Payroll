package com.lejiyu.payroll.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lejiyu.payroll.Common.LoginType;
import com.lejiyu.payroll.Entity.Employee;
import com.lejiyu.payroll.Entity.RequireRaise;
import com.lejiyu.payroll.Entity.Salary;
import com.lejiyu.payroll.Entity.User;
import com.lejiyu.payroll.Services.SalaryService;
import com.lejiyu.payroll.Services.UserService;

@Controller
public class UserController extends BaseController {

	@Resource
	UserService userService;

	@Resource
	SalaryService salaryService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(boolean autoLogin, @RequestBody User user, HttpServletResponse response) throws Exception {
		String url = null;
		session.setAttribute("autoLogin", autoLogin);
		Map<String, Object> map;
		try {

			map = userService.selectUser(user);
			if (!autoLogin) {
				session.setMaxInactiveInterval(30 * 60);
			}
			session.setAttribute("loginUser", map.get("loginUser"));
			session.setAttribute("user", map.get("user"));
			session.setAttribute("power", map.get("power"));
			if (map.get("power").toString().equals(LoginType.admin)) {
				url = "admin";
			} else if (map.get("power").toString().equals(LoginType.employee)) {
				url = "employee";
			} else if (map.get("power").toString().equals(LoginType.hr)) {
				url = "HR";
			}
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return url;
	}

	@RequestMapping(value = "logout")
	@ResponseBody
	public String logout() {
		session.removeAttribute("user");
		session.removeAttribute("power");
		return "index";
	}

	@RequestMapping(value = "saveEmployee", method = RequestMethod.POST)
	public void saveEmployee(@RequestBody Map<String, Object> map, HttpServletResponse response) throws Exception {
		try {
			userService.saveEmployee(map);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}

	}

	@RequestMapping(value = "getUser")
	@ResponseBody
	public Map<String, Object> getUser(HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (session.getAttribute("user") == null || session.getAttribute("loginUser") == null) {
			response.setStatus(404);
			response.getWriter().write("没有值");
		}
		if (session.getAttribute("user") != null) {
			map.put("user", session.getAttribute("user"));
			User user = (User) session.getAttribute("loginUser");
			user.setPassword("");
			map.put("account", user);
		}
		return map;
	}

	@RequestMapping(value = "employeeAppend", method = RequestMethod.POST)
	@ResponseBody
	public User addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = null;
		try {
			user = userService.addEmployee(request);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return user;
	}

	@RequestMapping(value = "deleteEmployee", method = RequestMethod.POST)
	public void deleteEmployee(Long employeeNumber, HttpServletResponse response) throws Exception {
		try {
			userService.deleteEmployee(employeeNumber);
			salaryService.deleteSalary(employeeNumber);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "powerUp", method = RequestMethod.POST)
	public void powerUp(Long employeeNumber, HttpServletResponse response) throws Exception {
		try {
			userService.powerUp(employeeNumber);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "powerDown", method = RequestMethod.POST)
	public void powerDown(Long employeeNumber, HttpServletResponse response) throws Exception {
		try {
			userService.powerDown(employeeNumber);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "getEmployees")
	@ResponseBody
	public List<Map<String, Object>> getEmployees(HttpServletResponse response) throws Exception {
		List<Map<String, Object>> empinformation = null;
		try {
			empinformation = userService.getEmployees();
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return empinformation;
	}

	@RequestMapping(value = "getSalary", method = RequestMethod.POST)
	@ResponseBody
	public Salary getSalary(Long employNumber, HttpServletResponse response) throws Exception {
		try {
			return salaryService.selectSalary(employNumber);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "saveEmployeeOne", method = RequestMethod.POST)
	public void saveEmployeeOne(@RequestBody Employee employee, HttpServletResponse response) throws Exception {
		try {
			userService.saveEmployeeOne(employee);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "saveSalary", method = RequestMethod.POST)
	public void saveSalary(@RequestBody Salary salary, HttpServletResponse response) throws Exception {
		try {
			salaryService.updateSalary(salary);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "requireRaise", method = RequestMethod.POST)
	public void requireRaise(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			userService.requireRaise(request, session);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "getEmployeeSalarys")
	@ResponseBody
	public List<Map<String, Object>> getEmployeeSalarys(HttpServletResponse response) throws Exception {
		try {
			return salaryService.getEmployeeSalarys();
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "updateEmployeeSalary", method = RequestMethod.POST)
	public void updateEmployeeSalary(@RequestBody Map<String, Object> map, HttpServletResponse response) throws Exception {
		try {
			System.out.println(map);
			salaryService.updateEmployeeSalary(map);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "getRaises")
	@ResponseBody
	public List<Map<String, Object>> getRaises(HttpServletResponse response) throws Exception {
		try {
			
			return userService.getRaises();
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "updateRaise", method = RequestMethod.POST)
	public void updateRaise(@RequestBody RequireRaise requireRaise, HttpServletResponse response) throws Exception {
		try {
			System.out.println(requireRaise);
			userService.updateRaise(requireRaise);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
	}

	@RequestMapping(value = "getEmployee", method = RequestMethod.POST)
	@ResponseBody
	public Employee getEmployee(HttpServletResponse response) throws Exception {
		try {
			Employee employee = null;
			if (session.getAttribute("user") != null) {
				employee = (Employee) session.getAttribute("user");
			}
			if (employee == null) {
				throw new Exception("用户没有登陆");
			}
			return userService.selectEmployee(employee.getEmployNumber());
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return null;
	}
}
