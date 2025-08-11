package com.mcnc.bizmob.web.domain.board.notice.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNoticeBoardDocListResponse {
	private int totalCnt;
	private List<GetNoticeBoardDocResponse> noticeBoardDocs;
	
	@Builder
	public GetNoticeBoardDocListResponse(List<GetNoticeBoardDocResponse> noticeBoardDocs, int totalCnt) {
		this.noticeBoardDocs = noticeBoardDocs;  
		this.totalCnt = totalCnt;
	}
}
