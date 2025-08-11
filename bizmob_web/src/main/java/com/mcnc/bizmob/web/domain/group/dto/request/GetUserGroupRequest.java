package com.mcnc.bizmob.web.domain.group.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserGroupRequest {
	@NotBlank(message = "아이디를 입력해주세요.")
	@Schema(description = "사용자 ID")
	private String userId;
}
