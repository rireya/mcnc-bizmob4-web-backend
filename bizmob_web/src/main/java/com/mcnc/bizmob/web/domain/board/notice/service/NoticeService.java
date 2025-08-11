package com.mcnc.bizmob.web.domain.board.notice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mcnc.bizmob.web.domain.board.notice.dto.request.DeleteNoticeDocListRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.request.DeleteNoticeDocRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.request.GetNoticeBoardDocListRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.request.GetNoticeDocDetailRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.request.RegisterNoticeDocRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.request.UpdateDisplayFlagNoticeDocListRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.request.UpdateNoticeDocRequest;
import com.mcnc.bizmob.web.domain.board.notice.dto.response.GetNoticeBoardDocListResponse;
import com.mcnc.bizmob.web.domain.board.notice.dto.response.GetNoticeDocDetailResponse;
import com.mcnc.bizmob.web.domain.board.notice.dto.response.RegisterNoticeDocResponse;
import com.mcnc.bizmob.web.domain.board.notice.dto.response.UpdateNoticeDocResponse;

public interface NoticeService {
	public GetNoticeBoardDocListResponse getNoticeBoardDocList(GetNoticeBoardDocListRequest request);
	public GetNoticeDocDetailResponse getNoticeDocDetail(GetNoticeDocDetailRequest request);

	public RegisterNoticeDocResponse registerNoticeDoc(RegisterNoticeDocRequest request, List<MultipartFile> imageFiles);
	public UpdateNoticeDocResponse updateNoticeDoc(UpdateNoticeDocRequest request, List<MultipartFile> imageFiles);
	public void deleteNoticeDoc(DeleteNoticeDocRequest request);
	public void deleteNoticeDocList(DeleteNoticeDocListRequest request);
	
	public void setDisplayFlagNoticeDocList(UpdateDisplayFlagNoticeDocListRequest request);
}
	