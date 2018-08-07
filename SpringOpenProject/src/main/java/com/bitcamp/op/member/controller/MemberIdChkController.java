package com.bitcamp.op.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.op.member.service.MemberIdChkService;
import com.bitcamp.op.member.service.ServiceException;


@Controller
public class MemberIdChkController {

	@Autowired
	MemberIdChkService memberIdChkService;
	
	@RequestMapping("/idCheck")
	public @ResponseBody String idDuplicateChk(@RequestParam("id") String inputId) throws ServiceException {
		
		String result = "";
		
		int tmp =  memberIdChkService.idDuplicateChk(inputId);
		
		if(tmp == 1) {
			result = "duplicate";
		}
		
		
		
		return result;
	}
}
