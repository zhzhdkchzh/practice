package com.example.test.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {
//세션생성
	public static void setAttribute(String name, Object object) throws Exception {
        RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
    }
//세션삭제
	public static void removeAttribute(String name) throws Exception {
		RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
	}
//세션가져오기	
	public static Object getSessionAttribute(final String name) throws Exception {
		return RequestContextHolder.getRequestAttributes().getAttribute(name,  RequestAttributes.SCOPE_SESSION);
	}
}
