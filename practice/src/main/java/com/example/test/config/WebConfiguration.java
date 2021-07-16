package com.example.test.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.test.util.SessionInterceptor;




@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	//controller의 메소드가 실행될때마다 실행되는 작업을 interceptor라고함
	//interceptor를 쓰는 이유는 로그인 세션체크 등이있음
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())	//interceptor생성
        .addPathPatterns("/*")		//인터셉터를 적용시킬 url설정
        .excludePathPatterns("/loginProc")	//인터셉터에서 제외시킬 url설정 로그인 전까지는 적용X
        .excludePathPatterns("/")			
		.excludePathPatterns("/signup");
	}



}
