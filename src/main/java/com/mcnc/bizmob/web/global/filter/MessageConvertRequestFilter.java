package com.mcnc.bizmob.web.global.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * 호출순서 : 2
 * Content-Type : application/x-www-form-urlencoded 로 호출 되는 경우 (비즈몹 클라이언트 호출 방법)
 * RequestBody의 message 데이터를 파싱하여 body 데이터를 json 형식으로 변환하여 컨트롤러에 전달
 */
@Component
public class MessageConvertRequestFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) {
		// 필터 초기화
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			MessageConvertWrapper convertWrapper = new MessageConvertWrapper((HttpServletRequest) request);
			if(convertWrapper.isFormAndJson()) {
				convertWrapper.convert();
				chain.doFilter(convertWrapper, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
