package com.dxc.imda.cam.igms.helper;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.dxc.imda.cam.igms.model.GroupRole;

@Component
public class IgmsGroupRoleHelper {

	public GroupRole getGroupRole(Map<String, Object> mapRow) {
		GroupRole groupRole = new GroupRole();
		if (mapRow.get("ROLE_UID") != null) {
			groupRole.setId(((Number) mapRow.get("ROLE_UID")).longValue());
			groupRole.setGroupRoleName((String) mapRow.get("ROLE_NAME"));
			groupRole.setGroupRoleDesc((String) mapRow.get("ROLE_DESCRIPTION"));			
		}
		return groupRole;
	}
}
