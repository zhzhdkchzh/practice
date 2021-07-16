package com.example.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class XssFilterConfig implements WebMvcConfigurer{
	//xss 필터를 적용하기위한 configration 
	//xss 공격을 방지하기위해 필터를 적용시키는 작업을 함
	//여러가지 필터들이있지만 naver에서 배포해주는 lucy-xss-servlet-filter가 대표적임
	//필터를 적용하기위해선 pom.xml에서 dependency 추가
	//lucy-xss-servlet-filter-rule.xml 추가
	//configration 설정
	  @Bean		//빈을 알리는 어노테이션
	  public FilterRegistrationBean<XssEscapeServletFilter> getFilterRegistrationBean(){
	      FilterRegistrationBean<XssEscapeServletFilter> registrationBean = new FilterRegistrationBean<>();
	      registrationBean.setFilter(new XssEscapeServletFilter());
	      registrationBean.setOrder(1);
	      registrationBean.addUrlPatterns("/*"); //적용시킬 url 패턴설정
	      return registrationBean;
	  }
}
