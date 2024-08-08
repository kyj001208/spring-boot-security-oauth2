package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService service;
	
	//회원가입페이지 이동
	@GetMapping("/signup")
	public String signup() {
		return "views/user/write";
	}
	
	//회원가입 후 저장 해주는 컨트롤러 (db 저장)
	@PostMapping("/signup")
	public String signup(MemberSaveDTO dto) {
		
		service.signupProcess(dto);
		return "redirect:/login?signup"; //회원가입 성공 시 -로그인 페이지로이동
	}
	
	//로그인 페이지 이동 
	//referer가 null이 아니고 /signup 또는 /login 페이지가 아닌 경우, referer URL을 세션에 prevPage라는 이름으로 저장합니다. 이는 로그인 후 사용자가 이전 페이지로 돌아갈 수 있게 하기 위한 것입니다.
	@GetMapping("/login")
	public String signin(HttpServletRequest request) {
		String referer=request.getHeader("Referer");
		System.out.println("referer"+referer);
		//302리다이렉트 페이지
		if(referer != null && !referer.contains("/signup") && !referer.contains("/login")) {
		request.getSession().setAttribute("prevPage", referer);
		}
		return "views/user/login";
	}
	
	

}
