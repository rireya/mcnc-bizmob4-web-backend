package com.mcnc.bizmob.web.domain.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcnc.bizmob.web.domain.group.dto.GroupDto;
import com.mcnc.bizmob.web.domain.group.mapper.GroupMapper;
import com.mcnc.bizmob.web.domain.user.dto.request.UserLoginRequest;
import com.mcnc.bizmob.web.domain.user.dto.response.UserLoginResponse;
import com.mcnc.bizmob.web.domain.user.enums.LoginResultType;
import com.mcnc.bizmob.web.domain.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	GroupMapper groupMapper;
	
	@Override
	public UserLoginResponse userLoginAndCreateJwtToken(UserLoginRequest request) {
		String userId = request.getUserId();
		String password = request.getPassword();
		
		
		return null;
	}

	@Override
	public UserLoginResponse login(UserLoginRequest request) {
		String userId = request.getUserId();
		String password = request.getPassword();

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("P_USERID", userId);
		paramMap.put("P_PWD", password);
		//paramMap.put("R_RESULT", "");
		
		String resultCode = userMapper.callAdminLogin(paramMap);
		
		//String resultCode = paramMap.get("R_RESULT").replace(" " , "");
		log.debug("R_RESULT : {}",resultCode);

		LoginResultType.isLoginSuccessful(resultCode);
		
		//GroupDto group = groupMapper.selectUserGroup(request.getUserId());
		
		return UserLoginResponse.builder()
				.userId(userId).status(resultCode)
				/*.groupCode(group.getGrpCode())
				.groupId(group.getGrpId())
				.groupName(group.getGrpName())*/
				.groupCode("12345")
				.groupId("groupId")
				.groupName("groupName")
				.build();
	}

	
}
