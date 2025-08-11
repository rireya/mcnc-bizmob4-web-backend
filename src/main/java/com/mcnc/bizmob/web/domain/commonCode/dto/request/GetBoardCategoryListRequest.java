package com.mcnc.bizmob.web.domain.commonCode.dto.request;

import javax.validation.constraints.NotNull;

import com.mcnc.bizmob.web.domain.commonCode.enums.BoardType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBoardCategoryListRequest {
	@NotNull(message = "게시판 타입을 입력해주세요")
	@Schema(description = "게시판 타입", nullable = false)
	private BoardType boardType;
}
