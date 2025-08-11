package com.mcnc.bizmob.web.domain.attachFile.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.mcnc.bizmob.web.domain.attachFile.dto.AttachFileDto;
import com.mcnc.bizmob.web.domain.attachFile.dto.AttachFileInfo;

@Mapper
public interface AttachFileMapper {
	public String selectNextAttachFileId();
	public List<AttachFileDto> selectAttachFileByDocId(String docId);
	public List<AttachFileDto> selectAttachFileById(List<String> fileIds);
	public int insertAttachFile(AttachFileDto attachFile);
	public int deleteAttachFile(String attachId);
	public int deleteAttachFileByDocId(String docId);
	public AttachFileDto selectSingleAttachFileByDocId(String docId);
	public AttachFileDto selectSingleAttachFileById(String fileId);
	public List<AttachFileDto> selectBannerAttachFilesByDocIds(@Param("docIds") List<String> docIds);
	public List<AttachFileDto> selectBodyAttachFileByMetaDocId(String docId);
	public List<AttachFileDto> selectBodyAttachFileByHeadDocId(String docId);



}
