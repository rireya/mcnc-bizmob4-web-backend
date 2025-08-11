package com.mcnc.bizmob.web.domain.group.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.mcnc.bizmob.web.domain.group.dto.MenuGroupDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterMappedGroupMenuRequest {
	@NotBlank(message = "그룹 ID를 입력해주세요.")
	@Schema(description = "그룹 ID")
	private String groupId;
	
	@Schema(description = "메뉴 ID")
	private List<String> menuIds;
	
	public MenuGroupDto toDto() {
		return MenuGroupDto.builder()
				.grpId(groupId)
				.build();
	}
}
