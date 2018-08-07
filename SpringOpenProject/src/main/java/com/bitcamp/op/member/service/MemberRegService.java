package com.bitcamp.op.member.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bitcamp.op.member.dao.MemberDaoImterface;
import com.bitcamp.op.member.model.Member;

public class MemberRegService {

//	@Autowired
//	MemberDao memberDao;
	
//	@Autowired
//	JdbcTemplateMemberDao memberDao;
	
//	@Autowired
//	MyBatisMemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	private MemberDaoImterface memberDao;
	
	
	public int RegMember(Member member , HttpServletRequest request) throws Exception {
		
		memberDao = sqlSessionTemplate.getMapper(MemberDaoImterface.class);
		
		
		
		
			
		int result = 0;
		//Connection conn = null;
		
		//저장용 파일이름, 물리적 저장, DB저장용
		String imgName = "";
		
		//물리적인 저장
		
		// 1. 저장 경로 지정
		String uploadUri = "/uploadFile/memberPhoto";
		// 2. 시스템의 물리적인 경로 정의
		String dir = request.getSession().getServletContext().getRealPath(uploadUri);
		System.out.println("dir : "+dir);
		// 3. 사용자의 업로드 파일을 물리적으로 저장
		if(!member.getPhotoFile().isEmpty()) {
			//imgName = System.currentTimeMillis() + member.getPhotoFile().getOriginalFilename();
			imgName = member.getId() + "_" +member.getPhotoFile().getOriginalFilename();
			
			// 저장
			member.getPhotoFile().transferTo(new File(dir,imgName));
			
			// DB에 저장할 파일 이름을 SET
			member.setPhoto(imgName);
		}
		
		/*try {*/
			//conn = ConnectionProvider.getConnection();
			//result = memberDao.insertMember(conn, member);
		
		
			System.out.println("입력 전 midx : " + member.getMidx());
		
			result = memberDao.insertMember(member);
			
			System.out.println("입력 후 midx : " + member.getMidx());
			
			
		/*} finally {
			conn.close();
		}*/
		
		
		
		return result;
	}
	
	
}
