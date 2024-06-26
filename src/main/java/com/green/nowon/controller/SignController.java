package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class SignController {
	
	@GetMapping("/signup")
	public String signup() {
		return "views/user/write";
	}
	

}
