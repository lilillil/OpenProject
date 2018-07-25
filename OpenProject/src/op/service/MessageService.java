package op.service;

import java.sql.Connection;
import java.sql.SQLException;

import op.service.InvalidMessagePassowrdException;
import op.service.MessageNotFoundException;
import op.dao.MessageDao;
import op.model.Message;
import op.service.ServiceException;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class MessageService {
	private static MessageService instance = new MessageService();

	public static MessageService getInstance() {
		return instance;
	}

	private MessageService() {
	}

	public int write(Message message) throws ServiceException {
		Connection conn = null;
		try {
			
			conn = ConnectionProvider.getConnection();
			MessageDao dao = MessageDao.getInstance();
			int resultCnt = dao.insert(conn, message);
			
			return resultCnt;
			
		} catch (SQLException e) {
			throw new ServiceException("메시지 등록 실패: " + e.getMessage(), e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	
	public void deleteMessage(int messageId, String password)
			throws ServiceException, InvalidMessagePassowrdException, MessageNotFoundException {

		Connection conn = null;
		try {

			conn = ConnectionProvider.getConnection();
			MessageDao dao = MessageDao.getInstance();

			// 1. 메시지 객체 검색

			Message message = dao.select(conn, messageId);

			// 게시물 유무 확인 : 없다면 예외 발생
			if (message == null) {
				throw new MessageNotFoundException("메시지가 없습니다:" + messageId);
			}

			// 비밀번호 존재 유무 확인 : 없다면 예외 발생
			if (!message.hasPassword()) {
				throw new InvalidMessagePassowrdException();
			}

			// 비밀번호 비교 : 틀리면 예외 발생
			if (!message.matchPassword(password)) {
				throw new InvalidMessagePassowrdException();
			}

			// 삭제 처리
			dao.delete(conn, messageId);

			// 트랜젝션 완료 : commit
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
		} catch (InvalidMessagePassowrdException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} catch (MessageNotFoundException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(false);
				} catch (SQLException e) {
				}
				JdbcUtil.close(conn);
			}
		}

	}
	
}
