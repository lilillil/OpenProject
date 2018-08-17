package com.bitcamp.op.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.op.member.model.Member;
import com.bitcamp.op.member.service.MemberListService;
import com.bitcamp.op.paging.model.Paging;

@Controller
public class MemberListController {

	@Autowired
	MemberListService memberListService;
	
	@RequestMapping("/member/memberList")
	
	public String getMemberList(@RequestParam(value="page", defaultValue="1") int pagenum, Model model) {
		
		List<Member> memberList = memberListService.getMemberList(pagenum);
		Paging paging = memberListService.pageCount(pagenum);

		model.addAttribute("memberList", memberList); 
		model.addAttribute("paging", paging); 
		
		return "member/memberList";
	}
	
}
