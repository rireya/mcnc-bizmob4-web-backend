package com.mcnc.bizmob.web.domain.board.notice.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateDisplayFlagNoticeDocListRequest {
	@NotNull(message = "게시글을 선택해주세요")
	@Schema(description = "노출/비노출 대상 게시글 목록")
	private List<DisplayFlagNoticeDocInfo> docList;
}
