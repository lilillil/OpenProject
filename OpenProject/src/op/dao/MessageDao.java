package op.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import op.model.Message;
import jdbc.JdbcUtil;

public class MessageDao {
	//Dao 클래스는 기능 클래스이다 --> 여러개의 인스턴스 생성이 불 필요
	//결론--> 싱글톤 패턴으로 처리
	
	//1. 생성자 은닉 --> 외부클래스에서 생성자 호출이 불가능 해 진다.
	private MessageDao() {
	}
	//2. 인스턴스 생성 : 1개 생성하고 공유해서 사용
	//static : 가상 메모리에 로드 되는 시점에 생성
	//private : 변수 직접 참조를 방지
	private static MessageDao instance = new MessageDao();
	
	//3.외부에서 사용 가능
	//static을 안 붙이면 인스턴스변수가 되므로 외부에서 사용 할 수가 없다
	public static MessageDao getInstance() {
		return instance;
	}
	// p.10
	public int insert(Connection conn, Message message) throws SQLException {

		PreparedStatement pstmt = null;
		
		String sql = "insert into guestbook_message values(message_id_seq.NEXTVAL, ?, ?, ?)";
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			
			return pstmt.executeUpdate();
			
		} finally {//무조건 처리
			JdbcUtil.close(pstmt);
		}
		
		
		
	}
	public int selectCount(Connection conn) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from guestbook_message";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} finally {
			JdbcUtil.close(stmt);
		}
		
		
		
		return stmt.executeUpdate(sql);
	}
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Message> messageList = null;
		String sql =  
				"select message_id, guest_name, password, message "
				+ "from ( "
				+ "  select rownum rnum, message_id, guest_name, password, message "
				+ "  from "
				+ "  ( "
				+ "    select * from guestbook_message m order by m.message_id desc "
				+ "  ) "
				+ "  where rownum <= ? "
				+ ") "
				+ " where rnum >= ?";
				
				try {
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, endRow);
					pstmt.setInt(2, firstRow);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						messageList = new ArrayList<Message>();
						do {
							messageList.add(makeMessageFromResultSet(rs));
						} while (rs.next());
						
					}else {
						Collections.emptyList();
					}
					
					return messageList;
				} finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
	}
	
	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("message_id"));
		message.setGuestName(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		return message;
	}
	
	
	
	public Message select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = null;
		String sql="select * from guestbook_message where message_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				message = makeMessageFromResultSet(rs);
			}
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return message;
		
		
		
		
		
	}
	public void delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		Message message = null;
		String sql="delete from guestbook_message where message_id =?";
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageId);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		
		
		
		
	}
}


