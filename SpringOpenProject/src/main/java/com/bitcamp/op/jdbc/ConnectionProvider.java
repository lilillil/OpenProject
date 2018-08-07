package com.bitcamp.op.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	//서비스 클라스에서 호출한 쪽에서 예외 처리하도록 던짐
	public static Connection getConnection() throws SQLException {
		//pool 드리아버 로드
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:guestbook");
	}
}
