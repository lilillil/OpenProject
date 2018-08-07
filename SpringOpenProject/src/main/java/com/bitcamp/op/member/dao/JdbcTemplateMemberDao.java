package com.bitcamp.op.member.dao;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bitcamp.op.member.model.Member;

//jdbcTemplate를 이용한 memberDao
public class JdbcTemplateMemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//로그인
	public Member selectById(String userId) {
		
		Member resultObj = null;
		String sql = "select * from member where id=?";
		resultObj = jdbcTemplate.queryForObject(sql, new MemberRowMapper(), userId);
		
//		또는
//		List<Member> result = jdbcTemplate.query(sql, new MemberRowMapper(), userId);
//		resultObj = result.isEmpty() ? null : result.get(0);
		
		
		return resultObj;
		
		
	}
	// 회원가입
	public int insertMember(Member member) {
		
		int resultCnt = 0;
		
		//KeyHolder : 인터페이스 / GeneratedKeyHolder :상속하고 있는 클래스
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql  = "insert into member (midx, id,  pw, photo, name ) values (member_seq.nextval, ?,?,?,?) ";
		//resultCnt = jdbcTemplate.update(sql, member.getId(), member.getPw(), member.getPhoto(), member.getName());

		resultCnt = jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = null;
				
				pstmt = con.prepareStatement(sql, new String[] {"midx"});
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPw());
				pstmt.setString(3, member.getPhoto());
				pstmt.setString(4, member.getName());
				
				return pstmt;
			}
		}, keyHolder);
		
		Number keyValue = keyHolder.getKey();
		
		member.setMidx(keyValue.intValue());
		
		return resultCnt;
		
		
	}

	
	//멤버 카운트
	public int selectMemberCount() {
		
		int resultCnt = 0;
		String sql = "select count(*) from member";
		resultCnt = jdbcTemplate.queryForObject(sql, Integer.class);
				
		return resultCnt;
	}
	
	//멤버 리스트
	public List<Member> selectMemberList(int firstRow, int endRow) {
		List<Member> resultObj = null;
		//oracle
		//String sql = "select midx, id, pw, photo, name, regdate from (select ROW_NUMBER() OVER (order by midx) rn, member.* from member order by midx ) where rn between ? and ?";
		
		//AWS Mysql
		String sql = "select midx, id, pw, photo, name, regdate from member limit ?,?";
		
		System.out.println(sql);
		System.out.println(firstRow);
		System.out.println(endRow);
		
		resultObj = jdbcTemplate.query(sql, new MemberRowMapper(), firstRow, endRow);
		
		
		
		return resultObj;
	}

	
}
