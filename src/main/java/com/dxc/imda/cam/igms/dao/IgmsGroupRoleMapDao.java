package com.dxc.imda.cam.igms.dao;

import java.util.List;

import com.dxc.imda.cam.igms.model.GroupRoleMap;


public interface IgmsGroupRoleMapDao {

	/** List **/

	public List<GroupRoleMap> loadAllGroupRoleMap();

	public List<GroupRoleMap> findByGroupUid(Long groupUid);

}
