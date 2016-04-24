package com.lejiyu.payroll.Controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

	@Resource
	HttpSession session;

}
