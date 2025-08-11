package com.mcnc.bizmob.web.global.quartz.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchedulerMapper {
	public void callSP_APP_RESET_SEQ();
}
