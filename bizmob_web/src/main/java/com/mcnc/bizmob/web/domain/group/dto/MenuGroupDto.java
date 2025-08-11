package com.mcnc.bizmob.web.domain.group.dto;

import com.mcnc.bizmob.web.global.domain.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuGroupDto extends BaseEntity{
	private String menuId;
	private String grpId;

	@Builder
	public MenuGroupDto(String menuId, String grpId) {
		this.menuId = menuId;
		this.grpId = grpId;
		setCreateBy("admin");
	}
	
}
