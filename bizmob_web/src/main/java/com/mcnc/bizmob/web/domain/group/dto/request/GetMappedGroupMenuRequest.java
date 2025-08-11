package com.mcnc.bizmob.web.domain.group.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMappedGroupMenuRequest {
	@NotBlank(message = "그룹 ID를 입력해주세요.")
	@Schema(description = "그룹 ID")
	private String groupId;
}
