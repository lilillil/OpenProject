package com.bitcamp.op.guestbook.service;

import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.guestbook.dao.GuestBookDaoInterface;
import com.bitcamp.op.guestbook.model.GuestBook;
import com.bitcamp.op.paging.model.Paging;

public class GuestBookListService {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private GuestBookDaoInterface guestBookDao;
	
	//한 페이지에 보여줄 메시지의 수
	private static final int MESSAGE_COUNT_PER_PAGE = 2;
	int guestBookTotalCount = 0;
	int firstRow = 0;
	
	public List<GuestBook> getGuestBookList(int pagenum) {
		
		guestBookDao = sqlSessionTemplate.getMapper(GuestBookDaoInterface.class);
		
		//전체 방명록 수
		guestBookTotalCount = guestBookDao.guestBookTotalCnt();
		
		//게시물의 리스트
		List<GuestBook> guestbookList = null;
		
		if(guestBookTotalCount > 0) {
	
			//AWS Mysql
			firstRow = (pagenum - 1) * MESSAGE_COUNT_PER_PAGE;
			guestbookList = guestBookDao.getGuestBookList(firstRow, MESSAGE_COUNT_PER_PAGE );
					
		}else {
			pagenum = 0;
			guestbookList = Collections.emptyList();
		}
		
		return guestbookList;
		
		
	
		
	}
	
	public Paging pageCount(int pagenum) {
		
		//현재 페이지, 총 레코드 수, 한 페이지당 보여줄 레코드 수
		Paging paing = new Paging(pagenum, guestBookTotalCount, MESSAGE_COUNT_PER_PAGE );
		
		
		return paing;
	}
}
