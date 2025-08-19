package com.mcnc.bizmob.web.domain.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public String callAdminLogin(Map<String, String> paramMap);
}
