package com.mcnc.bizmob.web.domain.board.notice.dto.response;

import java.time.LocalDateTime;

import com.mcnc.bizmob.web.domain.board.notice.enums.FlagType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNoticeBoardDocResponse {
	private String docId;

	private String title;					// 제목
	private String contents;				// 내용
	
	private String parentCategoryId;		// 상위 카테고리 ID
	private String parentCategoryName;		// 상위 카테고리 명
	private String subCategoryId;			// 하위 카테고리 ID
	private String subCategoryName;			// 하위 카테고리 명
	
	private FlagType displayFlag;				// 노출 여부
	private FlagType pinFlag;					// 상단 고정 여부

	private FlagType pubAlwaysFlag;			// 상시 여부
	private LocalDateTime pubStartDate;		// 게시 시작 일자
	private LocalDateTime pubEndDate;		// 게시 종료 일자
	
	private LocalDateTime createDate;		// 등록일
	private String createBy;
	
}
