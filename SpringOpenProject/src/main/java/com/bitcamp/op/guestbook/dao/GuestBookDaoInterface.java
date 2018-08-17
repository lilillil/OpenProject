package com.bitcamp.op.guestbook.dao;

import java.util.List;

import com.bitcamp.op.guestbook.model.GuestBook;
import com.bitcamp.op.member.model.Member;

public interface GuestBookDaoInterface {

	//mapper : 패키지이름 + 클래스 이름으로 찾음
	//id : 메서드 이름 으로 찾음

	public int writeGuestBook(GuestBook guestbook);
	
	public int guestBookTotalCnt();

	public List<GuestBook> getGuestBookList(int firstRow, int msgCntPerPage);
	
}
