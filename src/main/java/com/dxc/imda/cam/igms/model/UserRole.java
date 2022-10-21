package com.dxc.imda.cam.igms.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/*
 * Main User Roles / GROUP
 */
/** SYS_GROUPS Table **/
public class UserRole {

	private Long id;
	private String roleName;
	private String roleDesc;
	private String status;
	private Long createdBy;
	private Date createdDate;
	private Long lastUpdBy;
	private Date lastUpdDate;
	private List<GroupUserMap> groupUserMapList;
	private List<GroupRoleMap> groupRoleMapList;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getLastUpdBy() {
		return lastUpdBy;
	}
	public void setLastUpdBy(Long lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}
	public Date getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public List<GroupUserMap> getGroupUserMapList() {
		return groupUserMapList;
	}
	public void setGroupUserMapList(List<GroupUserMap> groupUserMapList) {
		this.groupUserMapList = groupUserMapList;
	}
	public List<GroupRoleMap> getGroupRoleMapList() {
		return groupRoleMapList;
	}
	public void setGroupRoleMapList(List<GroupRoleMap> groupRoleMapList) {
		this.groupRoleMapList = groupRoleMapList;
	}

	public UserRole() {

	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", status=" + status
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastUpdBy=" + lastUpdBy
				+ ", lastUpdDate=" + lastUpdDate + "]";
	}
}
