package com.mcnc.bizmob.web.global.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageConvertWrapper extends HttpServletRequestWrapper {
	private final String APP_FORM_URLENCODED = "application/x-www-form-urlencoded";
	private final String APP_JSON = "application/json";
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private String contentType;
	private String requestParam;
	private String uri;
	private String convertBodyJson;
	
	public MessageConvertWrapper(HttpServletRequest httpRequest) throws IOException {
		super(httpRequest);
		init();
	}

	public boolean isForm() {
		if(contentType == null) {
			contentType = "application/json";
		}
		return APP_FORM_URLENCODED.contains(contentType);
	}
	
	public boolean isFormAndJson() {
		if(contentType == null) {
			contentType = "application/json";
		}
		return APP_FORM_URLENCODED.contains(contentType) || APP_JSON.contains(contentType);
	}
	
	private void init() throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) this.getRequest();
		
		contentType = httpRequest.getContentType();
		requestParam = httpRequest.getParameter("message");
		uri = httpRequest.getRequestURI();
	}
	
	public void convert() throws IOException {
		if(contentType != null && APP_FORM_URLENCODED.contains(contentType)) {
			// 원본 본문(JSON 형식)을 파싱하고 "body" 부분만 추출
	        JsonNode rootNode = objectMapper.readTree(requestParam);
	        JsonNode headerNode = rootNode.get("header");
	        log.debug(">>> bizmob client trcode = {} ", headerNode.get("trcode").asText());
	        JsonNode bodyNode = rootNode.path("body");
			
	        // bodyNode를 JSON 문자열로 변환
	        convertBodyJson = objectMapper.writeValueAsString(bodyNode);
		}
	}
	
	@Override
    public String getContentType() {
        return APP_JSON;
    }
	
	@Override
    public String getHeader(String name) {
        if ("Content-Type".equalsIgnoreCase(name)) {
            return APP_JSON;
        }
        return super.getHeader(name);
    }
	
	@Override
    public Enumeration<String> getHeaders(String name) {
        if ("Content-Type".equalsIgnoreCase(name)) {
            return Collections.enumeration(Collections.singleton(APP_JSON));
        }
        return super.getHeaders(name);
    }
	
	@Override
    public ServletInputStream getInputStream() throws IOException {
		if (contentType.contains(APP_JSON)) {
			StringBuilder jsonBody = new StringBuilder();
	        byte[] buffer = new byte[1024];
	        int bytesRead;
			try (ServletInputStream inputStream = super.getInputStream()) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    jsonBody.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
                }
            }
			log.debug(">>> Received JSON body: {}", jsonBody.toString()); // JSON 본문 로그 출력
	        
			String convertBodyJson = jsonBody.toString();
			JsonNode rootNode = objectMapper.readTree(jsonBody.toString());
			if(rootNode.has("body")) {
				JsonNode bodyNode = rootNode.path("body");
		        convertBodyJson = objectMapper.writeValueAsString(bodyNode);
			}
			
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(convertBodyJson.getBytes());
		    return new MessageConvertServletInputStream(byteArrayInputStream);
		}else {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(convertBodyJson.getBytes(StandardCharsets.UTF_8));
			return new MessageConvertServletInputStream(byteArrayInputStream);
		}
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(convertBodyJson.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
    }
}
