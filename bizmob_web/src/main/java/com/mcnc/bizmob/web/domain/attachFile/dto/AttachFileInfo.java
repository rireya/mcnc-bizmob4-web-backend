package com.mcnc.bizmob.web.domain.attachFile.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AttachFileInfo {
	private String attachId;		// 파일 ID
	private String fileName;		// 사용자가 등록한 첨부한 파일의 이름
	private String filePath;		// 파일 경로
	private int orderNo;			// 파일 저장 순서
	private Long fileSize;
	private LocalDateTime createDate;
	
	private String docId;			// 첨부파일이 저장된 게시글 id

	
	public AttachFileDto toNewDto() {
		return AttachFileDto.builder()
				.fileName(fileName)
				.fileSize(fileSize)
				.orderNo(orderNo)
				.build();
	}
}
