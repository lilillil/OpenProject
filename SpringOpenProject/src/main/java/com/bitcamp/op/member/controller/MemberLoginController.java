package com.bitcamp.op.member.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.op.member.model.Member;
import com.bitcamp.op.member.service.MemberLoginService;
import com.bitcamp.op.member.service.ServiceException;

@Controller
public class MemberLoginController {

	@Autowired
	MemberLoginService memberLoginService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginForm() {
		
		return "member/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLoginForm(HttpServletRequest request, Model model) throws ServiceException, SQLException {
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//model 처리
		boolean loginChk = memberLoginService.login(request, id, pw);
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		model.addAttribute("loginChk", loginChk);
		
		return "member/loginOk";
	}

}
