package com.mcnc.bizmob.web.domain.board.notice.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNoticeDocDetailRequest {
	@Schema(description = "공지사항 게시글 ID")
	@NotBlank(message = "게시글 ID를 입력해주세요")
	private String noticeDocId; 
}
