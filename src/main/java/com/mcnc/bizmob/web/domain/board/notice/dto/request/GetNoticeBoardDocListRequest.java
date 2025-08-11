package com.mcnc.bizmob.web.domain.board.notice.dto.request;

import com.mcnc.bizmob.web.domain.board.notice.dto.KeywordSearch;
import com.mcnc.bizmob.web.domain.board.notice.dto.NoticeBoardSearchDto;
import com.mcnc.bizmob.web.domain.board.notice.dto.PageRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.PeriodSearch;
import com.mcnc.bizmob.web.domain.board.notice.enums.FlagType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetNoticeBoardDocListRequest extends PageRequest {
    @Schema(description = "상위 카테고리 ID, 전체인 경우 null로 전달", nullable = true)
    private String parentCategoryId;

    @Schema(description = "하위 카테고리 ID, 전체인 경우 null로 전달", nullable = true)
    private String subCategoryId;

    @Schema(description = "기간 검색 조건, 조건이 없는 경우 null로 전달", nullable = true)
    private PeriodSearch periodSearch;

    @Schema(description = "검색어 검색 조건, 조건이 없는 경우 null로 전달", nullable = true)
    private KeywordSearch keywordSearch;

    @Schema(description = "노출 여부 설정, 전체인 경우 null로 전달", nullable = true)
    private FlagType displayFlag;

    @Schema(description = "요청 유형 (모바일앱 - APP, 어드민 - null)", nullable = true)
    private String requestSource; 

    public NoticeBoardSearchDto toDto() {
        return NoticeBoardSearchDto.builder()
                .periodSearch(periodSearch)
                .keywordSearch(keywordSearch)
	            .requestSource(requestSource)
                .displayFlag(displayFlag)
                .build();
    }
}

