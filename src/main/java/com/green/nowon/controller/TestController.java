package com.green.nowon.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller + ResponseBody : 각각 적어도 됌 같은 뜻
public class TestController {
	
	
	@GetMapping("/user")
	public String user(@AuthenticationPrincipal User user){
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getAuthorities());
		return "인증만 되면 접근 가능";
	}
	
	
	@GetMapping("/hr")
	public String hr() {
		
		return "ROLE_HR 이 있어야해요";
	}
	

	@GetMapping("/hr/member/{no}")
	public String hr(@PathVariable("no") long no) {
		
		return "ROLE_HR 이 있어야해요";
	}
}
