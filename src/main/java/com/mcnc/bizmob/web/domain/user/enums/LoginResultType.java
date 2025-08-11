package com.mcnc.bizmob.web.domain.user.enums;

import java.util.Arrays;

import com.mcnc.bizmob.web.global.exception.ErrorCode;
import com.mcnc.bizmob.web.global.exception.InternalServerException;

import lombok.Getter;

/**
 * SP_ADMIN_LOGIN 프로시저 호출의 결과
 */
@Getter
public enum LoginResultType {
	PASS("로그인 성공하였습니다.", "Y"), 
	INCORRECT("비밀번호가 일치하지 않습니다.", "N"), 	
	NO_USER("사용자 정보가 존재하지 않습니다.", "E"),
	MAX_INCORRECT("비밀번호 5회 이상 오류가 발생했습니다.", "T");
	
	private String value;
	private String code;
	
	LoginResultType(String value, String code){
		this.value = value;
		this.code = code;
	}
	
	public static LoginResultType findByResultCode(String code) {
	    return Arrays.stream(LoginResultType.values())
	            .filter(type -> type.getCode().equals(code))
	            .findAny()
	            .orElseThrow(() -> new InternalServerException(ErrorCode.LOGIN_ERROR));
	}
	
	public static void isLoginSuccessful(String code) {
		LoginResultType type = findByResultCode(code);
	    if(type != LoginResultType.PASS) {
	    	throw new InternalServerException(ErrorCode.LOGIN_ERROR, type.getValue());
	    }
	}
}
