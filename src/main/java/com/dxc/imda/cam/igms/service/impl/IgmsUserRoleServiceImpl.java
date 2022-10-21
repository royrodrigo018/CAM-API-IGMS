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

import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.igms.dao.IgmsGroupRoleMapDao;
import com.dxc.imda.cam.igms.dao.IgmsGroupUserMapDao;
import com.dxc.imda.cam.igms.dao.IgmsUserRoleDao;
import com.dxc.imda.cam.igms.mapper.IgmsGroupInfoMapper;
import com.dxc.imda.cam.igms.model.GroupRoleMap;
import com.dxc.imda.cam.igms.model.GroupUserMap;
import com.dxc.imda.cam.igms.model.UserRole;
import com.dxc.imda.cam.igms.service.IgmsUserRoleService;

@Service
public class IgmsUserRoleServiceImpl implements IgmsUserRoleService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IgmsUserRoleDao igmsUserRoleDao;

	@Autowired
	private IgmsGroupUserMapDao igmsGroupUserMapDao;

	@Autowired
	private IgmsGroupRoleMapDao igmsGroupRoleMapDao;

	@Autowired
	private IgmsGroupInfoMapper igmsGroupInfoMapper;

	@Override
	public GroupInfo findByRoleName(String roleName) {
		GroupInfo groupInfo = new GroupInfo();
		try {
			UserRole userRole = igmsUserRoleDao.findByRoleName(roleName);
			logger.info("findByRoleName userRole: " + userRole);
			List<GroupUserMap> groupUserMapList = igmsGroupUserMapDao.findByGroupUid(userRole.getId());
			logger.info("findByRoleName groupUserMapList: " + groupUserMapList);
			userRole.setGroupUserMapList(groupUserMapList);

			List<GroupRoleMap> groupRoleMapList = igmsGroupRoleMapDao.findByGroupUid(userRole.getId());
			logger.info("findByRoleName groupRoleMapList: " + groupRoleMapList);
			userRole.setGroupRoleMapList(groupRoleMapList);

			groupInfo = getGroupInfo(userRole);
		} catch (Exception e){
			e.printStackTrace();
			groupInfo = null;
		}
		return groupInfo;
	}

	@Override
	public Long countAll() {
		return igmsUserRoleDao.countAll();
	}

	@Override
	public Long countByRoleNameEquals(String roleName) {
		return igmsUserRoleDao.countByRoleNameEquals(roleName);
	}

	@Override
	public Long countByRoleNameContaining(String roleName) {
		return igmsUserRoleDao.countByRoleNameContaining(roleName);
	}

	@Override
	public Long countByRoleDescEquals(String roleDesc) {
		return igmsUserRoleDao.countByRoleDescEquals(roleDesc);
	}

	@Override
	public Long countByRoleDescContaining(String roleDesc) {
		return igmsUserRoleDao.countByRoleDescContaining(roleDesc);
	}

	@Override
	public Page<GroupInfo> findAll(Pageable pageable) {
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = igmsUserRoleDao.findAll(pageable);
			logger.info("findAll pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findAll userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findAll groupInfos.size(): " + groupInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleNameEquals(String roleName, Pageable pageable){
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = igmsUserRoleDao.findByRoleNameEquals(roleName, pageable);
			logger.info("findAll pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleNameEquals userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleNameEquals groupInfos.size(): " + groupInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleNameContaining(String roleName, Pageable pageable){
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = igmsUserRoleDao.findByRoleNameContaining(roleName, pageable);
			logger.info("findByRoleNameContaining pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleNameContaining userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleNameContaining groupInfos.size(): " + groupInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleDescEquals(String roleDesc, Pageable pageable){
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = igmsUserRoleDao.findByRoleDescEquals(roleDesc, pageable);
			logger.info("findAll pagedUserRoles: " + pagedUserRoles);

			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleDescEquals userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleDescEquals groupInfos.size(): " + groupInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleDescContaining(String roleDesc, Pageable pageable){
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = igmsUserRoleDao.findByRoleDescContaining(roleDesc, pageable);
			logger.info("findAll pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleDescContaining userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleDescContaining groupInfos.size(): " + groupInfos.size());
		} catch (Exception e){
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	private List<GroupInfo> getGroupInfos(List<UserRole> userRoles){
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			List<GroupUserMap> groupUserMapList = new ArrayList<>();
			List<GroupRoleMap> groupRoleMapList = new ArrayList<>();
			
			List<GroupUserMap> groupUserMapAllList = igmsGroupUserMapDao.loadAllGroupUserMapProfile();
			List<GroupRoleMap> groupRoleMapAllList = igmsGroupRoleMapDao.loadAllGroupRoleMap();
			
			Map<Long, GroupUserMap> gumHashMap = new HashMap<>();
			Map<Long, GroupRoleMap> grmHashMap = new HashMap<>();

			for (UserRole userRole: userRoles) {
				
				for(GroupUserMap tempGroupUserMap: groupUserMapAllList) {
					if (userRole.getId().equals(tempGroupUserMap.getUserRoleUId())) {
						gumHashMap.put(userRole.getId(), tempGroupUserMap);
						//logger.info("getUserRoles gumHashMap: " + count++ +" --> "+ gumHashMap.get(userRole.getId()));
						groupUserMapList.add(gumHashMap.get(tempGroupUserMap.getUserRoleUId()));
					}
				}
				userRole.setGroupUserMapList(groupUserMapList);
				
				for(GroupRoleMap tempGroupRoleMap: groupRoleMapAllList) {
					if (userRole.getId().equals(tempGroupRoleMap.getUserRoleUId())) {
						grmHashMap.put(userRole.getId(), tempGroupRoleMap);
						//logger.info("getUserRoles grmHashMap: " + count++ +" --> "+ grmHashMap.get(userRole.getId()));
						groupRoleMapList.add(grmHashMap.get(tempGroupRoleMap.getUserRoleUId()));
					}
				}
				userRole.setGroupRoleMapList(groupRoleMapList);
				
				GroupInfo groupInfo = getGroupInfo(userRole);
				groupInfos.add(groupInfo);
			}
		} catch (Exception e){
			e.printStackTrace();
			groupInfos = null;
		}
		return groupInfos;
	}

	private GroupInfo getGroupInfo(UserRole userRole){
		return igmsGroupInfoMapper.convertUserRoleToJSON(userRole);
	}
}
