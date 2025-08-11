package com.mcnc.bizmob.web.domain.menu.dto.request;

import javax.validation.constraints.NotBlank;

import com.mcnc.bizmob.web.domain.menu.dto.MenuDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMenuRequest {
	@NotBlank(message = "menu ID가 유효하지 않습니다.")
	@Schema(description = "수정할 메뉴 ID")
	private String menuId;
	
	@NotBlank(message = "메뉴 명을 입력해주세요")
	@Schema(description = "메뉴 명")
	private String menuName;
	
	@Schema(description = "메뉴 URL")
	private String menuUrl;
	
	@Schema(description = "메뉴 설명")
	private String description;
	
	@Schema(description = "메뉴 활성화 여부(0: 비활성, 1:활성)", allowableValues = {"0", "1"})
	private String status;
	
	public MenuDto toDto() {
		return MenuDto.builder()
				.menuId(menuId)
				.menuName(menuName)
				.menuUrl(menuUrl)
				.description(description)
				.status(status)
				.updateBy("admin")
				.build();
	}
}
