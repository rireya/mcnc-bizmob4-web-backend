package com.mcnc.bizmob.web.domain.commonCode.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryInfo {
	private String codeId;
	private String codeName;
	private String codeType;
	List<CategoryInfo> subCategoryList = new ArrayList<>();
	
	public void setSubCategoryList(List<CategoryInfo> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
}
