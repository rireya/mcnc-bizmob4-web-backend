package com.mcnc.bizmob.web.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcnc.bizmob.web.global.filter.LoggingFilter;
import com.mcnc.bizmob.web.global.filter.MessageConvertRequestFilter;

@Configuration
public class FilterConfig {
	
	@Autowired
	LoggingFilter loggingFilter;
	
	@Autowired
	MessageConvertRequestFilter requestMessageConvertFilter;
	
	/**
	 * 로깅 필터
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<LoggingFilter> setLogginFilter(){
		FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<LoggingFilter>();
		
		registrationBean.setFilter(loggingFilter);		// 등록할 필터 설정
		registrationBean.addUrlPatterns("/api/*");		// 필터 적용될 URL 패턴 설정
		registrationBean.setOrder(2); 					// 필터 순서 설정
		
		return registrationBean;
	}
	
	/**
	 * reqBody 데이터 변환 필터
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<MessageConvertRequestFilter> setConvertFilter(){
		FilterRegistrationBean<MessageConvertRequestFilter> registrationBean = new FilterRegistrationBean<MessageConvertRequestFilter>();
		
		registrationBean.setFilter(requestMessageConvertFilter);	// 등록할 필터 설정
		registrationBean.addUrlPatterns("/api/*");					// 필터 적용될 URL 패턴 설정
		registrationBean.setOrder(1); 								// 필터 순서 설정
		
		return registrationBean;
	}
	
}
