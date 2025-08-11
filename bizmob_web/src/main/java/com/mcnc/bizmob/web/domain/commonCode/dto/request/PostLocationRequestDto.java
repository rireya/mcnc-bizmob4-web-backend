package com.mcnc.bizmob.web.domain.commonCode.dto.request;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLocationRequestDto {
	
	@NotNull(message = "POST LOCATION")
	@Schema(description = "게시글 목록 위치 공통 조회", nullable = false, defaultValue = "POST LOCATION")
	private String postLocation;
	
	
}
