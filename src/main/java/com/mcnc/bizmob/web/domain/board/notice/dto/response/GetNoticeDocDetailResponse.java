package com.mcnc.bizmob.web.domain.board.notice.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mcnc.bizmob.web.domain.attachFile.dto.AttachFileInfo;
import com.mcnc.bizmob.web.domain.board.notice.dto.NoticeBoardDto;
import com.mcnc.bizmob.web.domain.board.notice.enums.FlagType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNoticeDocDetailResponse {
	private String docId;

	private String title;					// 제목
	private String contents;
	
	private String parentCategoryId;		// 상위 카테고리 ID
	private String parentCategoryName;		// 상위 카테고리 명
	private String subCategoryId;			// 하위 카테고리 ID
	private String subCategoryName;			// 하위 카테고리 명
	
	private FlagType displayFlag;			// 노출 여부
	private FlagType pinFlag;				// 상단 고정 여부

	private FlagType pubAlwaysFlag;			// 상시 여부

    @Schema(type = "string", format = "date-time", example = "2024-11-25T10:32:19")
	private LocalDateTime pubStartDate;		// 게시 시작 일자

    @Schema(type = "string", format = "date-time", example = "2024-11-25T10:32:19")
	private LocalDateTime pubEndDate;		// 게시 종료 일자
	
	private List<AttachFileInfo> imageFiles = new ArrayList<>();
	
	@Builder
	public GetNoticeDocDetailResponse(NoticeBoardDto noticeDocDetail, List<AttachFileInfo> imageFiles) {
		this.title = noticeDocDetail.getTitle();
		this.subCategoryName = noticeDocDetail.getSubCategoryName();
		this.parentCategoryName = noticeDocDetail.getParentCategoryName();
		this.subCategoryId = noticeDocDetail.getSubCategoryId();
		this.parentCategoryId = noticeDocDetail.getParentCategoryId();

		this.docId = noticeDocDetail.getNoticeDocId();
		this.contents = noticeDocDetail.getContents();
		
		this.displayFlag = noticeDocDetail.getDisplayFlag();
		this.pinFlag = noticeDocDetail.getPinFlag();
		
		this.pubAlwaysFlag = noticeDocDetail.getPubAlwaysFlag();
		this.pubStartDate = noticeDocDetail.getPubStartDate();
		this.pubEndDate = noticeDocDetail.getPubEndDate();
		
		this.imageFiles = imageFiles;
	}
}
