package com.mcnc.bizmob.web.domain.commonCode.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mcnc.bizmob.web.domain.commonCode.dto.response.GetCommonCodeDetailResponse;
import com.mcnc.bizmob.web.domain.commonCode.enums.DefineType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommonCodeDto {
	private String parentCodeId;
	private String parentCodeName;
	private String codeId;
	private String codeName;
	private String codeType;
	private String status;
	private DefineType defineType;
	private String codeAlias;
	
	private LocalDateTime createDate;
	private String createBy;
	private LocalDateTime updateDate;
	private String updateBy;
	List<CommonCodeDto> subCommonCodeList = new ArrayList<>();
	
	public GetCommonCodeDetailResponse toResponseDetail() {
		return GetCommonCodeDetailResponse.builder()
				.codeId(codeId)
				.codeName(codeName)
				.codeType(codeType)
				.status(status)
				.defineType(defineType)
				.codeAlias(codeAlias)
				.createDate(createDate)
				.updateDate(updateDate)
				.build();
	}
}
