package com.example.test.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.test.util.SessionInterceptor;




@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
        .addPathPatterns("/*")		
        .excludePathPatterns("/loginProc")	
        .excludePathPatterns("/")			
		.excludePathPatterns("/signup");
	}



}
