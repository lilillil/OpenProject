package com.bitcamp.op.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{

	//전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println(request.getContextPath() + "........인터셉터 진입");
		HttpSession session = request.getSession(false);
		
		if( session != null ) {
			if(session.getAttribute("loginInfo") != null){
				System.out.println("check.....................true");
				return true;
			}
		}
		System.out.println("check.....................false");
		response.sendRedirect(request.getContextPath() + "/login");
		
		return false;
		
		
	}
	
	

}
