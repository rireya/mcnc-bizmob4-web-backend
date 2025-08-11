package com.mcnc.bizmob.web.domain.group.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mcnc.bizmob.web.domain.group.dto.GroupDto;
import com.mcnc.bizmob.web.domain.group.dto.MenuGroupDto;
import com.mcnc.bizmob.web.global.dto.SearchDto;

@Mapper
public interface GroupMapper {
	public GroupDto selectUserGroup(String userId);
	public List<GroupDto> selectGroupList(SearchDto searchDto);
	public List<String> selectMappedGroupByMenu(String groupId);
	public int insertMappedMenuByGroupId(MenuGroupDto menuGroupDto);
	public int selectGroupCount();
	public void deleteMissingMenus(Map<String, Object> dto);
}
