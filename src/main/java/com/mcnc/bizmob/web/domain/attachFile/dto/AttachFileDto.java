package com.mcnc.bizmob.web.domain.attachFile.dto;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mcnc.bizmob.web.domain.commonCode.enums.BoardType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachFileDto {
	private String attachId;
	private MultipartFile file;		// 파일 등록, 수정시에 사용
	
	private String fileName;		// 사용자가 등록한 첨부한 파일의 이름
	private String filePath;		// 실제 파일 경로 (상세에서 이미지 보여줄때)
	private Long fileSize;
	private String fileType;
	
	private String docId;			// 첨부파일이 저장된 게시글 id
	private BoardType boardType;	// 첨부파일이 저장된 게시글의 게시판 타입
	private String bannerType;		// 첨부파일이 저장된 배너 테이블 타입
	private int orderNo;			// 파일 저장 순서
	
	private String createBy;
	private LocalDateTime createDate;

	
	@Builder
	public AttachFileDto(MultipartFile file, String attachId, String docId, String fileName,Long fileSize, String filePath, int orderNo, BoardType boardType, String bannerType) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.orderNo = orderNo;
		this.docId	= docId;
		this.boardType = boardType;
		this.bannerType = bannerType;
		this.createBy = "admin";
	}
	
	public void setFile(MultipartFile file) {
		this.file = file;
		this.fileName = file.getOriginalFilename();
		this.fileSize = file.getSize();
		this.fileType = StringUtils.getFilenameExtension(fileName);
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
		this.fileType = StringUtils.getFilenameExtension(fileName);
	}

	public void setOciFilePath(String ociPath) {
		this.filePath = ociPath + this.filePath;
	}
	
	public AttachFileInfo of() {
		return AttachFileInfo.builder()
				.attachId(attachId)
				.fileName(fileName)
				.fileSize(fileSize)
				.filePath((filePath))
		        .docId(docId) 
				.createDate(createDate)
				.orderNo(orderNo)
				.build();
	}
}
