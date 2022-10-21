package com.dxc.imda.cam.igms.helper;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dxc.imda.cam.igms.model.UserRole;

@Component
public class IgmsUserRoleHelper {

	public UserRole getUserRole(Map<String, Object> mapRow) {
		UserRole userRole = new UserRole();
		if (mapRow.get("GROUP_UID") != null) {
			userRole.setId(((Number) mapRow.get("GROUP_UID")).longValue());
			userRole.setRoleName((String) mapRow.get("GROUP_NAME"));
			userRole.setRoleDesc((String) mapRow.get("GROUP_DESCRIPTION"));
			userRole.setCreatedBy(((Number) mapRow.get("CREATED_BY")).longValue());
			userRole.setCreatedDate((Date) mapRow.get("CREATION_DATE_TIME"));
			userRole.setLastUpdBy(((Number) mapRow.get("LAST_UPDATED_BY")).longValue());
			userRole.setLastUpdDate((Date) mapRow.get("LAST_UPDATED_DATE_TIME"));
		}
		return userRole;
	}
}
