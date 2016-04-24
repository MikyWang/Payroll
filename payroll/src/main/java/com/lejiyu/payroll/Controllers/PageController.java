package com.lejiyu.payroll.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lejiyu.payroll.Common.LoginType;

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
