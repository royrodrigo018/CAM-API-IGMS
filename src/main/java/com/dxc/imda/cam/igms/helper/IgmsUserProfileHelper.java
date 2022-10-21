package com.dxc.imda.cam.igms.helper;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.util.StringUtil;
import com.dxc.imda.cam.igms.model.UserProfile;

@Component
public class IgmsUserProfileHelper {
	
	public UserProfile getUserProfile(Map<String, Object> mapRow) {		
		UserProfile userProfile = new UserProfile();
		if (mapRow.get("USER_PROFILE_UID") != null) {
			userProfile.setId(((Number) mapRow.get("USER_PROFILE_UID")).longValue());
	    	userProfile.setUserId((String) mapRow.get("LOGIN_ID"));
	    	userProfile.setUserName((String) mapRow.get("USERNAME"));
	    	userProfile.setEmail((String) mapRow.get("EMAIL"));
	    	userProfile.setNric((String) mapRow.get("NRIC"));
	    	userProfile.setStatus((String) mapRow.get("STATUS"));
	    	userProfile.setCreatedBy(String.valueOf(mapRow.get("CREATION_BY")));
	    	userProfile.setCreatedDate((Date) mapRow.get("CREATION_DATE_TIME"));
	    	userProfile.setLastUpdBy(String.valueOf(mapRow.get("LAST_UPDATED_BY")));
	    	userProfile.setLastUpdDate((Date) mapRow.get("LAST_UPDATED_DATE_TIME")); 
		}
    	return userProfile;
	}
	
	// to debug must check sql statements 
	// this is to check which getUserInfo method to use.
	public String getPageableSortBy(Pageable pageable) {
		String[] sortArray = pageable.getSort().toString().split(":");	
		String sortBy = null;
		if (!StringUtil.isBlank(sortArray[0])) {
			if ("userRole.roleDesc".equalsIgnoreCase(sortArray[0].trim())) {	
				sortBy = "userRole.roleDesc";
			}else if ("userRole.roleName".equalsIgnoreCase(sortArray[0].trim())) {	
				sortBy = "userRole.roleName";
			}
		}		
		return sortBy;		
	}
	
	public String getPageableSortOrder(Pageable pageable) {
		String[] sortArray = pageable.getSort().toString().split(":");	
		String sortOrder = null;
		if (!StringUtil.isBlank(sortArray[1])) {
			sortOrder = sortArray[1].trim();
		}		
		return sortOrder;		
	}
}
