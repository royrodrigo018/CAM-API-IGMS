package com.dxc.imda.cam.igms.model;

/** G_GROUP_ROLE_MAPPING Table **/
public class GroupRoleMap {

	private Long groupRoleUId;
	private Long userRoleUId;

	private GroupRole groupRole;

	public GroupRoleMap() {

	}

	public GroupRoleMap(Long groupRoleUId, Long userRoleUId) {
		super();
		this.groupRoleUId = groupRoleUId;
		this.userRoleUId = userRoleUId;
	}

	public Long getGroupRoleUId() {
		return groupRoleUId;
	}

	public void setGroupRoleUId(Long groupRoleUId) {
		this.groupRoleUId = groupRoleUId;
	}

	public Long getUserRoleUId() {
		return userRoleUId;
	}

	public void setUserRoleUId(Long userRoleUId) {
		this.userRoleUId = userRoleUId;
	}

	public GroupRole getGroupRole() {
		return groupRole;
	}

	public void setGroupRole(GroupRole groupRole) {
		this.groupRole = groupRole;
	}

	@Override
	public String toString() {
		return "GroupRoleMap [groupRoleUId=" + groupRoleUId + ", userRoleUId=" + userRoleUId + "]";
	}
}
