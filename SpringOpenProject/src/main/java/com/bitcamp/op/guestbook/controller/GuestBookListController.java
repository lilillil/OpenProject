package com.bitcamp.op.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.op.guestbook.model.GuestBook;
import com.bitcamp.op.guestbook.service.GuestBookListService;

@Controller
public class GuestBookListController {

	@Autowired
	GuestBookListService gbListService;
	
	@RequestMapping("/guestbook/msgList")
	public String guestBookList(@RequestParam(value="page", defaultValue="1") int pagenum, Model model) {
		
		List<GuestBook> guestbook = gbListService.getGuestBookList(pagenum);
		
		model.addAttribute("guestbook", guestbook);
		
		return "guestbook/msgList";
	}
}
