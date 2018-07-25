package op.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import op.dao.MessageDao;
import op.model.Message;
import op.model.MessageListView;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class GetMessageListService {
	
	private static GetMessageListService instance = new GetMessageListService();

	public static GetMessageListService getInstance() {
		return instance;
	}

	private GetMessageListService() {
	}
	
	//한 페이지에 보여줄 메시지의 수
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	
	public MessageListView getMessageList(int pageNumber) throws ServiceException{
		
		Connection conn = null;
		MessageDao dao = null;
		
		int currentPageNumber = 1;
		
		if(currentPageNumber > 0 ) {
			currentPageNumber = pageNumber;
		}
		
		try {
			conn = ConnectionProvider.getConnection();
			
			dao = MessageDao.getInstance();
			//dao = dao.getInstance();
			
			//전체 게시물의 개수
			int messageTotalCount = dao.selectCount(conn);
			
			//게시물의 리스트
			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			
			
			if(messageTotalCount > 0) {
				firstRow = (currentPageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow +  MESSAGE_COUNT_PER_PAGE - 1;
				messageList = dao.selectList(conn, firstRow, endRow);
						
						
			}else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			
			
			return new MessageListView( messageList, messageTotalCount, 
					currentPageNumber, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return null;
		
		
		
	}
}
