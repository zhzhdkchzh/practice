package com.example.test.util;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.model.LoginDTO;

public class SessionInterceptor implements HandlerInterceptor{	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("세션 체크");	
		LoginDTO userSession = (LoginDTO)SessionUtil.getSessionAttribute("user");	
		if(userSession == null) {	
			response.setCharacterEncoding("utf-8");			
			response.setContentType("text/html; Charset=utf-8");	
			PrintWriter printwriter = response.getWriter();		
			
			printwriter.print("<script>alert('세션이 만료되었습니다.') \ndocument.location.href='/';</script>");
			printwriter.flush();	
			printwriter.close(); 

			return false;
		}
			
		else {
			
			return true;
		}
	}

	@Override		// 컨트롤러 메소드 실행 직후에 실행
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override		//View 페이지가 렌더링 되고 난 후 에 실행
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
	}

}
