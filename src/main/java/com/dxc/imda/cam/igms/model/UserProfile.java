package com.dxc.imda.cam.igms.model;

import java.util.Date;
import java.util.List;

/** U_USER_PROFILE Table **/
public class UserProfile {

	private Long id;
	private String userId;
	private String userName;
	private String nric;
	private String email;
	private String status;
	private int userActiveFlag;
	private Date systemDeactivateDate;
	private Date lastAccessDate;
	private String createdBy;
	private Date createdDate;
	private String lastUpdBy;
	private Date lastUpdDate;

	private List<GroupUserMap> groupUserMapList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserActiveFlag() {
		return userActiveFlag;
	}

	public void setUserActiveFlag(int userActiveFlag) {
		this.userActiveFlag = userActiveFlag;
	}

	public Date getSystemDeactivateDate() {
		return systemDeactivateDate;
	}

	public void setSystemDeactivateDate(Date systemDeactivateDate) {
		this.systemDeactivateDate = systemDeactivateDate;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
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

	public UserProfile() {

	}

	public UserProfile(UserProfile userProfile) {
		super();
		this.id = userProfile.id;
		this.userId = userProfile.userId;
		this.userName = userProfile.userName;
		this.nric = userProfile.nric;
		this.email = userProfile.email;
		this.status = userProfile.status;
		this.userActiveFlag = userProfile.userActiveFlag;
		this.systemDeactivateDate = userProfile.systemDeactivateDate;
		this.lastAccessDate = userProfile.lastAccessDate;
		this.createdBy = userProfile.createdBy;
		this.createdDate = userProfile.createdDate;
		this.lastUpdBy = userProfile.lastUpdBy;
		this.lastUpdDate = userProfile.lastUpdDate;
		this.groupUserMapList = userProfile.groupUserMapList;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", userId=" + userId + ", userName=" + userName + ", status=" + status
				+ ", userActiveFlag=" + userActiveFlag + "]";
	}
}
