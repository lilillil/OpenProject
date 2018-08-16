package com.bitcamp.op.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.op.guestbook.model.GuestBook;
import com.bitcamp.op.guestbook.service.GuestBookWriteService;

@Controller
@RequestMapping("guestbook/msgWrite")
public class GuestBookWriteController {
	
	@Autowired
	GuestBookWriteService gbservice;
	
	@InitBinder     /* Converts empty strings into null when a form is submitted */ 
	public void initBinder(WebDataBinder binder) {  
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));  
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String msgWriteForm() {
		
		return "guestbook/msgWriteForm"; 
	}
	@RequestMapping(method=RequestMethod.POST)
	public String msgWrite(GuestBook guestbook, Model model) {
		
		int result = 0;
		
		result = gbservice.write(guestbook);
		
		if(result == 0) {
			model.addAttribute("msg", "fail to write a guest book");
			return "guestbook/msgWriteForm";
		}else {
			return "guestbook/msgList";
		}
		
		
		
	}

}
