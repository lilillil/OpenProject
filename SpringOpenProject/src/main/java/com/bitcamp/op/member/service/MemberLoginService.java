package com.bitcamp.op.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.dao.JdbcTemplateMemberDao;
import com.bitcamp.op.member.dao.MemberDao;
import com.bitcamp.op.member.dao.MemberDaoImterface;
import com.bitcamp.op.member.dao.MyBatisMemberDao;
import com.bitcamp.op.member.model.Member;



public class MemberLoginService {
// 1. JDBC	
//	@Autowired
//	MemberDao memberDao ;

// 2. Spring Framework JdbcTemplate 클래스를 이용한 DAO	
//	@Autowired
//	JdbcTemplateMemberDao memberDao;
	
//	3. Spring + Mybatis 이용한 DAO
//	@Autowired
//	MyBatisMemberDao memberDao;
	
	
//	4. Spring + Mybatis : 자동 매퍼 생성 기능 이용한 DAO
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private MemberDaoImterface memberDao;
	
	
	//로그인
	public boolean login(HttpServletRequest request, String id, String pw) throws ServiceException, SQLException {
		
		memberDao = sqlSessionTemplate.getMapper(MemberDaoImterface.class);
		
		
		boolean result = false;
		HttpSession session = request.getSession(false);
		//Connection conn = null;
		

		/*try {*/
			//conn = ConnectionProvider.getConnection();

			//Member member = memberDao.selectById(conn, id);
			Member member = memberDao.selectById(id);

			if (member != null && member.isMatchPassword(pw)) {
				session.setAttribute("loginInfo", member);
				System.out.println("로그인 성공");
				result = true;
			}
		/*} finally {
			JdbcUtil.close(conn);
		}*/

		return result;
	}
	
	

	
}
