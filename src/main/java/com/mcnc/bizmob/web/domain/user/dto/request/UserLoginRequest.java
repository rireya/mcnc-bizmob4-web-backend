package com.mcnc.bizmob.web.domain.user.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginRequest {
	@NotBlank(message = "아이디를 입력해주세요.")
	@Schema(description = "사용자 ID")
	String userId;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Schema(description = "사용자 비밀번호")
	String password;
}
