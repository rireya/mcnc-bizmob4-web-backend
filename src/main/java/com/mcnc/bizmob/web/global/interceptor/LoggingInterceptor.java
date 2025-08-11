package com.mcnc.bizmob.web.global.interceptor;


import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @className LoggingInterceptor
 * @Description 서블릿에서 처리되는 요청, 응답 값에 대한 로깅을 남기는 클래스.
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	    if (!(handler instanceof HandlerMethod)) return;

	    ContentCachingRequestWrapper cachingRequest = null;
	    ContentCachingResponseWrapper cachingResponse = null;

	    if (request instanceof ContentCachingRequestWrapper) {
	        cachingRequest = (ContentCachingRequestWrapper) request;
	    }
	    if (response instanceof ContentCachingResponseWrapper) {
	        cachingResponse = (ContentCachingResponseWrapper) response;
	    }

	    HandlerMethod handlerMethod = (HandlerMethod) handler;
	    String requestMethod = request.getMethod();
	    String requestURI = request.getRequestURI();
	    StringBuffer logMessage = new StringBuffer("\n");

	    if (!"/view/welcome".equalsIgnoreCase(requestURI)) {
	        logMessage.append("┌──────────────────────────────────────────────────────────────\n");
	        logMessage.append("│ Request URI : " + requestURI + "\n");
	        logMessage.append("│ Request Method : " + requestMethod + "\n");
	        logMessage.append("│ Class Method : " + handlerMethod.getMethod().getName() + "\n");

	        if (request.getQueryString() != null) {
	            logMessage.append("│ Request Body : " + URLDecoder.decode(request.getQueryString(), "UTF-8") + "\n");
	        }

	        // Json 형식에 대한 Request 데이터 추출
	        if (cachingRequest != null && cachingRequest.getContentType() != null
	                && cachingRequest.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
	            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0) {
	                logMessage.append("│ Request Body : " + objectMapper.readTree(cachingRequest.getContentAsByteArray()) + " \n");
	            }
	        }

	        // Json 형식에 대한 Response 데이터 추출
	        if (cachingResponse != null && cachingResponse.getContentType() != null
	                && cachingResponse.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
	            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
	                logMessage.append("│ Response Body : " + objectMapper.readTree(cachingResponse.getContentAsByteArray()) + " \n");
	            }
	        }
	        logMessage.append("└──────────────────────────────────────────────────────────────");

	        // 로그 출력
	        logger.info(logMessage.toString());
	    }
	}
}
