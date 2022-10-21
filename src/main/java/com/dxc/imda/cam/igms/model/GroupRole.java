package com.dxc.imda.cam.igms.model;

import java.util.Date;
import java.util.List;

/*
 * Main User Roles / GROUP
 */
/** SYS_ROLES Table **/
public class GroupRole {

	private Long id;
	private String groupRoleName;
	private String groupRoleDesc;
	private String status;
	private Long createdBy;
	private Date createdDate;
	private Long lastUpdBy;
	private Date lastUpdDate;

	private List<GroupRoleMap> groupRoleMapList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupRoleName() {
		return groupRoleName;
	}

	public void setGroupRoleName(String groupRoleName) {
		this.groupRoleName = groupRoleName;
	}

	public String getGroupRoleDesc() {
		return groupRoleDesc;
	}

	public void setGroupRoleDesc(String groupRoleDesc) {
		this.groupRoleDesc = groupRoleDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(long lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public List<GroupRoleMap> getGroupRoleMapList() {
		return groupRoleMapList;
	}

	public void setGroupRoleMapList(List<GroupRoleMap> groupRoleMapList) {
		this.groupRoleMapList = groupRoleMapList;
	}

	@Override
	public String toString() {
		return "GroupRole [id=" + id + ", groupRoleName=" + groupRoleName + ", groupRoleDesc=" + groupRoleDesc
				+ ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastUpdBy="
				+ lastUpdBy + ", lastUpdDate=" + lastUpdDate + "]";
	}
}
