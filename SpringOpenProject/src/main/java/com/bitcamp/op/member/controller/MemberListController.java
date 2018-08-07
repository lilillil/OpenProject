package com.bitcamp.op.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.op.member.model.Member;
import com.bitcamp.op.member.service.MemberListService;

@Controller
public class MemberListController {

	@Autowired
	MemberListService memberListService;
	
	@RequestMapping("/member/memberList")
	
	public String getMemberList(@RequestParam(value="page", defaultValue="1") int pageNumber, Model model) {
		
		System.out.println("pnum : " +pageNumber);
		
		List<Member> memberList = memberListService.getMemberList(pageNumber);
		int pageCnt = memberListService.pageCount();
		
		int startPage = (pageNumber == 1 ) ? 1 : ((pageNumber-1)/5)*5+1;
		
		System.out.println("% : " +startPage%5);
		
		System.out.println("pageNumber%5 :"+ pageNumber%5);
//		if(pageNumber%5 == 0) {
//			startPage = startPage - 4;
//		}
		
		int endPage =  ((startPage + 5 -1)< pageCnt) ? startPage + 5 -1 : pageCnt ; 
		
		System.out.println("pageCnt : "+pageCnt);
		System.out.println("start : "+startPage);
		System.out.println("end : "+endPage);
		
		model.addAttribute("memberList", memberList); 
		model.addAttribute("pageCnt", pageCnt); 
		model.addAttribute("startPage", startPage); 
		model.addAttribute("endPage", endPage); 
		
		return "member/memberList";
	}
	
}
