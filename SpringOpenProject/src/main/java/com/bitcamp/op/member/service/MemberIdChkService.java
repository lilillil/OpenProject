package com.bitcamp.op.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.jdbc.ConnectionProvider;
import com.bitcamp.op.jdbc.JdbcUtil;
import com.bitcamp.op.member.dao.MemberDao;
import com.bitcamp.op.member.model.Member;

public class MemberIdChkService {

	@Autowired
	MemberDao memberDao;
	
	
	
	public int idDuplicateChk(String inputId) throws ServiceException {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			int resultCnt = memberDao.idDuplicateChk(conn, inputId);
			
			return resultCnt;
			
		} catch (SQLException e) {
			throw new ServiceException("아이디 중복 체크 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
}
