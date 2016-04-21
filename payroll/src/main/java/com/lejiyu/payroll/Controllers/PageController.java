package com.lejiyu.payroll.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping(value="modal")
	public String modal(){
		return "Modal";
	}
	
	@RequestMapping(value="index")
	public String index(){
		return "index";
	}
}
