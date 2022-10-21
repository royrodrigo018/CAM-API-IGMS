package com.dxc.imda.cam.igms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.igms.dao.IgmsGroupRoleMapDao;
import com.dxc.imda.cam.igms.helper.IgmsGroupRoleHelper;
import com.dxc.imda.cam.igms.helper.IgmsGroupRoleMapHelper;
import com.dxc.imda.cam.igms.model.GroupRole;
import com.dxc.imda.cam.igms.model.GroupRoleMap;

@Repository
public class IgmsGroupRoleMapDaoImpl implements IgmsGroupRoleMapDao{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate igmsJdbcTemplate;

	@Autowired
	private IgmsGroupRoleMapHelper igmsGroupRoleMapHelper;

	@Autowired
	private IgmsGroupRoleHelper igmsGroupRoleHelper;

	@Autowired
	public IgmsGroupRoleMapDaoImpl(JdbcTemplate igmsJdbcTemplate) {
		this.igmsJdbcTemplate = igmsJdbcTemplate;
	}

	private static final String sqlQueryByGroupRole = "SELECT groupRoleMap.*, groupRole.* "
		+ "FROM G_GROUP_ROLES_MAPPING groupRoleMap "
		+ "LEFT JOIN SYS_ROLES groupRole ON groupRoleMap.ROLE_UID = groupRole.ROLE_UID ";

	//private static final String whereClauseEqUserGroupRoleUid = "WHERE groupRoleMap.ROLE_UID = ? ";

	private static final String whereClauseEqGroupUid = "WHERE groupRoleMap.GROUP_UID = ? ";

	@Override
	public List<GroupRoleMap> loadAllGroupRoleMap() {
		List<GroupRoleMap> groupRoleMapList = new ArrayList<>();
		try {
			String sql = sqlQueryByGroupRole;
			//logger.info("loadAllGroupRoleMap sql: " + sql);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
					sql);
			logger.info("loadAllGroupRoleMap results.size(): " + results.size());
			groupRoleMapList = getGroupRoleMapList(results);
		} catch (Exception e){
			e.printStackTrace();
		}
		return groupRoleMapList;
	}

	@Override
	public List<GroupRoleMap> findByGroupUid(Long groupUid) {
		List<GroupRoleMap> groupRoleMapList = new ArrayList<>();
		try {
			String sql = sqlQueryByGroupRole + whereClauseEqGroupUid;
			logger.info("findByGroupUid sql: " + sql);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{groupUid});
			groupRoleMapList = getGroupRoleMapList(results);
		} catch (Exception e){
			e.printStackTrace();
		}
		return groupRoleMapList;
	}

	private List<GroupRoleMap> getGroupRoleMapList(List<Map<String, Object>> results){
		List<GroupRoleMap> groupRoleMapList = new ArrayList<>();
		try {
			for (Map<String, Object> mapRow: results) {
				GroupRoleMap groupRoleMap = igmsGroupRoleMapHelper.getGroupRoleMap(mapRow);

				GroupRole groupRole = igmsGroupRoleHelper.getGroupRole(mapRow);

				groupRoleMap.setGroupRole(groupRole);
				groupRoleMapList.add(groupRoleMap);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return groupRoleMapList;
	}

	/** List **/



}
