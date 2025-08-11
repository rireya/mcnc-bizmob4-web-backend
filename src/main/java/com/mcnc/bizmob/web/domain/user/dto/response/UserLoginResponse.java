package com.mcnc.bizmob.web.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginResponse {
	private String userId;
	private String token;
	private String status;
	private String groupName;
	private String groupCode;
	private String groupId;
	
	@Builder
	public UserLoginResponse(String userId, String token, String status, String groupName, String groupCode, String groupId) {
		this.userId = userId;
		this.token = token;
		this.status = status;
		this.groupName = groupName;
		this.groupCode = groupCode;
		this.groupId = groupId;
	}
}
