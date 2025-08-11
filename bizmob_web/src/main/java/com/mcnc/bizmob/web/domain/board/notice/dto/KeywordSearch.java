package com.mcnc.bizmob.web.domain.board.notice.dto;

import com.mcnc.bizmob.web.domain.board.notice.enums.KeywordType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeywordSearch {
	private KeywordType keywordType;
	private String keyword;
}
