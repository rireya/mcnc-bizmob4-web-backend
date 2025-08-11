package com.mcnc.bizmob.web.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GroupInfo {
	private String groupName;
	private String groupId;
	private String groupCode;
}

