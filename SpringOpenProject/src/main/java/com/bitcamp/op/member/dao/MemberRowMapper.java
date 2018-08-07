package com.bitcamp.op.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bitcamp.op.member.model.Member;

public class MemberRowMapper implements RowMapper<Member>{
	
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Member member = new Member();
		member.setMidx(rs.getInt(1));
		member.setId(rs.getString(2));
		member.setPw(rs.getString(3));
		member.setPhoto(rs.getString(4));
		member.setName(rs.getString(5));
		member.setRegdate(rs.getDate(6));
		return member;
		
		
	}

}
