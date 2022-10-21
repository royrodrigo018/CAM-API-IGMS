package com.dxc.imda.cam.igms.dao;

import java.util.List;

import com.dxc.imda.cam.igms.model.GroupUserMap;

public interface IgmsGroupUserMapDao {		
	
	/** List **/
	
	public List<GroupUserMap> loadAllGroupUserMapProfile();
	
	public List<GroupUserMap> loadAllGroupUserMapRole();
	
	public List<GroupUserMap> findByGroupUid(Long groupUid);
	
	public List<GroupUserMap> findByUserProfileUid(Long userProfileUid);

}
