package com.lejiyu.payroll.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lejiyu.payroll.Common.LoginType;
import com.lejiyu.payroll.Entity.Admin;
import com.lejiyu.payroll.Entity.Employee;
import com.lejiyu.payroll.Entity.User;
import com.lejiyu.payroll.Services.UserService;

@Controller
public class UserController extends BaseController {

	@Resource
	UserService userService;

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
			session.setAttribute("user", map.get("user"));
			session.setAttribute("power", map.get("power"));
			if (map.get("power").toString().equals(LoginType.admin)) {
				url = "admin";
			} else if (map.get("power").toString().equals(LoginType.employee)) {
				url = "employee";
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

	@RequestMapping(value = "getUser")
	@ResponseBody
	public Map<String, Object> getUser(HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (session.getAttribute("user") == null) {
			response.setStatus(404);
			response.getWriter().write("没有值");
		}
		if (session.getAttribute("user") != null) {
				map.put("user", session.getAttribute("user"));
		}
		return map;
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

}
