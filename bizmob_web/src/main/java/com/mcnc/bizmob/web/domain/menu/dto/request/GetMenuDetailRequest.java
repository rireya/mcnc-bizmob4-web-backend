package com.mcnc.bizmob.web.domain.menu.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMenuDetailRequest {
	@NotEmpty
	@NotBlank(message = "menu ID가 유효하지 않습니다.")
	private String menuId;
}
