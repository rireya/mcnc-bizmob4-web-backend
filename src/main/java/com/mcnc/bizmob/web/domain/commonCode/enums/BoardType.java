package com.mcnc.bizmob.web.domain.commonCode.enums;

public enum BoardType {
	/**
	 * 공지사항 게시판
	 */
	NOTICE,
	
	/**
	 * FAQ 게시판
	 */
    FAQ,
    
	/**
	 * 하이패스 인포 게시판
	 */
    HIPASSINFO,
    
	/**
	 * 이벤트 게시판
	 */
    EVENTBOARD;
    
	public static boolean isValid(String value) {
        for (BoardType type : BoardType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}