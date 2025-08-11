package com.mcnc.bizmob.web.domain.board.notice.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteNoticeDocListRequest {
	@NotNull(message = "삭제할 게시글이 없습니다")
	private List<String> noticeDocIds;
}
