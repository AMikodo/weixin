package com.star.project.weixin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("pages")
public class PagesController {
	@RequestMapping("login")
	public String toLoginPage() {
		return "redirect:/pages/login.jsp";
	}
	@RequestMapping("userInfo")
	public String toUserPage(@RequestParam(value="email")String email) {
		
		return "redirect:/pages/userInfo.jsp";
	}
	
}
