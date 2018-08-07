package com.bitcamp.op.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.model.Member;

public class MyBatisMemberDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Member selectById(String id) {
		
		//namespace
		String str = "com.bitcamp.op.mapper.mybatis.MemberMapper.selectById";
		
		return (Member) sqlSessionTemplate.selectOne(str, id);
	}
	
	
	public int insertMember(Member member) {
		System.out.println("mybatis insert member.................");
		String str = "com.bitcamp.op.mapper.mybatis.MemberMapper.insertMember";
		return sqlSessionTemplate.update(str, member);
	}
	
	
	
}
