package com.bitcamp.op.guestbook.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.guestbook.dao.GuestBookDaoInterface;
import com.bitcamp.op.guestbook.model.GuestBook;

public class GuestBookWriteService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private GuestBookDaoInterface guestBookDao;
	
	public int write(GuestBook guestbook) {

		guestBookDao = sqlSessionTemplate.getMapper(GuestBookDaoInterface.class);
		
		int result = 0;
		
		try {
			
			result = guestBookDao.writeGuestBook(guestbook);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
