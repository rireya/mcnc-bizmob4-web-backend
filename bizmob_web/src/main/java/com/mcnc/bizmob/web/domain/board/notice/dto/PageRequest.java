package com.mcnc.bizmob.web.domain.board.notice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
	@Schema(description = "페이지 번호", defaultValue = "1")
	private int pageNo;
	@Schema(description = "한 페이지 당 로우 개수", defaultValue = "10")
	private int pageSize;
}
