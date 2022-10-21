package com.dxc.imda.cam.igms.helper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.dxc.imda.cam.igms.model.GroupRoleMap;

@Component
public class IgmsGroupRoleMapHelper {

	public GroupRoleMap getGroupRoleMap(Map<String, Object> mapRow) {
		GroupRoleMap groupRoleMap = new GroupRoleMap();
		if (mapRow.get("GROUP_UID") != null || mapRow.get("ROLE_UID") != null){
			groupRoleMap.setUserRoleUId(((Number) mapRow.get("GROUP_UID")).longValue());
			groupRoleMap.setGroupRoleUId(((Number) mapRow.get("ROLE_UID")).longValue());
		}
		return groupRoleMap;
	}
}
