package com.lejiyu.payroll.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lejiyu.payroll.Common.LoginType;
import com.lejiyu.payroll.Entity.Employee;

@Controller
public class PageController extends BaseController {

	@RequestMapping(value = "index")
	public String index() {
		return verifySign("index");
	}

	@RequestMapping(value = "loginPage")
	public String loginPage() {
		return verifySign("login");
	}

	@RequestMapping(value = "admin")
	public String adminPage() {
		String actUrl = "employee";
		if (verifyPower(LoginType.admin)) {
			actUrl = "admin";
		}
		return verifySign(actUrl);
	}

	public boolean verifyPower(String loginType) {
		boolean success = false;
		if (session.getAttribute("power") != null && session.getAttribute("power").equals(loginType)) {
			success = true;
		}
		return success;
	}

	@RequestMapping(value = "processbar")
	public String getProcess() {
		return "processbar";
	}

	@RequestMapping(value = "employee")
	public String employee() {
		return verifySign("employee");
	}

	@RequestMapping(value = "addEmployee")
	public String addEmployee() {
		return "addEmployee";
	}

	@RequestMapping(value = "showRaises")
	public String showRaises() {
		return "showRaises";
	}

	@RequestMapping(value = "HR")
	public String HR() {
		String actUrl = "employee";
		String power = session.getAttribute("power").toString();
		if (power.equals(LoginType.hr) && session.getAttribute("user") != null) {
			actUrl = "HR";
		}
		return verifySign(actUrl);
	}

	@RequestMapping(value = "requestSalary")
	public String requestSalary() {
		return "requireRaise";
	}

	public String verifySign(String url) {
		String actUrl = "login";
		if (session.getAttribute("user") != null) {
			if (url.equals("index")) {
				if (session.getAttribute("power") != null && session.getAttribute("power").equals(LoginType.admin)) {
					actUrl = "admin";
				} else if (session.getAttribute("power") != null
						&& session.getAttribute("power").equals(LoginType.employee)) {
					actUrl = "employee";

				}
			}
			actUrl = url;
		}
		return actUrl;
	}

}
