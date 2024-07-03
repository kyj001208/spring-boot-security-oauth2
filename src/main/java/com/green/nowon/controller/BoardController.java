package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BoardController {
	
	@GetMapping("/boards")
	public String list() {
		return "views/board/list";
	}
	
	
	@GetMapping("/boards/new")
	public String newWrite() {
		return "views/board/write";
	}
	

}
