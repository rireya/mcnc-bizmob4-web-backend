package com.mcnc.bizmob.web.global.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationException extends CustomException {
	private static final long serialVersionUID = 1L;
	
	public ValidationException(ErrorCode errorCode) {
		super(errorCode);
		log.error("[ValidationException] errorCode: {}, errorMsg: {}", errorCode.getCode(), errorCode.getKoMessage());
	}
	
	public ValidationException(ErrorCode errorCode, String message) {
		super(errorCode, message);
		log.error("[ValidationException] errorCode: {}, errorMsg: {}", errorCode.getCode(), message);
	}
	
}
