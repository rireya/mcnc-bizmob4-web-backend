package com.mcnc.bizmob.web.domain.group.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GetUserGroupResponse {
	private String groupName;
	private String groupId;
	private String groupCode;
}

