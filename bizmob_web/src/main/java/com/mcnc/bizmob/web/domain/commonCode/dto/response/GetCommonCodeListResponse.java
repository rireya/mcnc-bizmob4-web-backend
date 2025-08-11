package com.mcnc.bizmob.web.domain.commonCode.dto.response;

import java.util.List;

import com.mcnc.bizmob.web.domain.commonCode.dto.CommonCodeDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetCommonCodeListResponse {
	private List<CommonCodeDto> commonCodeList;
	
	@Builder
	public GetCommonCodeListResponse(List<CommonCodeDto> commonCodeList) {
		this.commonCodeList = commonCodeList;
	}
}
