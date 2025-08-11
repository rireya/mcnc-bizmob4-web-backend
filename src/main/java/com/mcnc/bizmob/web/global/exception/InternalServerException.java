package com.mcnc.bizmob.web.global.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerException extends CustomException {
	private static final long serialVersionUID = 1L;
	
	public InternalServerException(ErrorCode errorCode) {
		super(errorCode);
		log.error("[InternalServerException] errorCode: {}, errorMsg: {}", errorCode.getCode(), errorCode.getKoMessage());
	}
	
	public InternalServerException(ErrorCode errorCode, Throwable e) {
		super(errorCode, e);
		log.error("[InternalServerException] errorCode: {}, errorMsg: {}", errorCode.getCode(), e);
	}

	public InternalServerException(ErrorCode errorCode, String message) {
		super(errorCode, message);
		log.error("[InternalServerException] errorCode: {}, errorMsg: {}", errorCode.getCode(), message);
	}
	
	public InternalServerException(ErrorCode errorCode, String message, Throwable e) {
		super(errorCode, message, e);
		log.error("[InternalServerException] errorCode: {}, errorMsg: {}, cause : {} ", errorCode.getCode(), message, e);
	}
	
}
