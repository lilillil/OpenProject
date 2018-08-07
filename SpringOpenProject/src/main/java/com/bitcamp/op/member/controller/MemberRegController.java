package com.bitcamp.op.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.op.member.model.Member;
import com.bitcamp.op.member.service.MemberRegService;

@Controller
@RequestMapping("/member/memberReg")
public class MemberRegController {

	@Autowired
	MemberRegService memberRegService; //new하고 만들지 않고 의존설정을 해준다
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView memRegForm() {
		
		String viewName = "member/memberRegForm";		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView processMemReg(Member member, HttpServletRequest request) throws Exception {
		
		String viewName = "member/memberReg";
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		
		int insertCnt = memberRegService.RegMember(member, request);
		
		modelAndView.addObject("insertCnt", insertCnt);
		
		
//		System.out.println(member);
		
		return modelAndView;
	}
}
