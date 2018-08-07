package com.bitcamp.op.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	//서블릿 클라스가 실행할때 자동 호출되는 메서드
	//톰캣 컨테이너가 실행될때 서블릿 실행
	//서블릿 용도
	//1.사용자 요청처리->컨트롤러
	//2. 초기화-> 톰캣이 실행 할때
	@Override
	public void init() throws ServletException {
		super.init();
		loadJDBCDriver();
		initConnectionPool();
		
	}
	// 데이터베이스의 드라이버 로드
		private void loadJDBCDriver() {
		try {
			// 사용하고자하는 데이터베이스 드라이버 로딩
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// POOL 드라이버 로드, 설정, 풀 등록
		private void initConnectionPool() {
		try {
			//AWS RDB
			String jdbcUrl = "jdbc:mysql://opendbmysql.crsv1mozx8ol.ap-northeast-2.rds.amazonaws.com:3306/openproject";
			String dbUser = "admin";
			String dbPass = "admin1234";
			
//			String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:orcl";
//			String dbUser = "SCOTT";
//			String dbPass = "tiger";
//			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
//			String dbUser = "sjpark";
//			String dbPass = "1234";
			
			// 커넥션풀이 새로운 커넥션을 생성할 때 사용할 커넥션팩토리를 생성.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl,dbUser, dbPass); 
			///////////////////////////////////////////////////////////////////////////
			
			// PoolableConnection을 생성하는 팩토리 생성.
			// DBCP는 커넥션을 보관할 때 PoolableConnection 을 사용
			// 실제 커넥션을 담고 있있으며, 커넥션 풀을 관리하는데 필요한 기능을 제공한다.
			// 커넥션을 close하면 종료하지 않고 커넥션 풀에 반환
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			//커넥션이 유효한지 여부를 검사할 때 사용하는 쿼리를 지정한다.
			poolableConnFactory.setValidationQuery("select 1");
			
			///////////////////////////////////////////////////////////////////////////
			
			
			//커넥션 풀의 설정 정보를 생성한다.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			//유휴 커넥션 검사 주기
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			//풀에 보관중인 커넥션이 유효한지 검사할지 유무 설정
			poolConfig.setTestWhileIdle(true);
			//커넥션 최소 개수
			poolConfig.setMinIdle(4);
			//커넥션 최대 개수
			poolConfig.setMaxTotal(50);
			
			///////////////////////////////////////////////////////////////////////////
			
			//커넥션 풀을 생성. 생성자는 PoolabeConnectionFactory와 GenericObjectPoolConfig를 사용
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			//PoolabeConnectionFactory에도 커넥션 풀을 연결
			poolableConnFactory.setPool(connectionPool);
			///////////////////////////////////////////////////////////////////////////
			
			//커넥션 풀을 제공하는 jdbc 드라이버를 등록.
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			//위에서 커넥션 풀 드라이버에 생성한 커넥션 풀을 등록한다. 이름은 chap14 이다.
			driver.registerPool("guestbook", connectionPool);
			
			System.out.println("guestbook 이름으로 컨넥션 풀 등록 성공");
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	

}
