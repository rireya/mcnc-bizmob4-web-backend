package com.mcnc.bizmob.web.global.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {
	private LocalDateTime createDate;
    private String createBy;

	private LocalDateTime updateDate;
	private String updateBy;
}
