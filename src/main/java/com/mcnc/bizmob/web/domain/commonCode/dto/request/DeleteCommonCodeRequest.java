package com.mcnc.bizmob.web.domain.commonCode.dto.request;

import com.mcnc.bizmob.web.domain.commonCode.dto.CommonCodeDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteCommonCodeRequest {
	@Schema(description = "삭제하려는 코드 ID")
	private String codeId;
	
	public CommonCodeDto toDto() {
		return CommonCodeDto.builder()
				.codeId(codeId)
				.updateBy("admin")
				.build();
	}
}
