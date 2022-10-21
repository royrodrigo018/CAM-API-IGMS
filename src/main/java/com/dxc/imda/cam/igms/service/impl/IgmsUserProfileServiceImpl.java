package com.dxc.imda.cam.igms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.model.UserInfo;
import com.dxc.imda.cam.igms.dao.IgmsGroupUserMapDao;
import com.dxc.imda.cam.igms.dao.IgmsUserProfileDao;
import com.dxc.imda.cam.igms.helper.IgmsUserProfileHelper;
import com.dxc.imda.cam.igms.mapper.IgmsUserInfoMapper;
import com.dxc.imda.cam.igms.model.GroupUserMap;
import com.dxc.imda.cam.igms.model.UserProfile;
import com.dxc.imda.cam.igms.service.IgmsUserProfileService;

@Service
public class IgmsUserProfileServiceImpl implements IgmsUserProfileService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IgmsUserProfileDao igmsUserProfileDao;

	@Autowired
	private IgmsGroupUserMapDao igmsGroupUserMapDao;
	
	@Autowired
	private IgmsUserProfileHelper igmsUserProfileHelper;

	@Autowired
	private IgmsUserInfoMapper igmsUserInfoMapper;

	@Override
	public UserInfo findByUserId(String userId) {
		UserInfo userInfo = new UserInfo();
		try {
			UserProfile userProfile = igmsUserProfileDao.findByUserId(userId);
			logger.info("findByUserId userProfile: " + userProfile);
			if (userProfile != null) {
				List<GroupUserMap> groupUserMapList = igmsGroupUserMapDao.findByUserProfileUid(userProfile.getId());
				userProfile.setGroupUserMapList(groupUserMapList);
				userInfo = getUserInfo(userProfile);
			}
		} catch (Exception e){
			e.printStackTrace();
			userInfo = null;
		}
		return userInfo;
	}

	@Override
	public Long countAll() {
		return igmsUserProfileDao.countAll();
	}

	@Override
	public Long countByRoleNameEquals(String roleName) {
		return igmsUserProfileDao.countByRoleNameEquals(roleName);
	}

	@Override
	public Long countByRoleNameContaining(String roleName) {
		return igmsUserProfileDao.countByRoleNameContaining(roleName);
	}

	@Override
	public Long countByRoleDescEquals(String roleDesc) {
		return igmsUserProfileDao.countByRoleDescEquals(roleDesc);
	}

	@Override
	public Long countByRoleDescContaining(String roleDesc) {
		return igmsUserProfileDao.countByRoleDescContaining(roleDesc);
	}

	@Override
	public Page<UserInfo> findAll(Pageable pageable) {
		List<UserInfo> userInfos = new ArrayList<>();
		try {
			Page<UserProfile> pagedUserProfiles = igmsUserProfileDao.findAll(pageable);
			//logger.info("findAll pagedUserProfiles: " + pagedUserProfiles);
			List<UserProfile> userProfiles = pagedUserProfiles.getContent();
			logger.info("findAll userProfiles.size(): " + userProfiles.size());
			userInfos = getUserInfos(userProfiles, pageable);
			logger.info("findAll userInfos.size(): " + userInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			userInfos = null;
		}
		return new PageImpl<UserInfo>(userInfos, pageable, userInfos.size());
	}

	@Override
	public Page<UserInfo> findByRoleNameEquals(String roleName, Pageable pageable) {
		List<UserInfo> userInfos = new ArrayList<>();
		try {
			Page<UserProfile> pagedUserProfiles = igmsUserProfileDao.findByRoleNameEquals(roleName, pageable);
			//logger.info("findByRoleNameEquals pagedUserProfiles: " + pagedUserProfiles);
			List<UserProfile> userProfiles = pagedUserProfiles.getContent();
			logger.info("findByRoleNameEquals userProfiles.size(): " + userProfiles.size());
			userInfos = getUserInfos(userProfiles, pageable);
			logger.info("findByRoleNameEquals userInfos.size(): " + userInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			userInfos = null;
		}
		return new PageImpl<UserInfo>(userInfos, pageable, userInfos.size());
	}

	@Override
	public Page<UserInfo> findByRoleNameContaining(String roleName, Pageable pageable) {
		List<UserInfo> userInfos = new ArrayList<>();
		try {
			Page<UserProfile> pagedUserProfiles = igmsUserProfileDao.findByRoleNameContaining(roleName, pageable);
			//logger.info("findByRoleNameContaining pagedUserProfiles: " + pagedUserProfiles);
			List<UserProfile> userProfiles = pagedUserProfiles.getContent();
			logger.info("findByRoleNameContaining userProfiles.size(): " + userProfiles.size());
			userInfos = getUserInfos(userProfiles, pageable);
			logger.info("findByRoleNameContaining userInfos.size(): " + userInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			userInfos = null;
		}
		return new PageImpl<UserInfo>(userInfos, pageable, userInfos.size());
	}

	@Override
	public Page<UserInfo> findByRoleDescEquals(String roleDesc, Pageable pageable) {
		List<UserInfo> userInfos = new ArrayList<>();
		try {
			Page<UserProfile> pagedUserProfiles = igmsUserProfileDao.findByRoleDescEquals(roleDesc, pageable);
			//logger.info("findByRoleDescEquals pagedUserProfiles: " + pagedUserProfiles);
			List<UserProfile> userProfiles = pagedUserProfiles.getContent();
			logger.info("findByRoleDescEquals userProfiles.size(): " + userProfiles.size());
			userInfos = getUserInfos(userProfiles, pageable);
			logger.info("findByRoleDescEquals userInfos.size(): " + userInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			userInfos = null;
		}
		return new PageImpl<UserInfo>(userInfos, pageable, userInfos.size());
	}

	@Override
	public Page<UserInfo> findByRoleDescContaining(String roleDesc, Pageable pageable) {
		List<UserInfo> userInfos = new ArrayList<>();
		try {
			Page<UserProfile> pagedUserProfiles = igmsUserProfileDao.findByRoleDescContaining(roleDesc, pageable);
			//logger.info("findByRoleDescContaining pagedUserProfiles: " + pagedUserProfiles);
			List<UserProfile> userProfiles = pagedUserProfiles.getContent();
			logger.info("findByRoleDescContaining userProfiles.size(): " + userProfiles.size());
			userInfos = getUserInfos(userProfiles, pageable);
			logger.info("findByRoleDescContaining userInfos.size(): " + userInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			userInfos = null;
		}
		return new PageImpl<UserInfo>(userInfos, pageable, userInfos.size());
	}

	@Override
	public UserInfo updateUser(String userId, String status) {
		UserInfo userInfo = new UserInfo();
		try {
			UserProfile userProfile = setUserProfile(userId, status);
			userProfile = igmsUserProfileDao.updateUser(userProfile);
			userInfo = getUserInfo(userProfile);
		} catch (Exception e){
			e.printStackTrace();
			userInfo = null;
		}
		return userInfo;
	}
	
	@Override
	public UserInfo updateUser(String userId, Boolean blnValue) {
		UserInfo userInfo = new UserInfo();
		try {
			UserProfile userProfile = setUserProfile(userId, blnValue);
			userProfile = igmsUserProfileDao.updateUser(userProfile);
			userInfo = getUserInfo(userProfile);
		} catch (Exception e){
			e.printStackTrace();
			userInfo = null;
		}
		return userInfo;
	}

	@Override
	public UpdateInfo removeUser(String userId, String status) {
		UpdateInfo updateInfo = new UpdateInfo();
		try {
			UserProfile userProfile = setUserProfile(userId, status);
			userProfile = igmsUserProfileDao.updateUser(userProfile);
			updateInfo = getUpdateInfo(userProfile); //TODO to check
		} catch (Exception e){
			e.printStackTrace();
			updateInfo = null;
		}
		return updateInfo;
	}

	private UserProfile setUserProfile(String userId, String status) {
		UserProfile userProfile = new UserProfile();
		try {
			userProfile = igmsUserProfileDao.findByUserId(userId);
			int userActiveFlag = 1;
			if ("InActive".equalsIgnoreCase(status)) {
				userActiveFlag = 0;
			}
			userProfile.setUserId(userId);
			userProfile.setStatus(status);
			userProfile.setUserActiveFlag(userActiveFlag);
		} catch (Exception e){
			e.printStackTrace();
			userProfile = null;
		}
		return userProfile;
	}
	
	private UserProfile setUserProfile(String userId, Boolean blnValue) {
		UserProfile userProfile = igmsUserProfileDao.findByUserId(userId);
		int userActiveFlag = 1;
		String status = null;
		if (blnValue) {
			if ("Active".equalsIgnoreCase(userProfile.getStatus())) {
				status = "Inactive";
				userActiveFlag = 0;
			}else {
				status = "Active";
				userActiveFlag = 1;
			}
			userProfile.setUserId(userId);
			userProfile.setStatus(status);
			userProfile.setUserActiveFlag(userActiveFlag);
		}		
		return userProfile;
	}


	private List<UserInfo> getUserInfos(List<UserProfile> userProfiles, Pageable pageable){
		List<UserInfo> userInfos = new ArrayList<>();
		try {
			List<GroupUserMap> groupUserMapList = new ArrayList<>();
			List<GroupUserMap> groupUserMapAllRoleList = igmsGroupUserMapDao.loadAllGroupUserMapRole();
			Map<Long, GroupUserMap> gumHashMap = new HashMap<>();
			
			String sortBy = igmsUserProfileHelper.getPageableSortBy(pageable);
			String sortOrder = igmsUserProfileHelper.getPageableSortOrder(pageable);

			for (UserProfile userProfile: userProfiles) {
				//logger.info("userProfile: " + userProfile);
				
				for(GroupUserMap tempGroupUserMap: groupUserMapAllRoleList) {
					if (userProfile.getId().equals(tempGroupUserMap.getUserProfileUId())) {
						gumHashMap.put(userProfile.getId(), tempGroupUserMap);
						//logger.info("getUserRoles gumHashMap: " + count++ +" --> "+ gumHashMap.get(userRole.getId()));
						groupUserMapList.add(gumHashMap.get(tempGroupUserMap.getUserProfileUId()));
					}
				}
				userProfile.setGroupUserMapList(groupUserMapList);
				
				//UserInfo userInfo = getUserInfo(userProfile);
				UserInfo userInfo = new UserInfo();
				if (sortBy != null) {					
					userInfo = getUserInfo(userProfile, sortBy, sortOrder);
				}else {
					userInfo = getUserInfo(userProfile);
				}				
				userInfos.add(userInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
			userInfos = null;
		}
		return userInfos;
	}

	private UserInfo getUserInfo(UserProfile userProfile){
		return igmsUserInfoMapper.convertUserToJSON(userProfile);
	}
	
	private UserInfo getUserInfo(UserProfile userProfile, String sortBy, String sortOrder) {
		return igmsUserInfoMapper.convertUserToJSON(userProfile, sortBy, sortOrder);
	}

	private UpdateInfo getUpdateInfo(UserProfile userProfile){
		return igmsUserInfoMapper.convertUpdateInfoToJSON(userProfile);
	}
}
