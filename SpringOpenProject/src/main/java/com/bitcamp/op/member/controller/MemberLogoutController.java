package com.bitcamp.op.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberLogoutController {

	@RequestMapping("/member/logout")
	public String logout(HttpServletRequest request) {
		
		request.getSession(false).invalidate();
		
		//redirect에 context path가 포함되어있다.
		return "redirect:/";
	}
}
