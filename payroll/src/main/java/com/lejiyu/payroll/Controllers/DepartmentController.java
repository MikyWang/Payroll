package com.lejiyu.payroll.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lejiyu.payroll.Entity.Department;
import com.lejiyu.payroll.Services.UserService;

@Controller
public class DepartmentController extends BaseController {

	@Resource
	UserService userService;

	@RequestMapping(value = "getDepartments")
	@ResponseBody
	public List<Department> getDepartments(HttpServletResponse response) throws Exception {
		try {
			return userService.selectDepartments();
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "getDepartment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDepartment(String departmentName, HttpServletResponse response) throws Exception {
		try {
			return userService.getDepartmentByName(departmentName);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}
		return null;
	}

	@RequestMapping(value = "createDepartment", method = RequestMethod.POST)
	public void createDepartment(@RequestBody Department department, HttpServletResponse response) throws Exception {
		try {
			userService.createDepartment(department);
		} catch (Exception e) {
			response.setStatus(404);
			response.getWriter().write(e.getMessage());
		}

	}

}
