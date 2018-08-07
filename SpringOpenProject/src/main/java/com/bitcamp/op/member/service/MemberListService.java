package com.bitcamp.op.member.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.model.Member;

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
	
	public int pageCount() {
		
		int pageTotalCount = 0;
		
		if (memberTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = memberTotalCount / MESSAGE_COUNT_PER_PAGE;
			if (memberTotalCount % MESSAGE_COUNT_PER_PAGE > 0) {
				pageTotalCount++;
			}

		}
		
		
		return pageTotalCount;
	}
	
}
