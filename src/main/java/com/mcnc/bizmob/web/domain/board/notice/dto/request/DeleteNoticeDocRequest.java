package com.mcnc.bizmob.web.domain.board.notice.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteNoticeDocRequest {
	@NotBlank(message = "게시물 ID를 입력해주세요")
	private String noticeDocId;
}
