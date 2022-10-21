package com.dxc.imda.cam.igms.model;

/** G_GROUP_USER_MAPPING Table **/
public class GroupUserMap {

	private Long userProfileUId;
	private Long userRoleUId;

	private UserRole userRole;
	private UserProfile userProfile;

	public GroupUserMap() {

	}

	public GroupUserMap(Long userProfileUId, Long userRoleUId) {
		super();
		this.userProfileUId = userProfileUId;
		this.userRoleUId = userRoleUId;
	}

	public Long getUserProfileUId() {
		return userProfileUId;
	}

	public void setUserProfileUId(Long userProfileUId) {
		this.userProfileUId = userProfileUId;
	}

	public Long getUserRoleUId() {
		return userRoleUId;
	}

	public void setUserRoleUId(Long userRoleUId) {
		this.userRoleUId = userRoleUId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "GroupUserMap [userProfileUId=" + userProfileUId + ", userRoleUId=" + userRoleUId + "]";
	}
}
