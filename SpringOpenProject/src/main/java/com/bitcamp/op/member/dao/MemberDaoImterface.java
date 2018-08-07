package com.bitcamp.op.member.dao;

import com.bitcamp.op.member.model.Member;

public interface MemberDaoImterface {

	//mapper : 패키지이름 + 클래스 이름으로 찾음
	//id : 메서드 이름 으로 찾음
	
	public Member selectById(String id);
	public int insertMember(Member member);
}
