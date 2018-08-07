package com.bitcamp.op.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookListController {

	@RequestMapping("/guestbook/msgList")
	public String guestBookList() {
		
		return "guestbook/msgList";
	}
}
