package com.bitcamp.op.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("guestbook/msgWrite")
public class GuestBookWriteController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String msgWriteForm() {
		
		return "guestbook/msgWriteForm"; 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void msgWrite() {
		
		System.out.println("!! write");
		
	}

}
