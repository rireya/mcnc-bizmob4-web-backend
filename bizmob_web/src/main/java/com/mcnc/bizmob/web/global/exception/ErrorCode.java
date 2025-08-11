package com.mcnc.bizmob.web.global.exception;

public enum ErrorCode {
	
	// COMMON
	RESULT_NO_DATA(500, "CM001", "조회된 데이터가 없습니다.", "NO DATA"),
	INVALID_DATA(500, "CM002", "유효하지않은 데이터입니다.", "Invalid Data"),
	
	GROUP_NOT_FOUND(500, "USER001", "권한이 없습니다.", "Invalid Authorized"),
	LOGIN_ERROR(500, "USER002", "로그인 오류가 발생했습니다.", "Login Error"),
	
	DOC_REGISTER_FAIL(500, "DOC001", "게시글 작성시 오류가 발생했습니다.", "document register Failed"),
	DOC_UPDATE_FAIL(500, "DOC002", "게시글 수정시 오류가 발생했습니다.", "document update Failed"),
	DOC_DELETE_FAIL(500, "DOC002", "게시글 삭제시 오류가 발생했습니다.", "document delete Failed"),
	
	CATE_CODE_INVALID(500, "CODE001", "카테고리 코드가 유효하지 않습니다.", "code Invalid"),

	FILE_UPLOAD_FAIL(500, "FILE0001", "파일 업로드 오류가 발생했습니다.", "file upload Failed"),
	IMAGE_FILES_NULL(500, "FILE0002", "파일이 첨부되지 않았습니다.", "file not exist"),
	DUPLICATE_KEY(500,"DB0001","중복 키 에러가 발생했습니다.", "Duplicate Key Error"),
	MAX_BANNER_COUNT_EXCEEDED(500, "BANNER001", "배너의 최대 등록 개수를 초과했습니다.", "Banner Max Count Exceeded");

	

	private int status;
	private final String code;
	private final String koMessage;
	private final String enMessage;

	ErrorCode(final int status, final String code, final String koMessage, final String enMessage) {
        this.status = status;
        this.code = code;
        this.koMessage = koMessage;
        this.enMessage = enMessage;
	}

	public int getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getKoMessage() {
		return koMessage;
	}
	
	public String getEnMessage() {
		return enMessage;
	}
}
