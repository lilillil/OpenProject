package op.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import jdbc.JdbcUtil;
import op.model.Member;
import op.model.Message;

public class MemberDao {

	//1. 생성자 은닉
	private MemberDao() {
	}
	//2. 인스턴스 생성
	private static MemberDao instance = new MemberDao();
	//3.외부에서 사용 가능
	public static  MemberDao getInstance() {
		return instance;
	}
	
	//회원 등록
	public int register(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		
		String sql = "insert into member values(member_seq.NEXTVAL, ?, ?, ?, ? , sysdate)";
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getPhoto());
			pstmt.setString(4, member.getName());
			
			return pstmt.executeUpdate();
			
		} finally {//무조건 처리
			JdbcUtil.close(pstmt);
		}

		
	}

	//아이디 중복 체크
	public int idDuplicateChk(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select id from member where id= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//아이디 있을때
				return 1;				
			}
			
		} finally {//무조건 처리
			JdbcUtil.close(pstmt);
		}
		
		return 0;
		
		
	}

	//로그인
	public Member login(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select id, pw, photo, name, regdate from member where id= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//결과값이 있을 때
				
				Member mem = new Member();
				
				mem.setId(rs.getString(1));
				mem.setPw(rs.getString(2));
				mem.setPhoto(rs.getString(3));
				mem.setName(rs.getString(4));
				mem.setRegdate(rs.getDate(5));
				
				return mem;
			}
			
		} finally {//무조건 처리
			JdbcUtil.close(pstmt);
		}
		
		return null;
	}

	//회원 목록
	public ArrayList<Member> getMemberList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Member> list = null;
		
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {//결과값이 있을 때
				
				list = new ArrayList<Member>();
				
				do {
					list.add(makeMemberFromResultSet(rs));
				} while (rs.next());
				
			}else {
				Collections.emptyList();
			}
			
			return list;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	
	private Member makeMemberFromResultSet(ResultSet rs) throws SQLException {
		Member member = new Member();
		member.setId(rs.getString("id"));
		member.setPw(rs.getString("pw"));
		member.setPhoto(rs.getString("photo"));
		member.setName(rs.getString("name"));
		member.setRegdate(rs.getDate("regdate"));
		return member;
	}
	
	
}
