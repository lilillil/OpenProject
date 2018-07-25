package op.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import op.dao.MemberDao;
import op.model.Member;

public class MemberService {
	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	private MemberService() {
	}
	//회원가입
	public int register(Member member) throws ServiceException {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();
			int resultCnt = dao.register(conn, member);
			
			return resultCnt;
			
		} catch (SQLException e) {
			throw new ServiceException("회원 가입 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	//아이디 중복체크
	public int idDuplicateChk(Member member) throws ServiceException {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();
			int resultCnt = dao.idDuplicateChk(conn, member);
			
			return resultCnt;
			
		} catch (SQLException e) {
			throw new ServiceException("아이디 중복 체크 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	//로그인
	public Member login(Member member) throws ServiceException {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();
			Member mem = dao.login(conn, member);
			
			System.out.println("qqqq"+member.getId());
			System.out.println(mem.getId());
			
			if(member.getId().equals(mem.getId())) {
				
				if(member.getPw().equals(mem.getPw())) {
					return mem;
				} else {
					return null;
				}
				
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new ServiceException("로그인 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	//회원 목록
	public ArrayList<Member> getMemberList(){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();
			ArrayList<Member> list = dao.getMemberList(conn);
			
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
		
		
	}
	
}
