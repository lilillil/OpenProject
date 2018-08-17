package com.bitcamp.op.member.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.model.Member;
import com.bitcamp.op.paging.model.Paging;

public class MemberListService {

	@Autowired
	JdbcTemplateMemberDao memberDao;
	
	//한 페이지에 보여줄 멤버의 수
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	int memberTotalCount = 0;
	
	public List<Member> getMemberList(int pageNumber) {
		
		//List<Member> member = memberDao.findAllMember();
		
		//전체 멤버 수
		memberTotalCount = memberDao.selectMemberCount();
		
		//멤버의 리스트
		List<Member> memberList = null;
		int firstRow = 0;
		int endRow = 0;
		
		
		if(memberTotalCount > 0) {
			//oracle
//			firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
//			endRow = firstRow +  MESSAGE_COUNT_PER_PAGE - 1;
//			memberList = memberDao.selectMemberList(firstRow, endRow);
			
			//AWS Mysql
			firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE;
			memberList = memberDao.selectMemberList(firstRow, MESSAGE_COUNT_PER_PAGE);
			
					
		}else {
			pageNumber = 0;
			memberList = Collections.emptyList();
		}
		
		
		return memberList;
		
	}
	
	
	public Paging pageCount(int pagenum) {
		
		//현재 페이지, 총 레코드 수, 한 페이지당 보여줄 레코드 수
		Paging paing = new Paging(pagenum, memberTotalCount, MESSAGE_COUNT_PER_PAGE );
		
		
		return paing;
	}
	
}
