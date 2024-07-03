package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.nowon.domain.entity.Role;


@Controller
public class EmpController {
	
	@GetMapping("/emp")
	public String getMethodName() {
		
		return "views/emp/list";
	}
	
	

}
