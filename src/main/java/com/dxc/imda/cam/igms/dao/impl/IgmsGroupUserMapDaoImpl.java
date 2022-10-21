package com.dxc.imda.cam.igms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.igms.dao.IgmsGroupUserMapDao;
import com.dxc.imda.cam.igms.helper.IgmsGroupUserMapHelper;
import com.dxc.imda.cam.igms.helper.IgmsUserProfileHelper;
import com.dxc.imda.cam.igms.helper.IgmsUserRoleHelper;
import com.dxc.imda.cam.igms.model.GroupUserMap;
import com.dxc.imda.cam.igms.model.UserProfile;
import com.dxc.imda.cam.igms.model.UserRole;

@Repository
public class IgmsGroupUserMapDaoImpl implements IgmsGroupUserMapDao{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate igmsJdbcTemplate;
	
	@Autowired
	private IgmsGroupUserMapHelper igmsGroupUserMapHelper;
	
	@Autowired
	private IgmsUserProfileHelper igmsUserProfileHelper;
	
	@Autowired
	private IgmsUserRoleHelper igmsUserRoleHelper;
	
	@Autowired
	public IgmsGroupUserMapDaoImpl(JdbcTemplate igmsJdbcTemplate) {
       this.igmsJdbcTemplate = igmsJdbcTemplate;
    }
	
	private static final String sqlQueryByUserRole = "SELECT groupUserMap.*, userRole.* "
		+ "FROM G_GROUP_USER_MAPPING groupUserMap "
		+ "LEFT JOIN SYS_GROUPS userRole ON userRole.GROUP_UID = groupUserMap.GROUP_UID ";
	
	private static final String sqlQueryByUserProfile = "SELECT groupUserMap.*, userProfile.* "
		+ "FROM G_GROUP_USER_MAPPING groupUserMap "
		+ "LEFT JOIN U_USER_PROFILE userProfile ON userProfile.USER_PROFILE_UID = groupUserMap.USER_PROFILE_UID ";
			
	private static final String whereClauseEqUserProfileUid = "WHERE groupUserMap.USER_PROFILE_UID = ? ";

	private static final String whereClauseEqGroupUid = "WHERE groupUserMap.GROUP_UID = ? ";
	
	
	/** List **/
	
	@Override
	public List<GroupUserMap> loadAllGroupUserMapProfile(){
		List<GroupUserMap> groupUserMapList = new ArrayList<>(); 
		try {
			String sql = sqlQueryByUserProfile; 
			//logger.info("loadAllGroupUserMapProfile sql: " + sql);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql);
			groupUserMapList = getGroupUserMapProfileList(results);
		} catch (Exception e){
			e.printStackTrace();
		}
        return groupUserMapList;
	}
	
	@Override
	public List<GroupUserMap> loadAllGroupUserMapRole(){
		List<GroupUserMap> groupUserMapList = new ArrayList<>(); 
		try {
			String sql = sqlQueryByUserRole; 
			//logger.info("loadAllGroupUserMapRole sql: " + sql);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql);
			//logger.info("loadAllGroupUserMap results.size(): " + results.size());
			groupUserMapList = getGroupUserMapRoleList(results);
		} catch (Exception e){
			e.printStackTrace();
		}
        return groupUserMapList;
	}
	
	/** UserRole to UserProfile **/
	@Override
	public List<GroupUserMap> findByGroupUid(Long groupUid){
		List<GroupUserMap> groupUserMapList = new ArrayList<>(); 
		try {
			String sql = sqlQueryByUserProfile + whereClauseEqGroupUid; 
			logger.info("findByGroupUid sql: " + sql);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{groupUid});
			groupUserMapList = getGroupUserMapProfileList(results);
		} catch (Exception e){
			e.printStackTrace();
		}
        return groupUserMapList;
	}
	
	/** UserProfile to UserRole **/
	@Override
	public List<GroupUserMap> findByUserProfileUid(Long userProfileUid){
		List<GroupUserMap> groupUserMapList = new ArrayList<>(); 
		try {
			String sql = sqlQueryByUserRole + whereClauseEqUserProfileUid; 
			logger.info("findByUserProfileUid sql: " + sql);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{userProfileUid});
			logger.info("findByUserProfileUid results: " + results);
			groupUserMapList = getGroupUserMapRoleList(results);
		} catch (Exception e){
			e.printStackTrace();
		}
		logger.info("findByUserProfileUid groupUserMapList: " + groupUserMapList);
        return groupUserMapList;
	}
	
	private List<GroupUserMap> getGroupUserMapProfileList(List<Map<String, Object>> results){
		List<GroupUserMap> groupUserMapList = new ArrayList<>();
		try {
	        for (Map<String, Object> mapRow: results) {
	        	GroupUserMap groupUserMap = igmsGroupUserMapHelper.getGroupUserMap(mapRow);
	        	
	        	UserProfile userProfile = igmsUserProfileHelper.getUserProfile(mapRow);   
	            
	        	groupUserMap.setUserProfile(userProfile);
	        	groupUserMapList.add(groupUserMap);
	        }
		} catch (Exception e){
			e.printStackTrace();
		}
		return groupUserMapList;
	}
	
	private List<GroupUserMap> getGroupUserMapRoleList(List<Map<String, Object>> results){
		List<GroupUserMap> groupUserMapList = new ArrayList<>(); 
		try {
	        for (Map<String, Object> mapRow: results) {
	        	GroupUserMap groupUserMap = igmsGroupUserMapHelper.getGroupUserMap(mapRow);
	        	
	        	UserRole userRole = igmsUserRoleHelper.getUserRole(mapRow);   
	            
	        	groupUserMap.setUserRole(userRole);
	        	groupUserMapList.add(groupUserMap);
	        }
		} catch (Exception e){
			e.printStackTrace();
		}
		return groupUserMapList;
	}
}
