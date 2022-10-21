package com.dxc.imda.cam.igms.helper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.dxc.imda.cam.igms.model.GroupUserMap;

@Component
public class IgmsGroupUserMapHelper {

	public GroupUserMap getGroupUserMap(Map<String, Object> mapRow) {
		GroupUserMap groupUserMap = new GroupUserMap();
		if (mapRow.get("USER_PROFILE_UID") != null || mapRow.get("GROUP_UID") != null){
			groupUserMap.setUserProfileUId(((Number) mapRow.get("USER_PROFILE_UID")).longValue());
			groupUserMap.setUserRoleUId(((Number) mapRow.get("GROUP_UID")).longValue());
		}
		return groupUserMap;
	}
}
