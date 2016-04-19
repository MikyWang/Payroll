package com.lejiyu.payroll.Controllers;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lejiyu.payroll.Entity.Salary;
import com.lejiyu.payroll.Services.SalaryService;

@Controller
public class TestController {
	
	@Resource
	SalaryService salaryService;
	
	@RequestMapping(value = "index")
	public String test() {
		return "index";
	}

	@RequestMapping(value="salary")
	public String salary(String employNumber){
		Salary salary=salaryService.selectSalary(employNumber);
		System.out.println(salary.getEmployNumber()+":"+salary.getActuallySalary());
		return "index";
	}

}
