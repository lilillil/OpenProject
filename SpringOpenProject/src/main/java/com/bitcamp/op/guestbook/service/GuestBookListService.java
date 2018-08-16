package com.bitcamp.op.guestbook.service;

import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.guestbook.dao.GuestBookDaoInterface;
import com.bitcamp.op.guestbook.model.GuestBook;

public class GuestBookListService {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private GuestBookDaoInterface guestBookDao;
	
	//한 페이지에 보여줄 메시지의 수
	private static final int MESSAGE_COUNT_PER_PAGE = 3;

	public List<GuestBook> getGuestBookList(int pagenum) {
		
		//게시물의 리스트
		List<GuestBook> guestbookList = null;
		
		guestbookList = guestBookDao.getGuestBookList();
		
		if(guestbookList.size() == 0) {
			return Collections.emptyList();
		}else {
			return guestbookList;
		}
		
	}
}
