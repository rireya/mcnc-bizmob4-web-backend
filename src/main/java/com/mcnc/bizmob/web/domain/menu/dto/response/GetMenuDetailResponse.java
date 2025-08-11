package com.mcnc.bizmob.web.domain.menu.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GetMenuDetailResponse {
	private String menuId;
	private String menuName;
	private String menuUrl;
	private String description;
	private String status;

    @Schema(type = "string", format = "date-time", example = "2024-11-25T10:32:19")
	private LocalDateTime createDate;
    @Schema(type = "string", format = "date-time", example = "2024-11-25T10:32:19")
	private LocalDateTime updateDate;
	
}
