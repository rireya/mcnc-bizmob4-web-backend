package com.mcnc.bizmob.web.domain.group.dto.response;

import java.util.List;

import com.mcnc.bizmob.web.domain.group.dto.GroupInfo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GetGroupListResponse {
	private int totalCount;
	private List<GroupInfo> groupList;
}

