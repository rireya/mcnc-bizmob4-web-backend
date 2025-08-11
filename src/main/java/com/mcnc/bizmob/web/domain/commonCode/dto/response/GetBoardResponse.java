package com.mcnc.bizmob.web.domain.commonCode.dto.response;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetBoardResponse {
	private String docId;
    private String title;    
	private LocalDateTime createDate;


}
