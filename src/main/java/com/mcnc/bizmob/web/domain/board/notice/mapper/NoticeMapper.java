package com.mcnc.bizmob.web.domain.board.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mcnc.bizmob.web.domain.board.notice.dto.NoticeBoardDto;
import com.mcnc.bizmob.web.domain.board.notice.dto.NoticeBoardSearchDto;

@Mapper
public interface NoticeMapper {
	public String selectNextNoticeDocId();
	public List<NoticeBoardDto> selectNoticeBoardDocList(NoticeBoardSearchDto searchDto);
	public int selectNoticeBoardDocCount(NoticeBoardSearchDto searchDto);
	
	public NoticeBoardDto selectNoticeDocDetail(String noticeDocId);

	public int insertNoticeDoc(NoticeBoardDto noticeDoc);
	public int updateNoticeDoc(NoticeBoardDto noticeDoc);
	public int deleteNoticeDoc(String noticeDocId);
	public int updateNoticeDocDisplayFlag(NoticeBoardDto noticeDoc);

}
