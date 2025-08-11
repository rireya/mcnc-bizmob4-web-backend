package com.mcnc.bizmob.web.domain.menu.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteMenuRequest {
	@NotBlank(message = "menu ID가 유효하지 않습니다.")
	@Schema(description = "삭제할 메뉴 ID")
	private String menuId;
}
