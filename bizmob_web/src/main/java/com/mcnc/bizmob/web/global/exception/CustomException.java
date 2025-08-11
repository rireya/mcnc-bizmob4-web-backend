package com.mcnc.bizmob.web.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	protected CustomException(ErrorCode errorCode) {
		this(errorCode, null, null);
	}
	
	 protected CustomException(ErrorCode errorCode, Throwable cause) {
	  	this(errorCode, null, cause);
	}

	protected CustomException(ErrorCode errorCode, String message) {
		this(errorCode, message, null);
		this.errorCode = errorCode;
	}
	
    protected CustomException(ErrorCode errorCode, String message, Throwable cause) {
        super(message != null ? message : errorCode.getKoMessage(), cause);
        this.errorCode = errorCode;
    }

}
