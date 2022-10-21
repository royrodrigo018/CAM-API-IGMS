package com.dxc.imda.cam.igms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.igms.dao.IgmsGroupUserMapDao;
import com.dxc.imda.cam.igms.model.GroupUserMap;
import com.dxc.imda.cam.igms.service.IgmsGroupUserMapService;

@Service
public class IgmsGroupUserMapServiceImpl implements IgmsGroupUserMapService{
	
	@Autowired
	private IgmsGroupUserMapDao igmsGroupUserMapDao;
	
	@Override
	public List<GroupUserMap> loadAllGroupUserMapProfile() {
		return igmsGroupUserMapDao.loadAllGroupUserMapProfile();
	}

	@Override
	public List<GroupUserMap> loadAllGroupUserMapRole() {
		return igmsGroupUserMapDao.loadAllGroupUserMapRole();
	}

	@Override
	public List<GroupUserMap> findByGroupUid(Long groupUid) {
		return igmsGroupUserMapDao.findByGroupUid(groupUid);
	}

	@Override
	public List<GroupUserMap> findByUserProfileUid(Long userProfileUid) {
		return igmsGroupUserMapDao.findByUserProfileUid(userProfileUid);
	}
}
