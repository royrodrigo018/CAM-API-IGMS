package com.dxc.imda.cam.igms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.imda.cam.common.constant.Enums.AppName;
import com.dxc.imda.cam.common.util.PageUtil;
import com.dxc.imda.cam.igms.dao.IgmsUserProfileDao;
import com.dxc.imda.cam.igms.helper.IgmsUserProfileHelper;
import com.dxc.imda.cam.igms.model.UserProfile;

@Repository
public class IgmsUserProfileDaoImpl implements IgmsUserProfileDao{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate igmsJdbcTemplate;

	@Autowired
	private IgmsUserProfileHelper igmsUserProfileHelper;

	@Autowired
	private PageUtil pageUtil;

	@Autowired
	public IgmsUserProfileDaoImpl(JdbcTemplate igmsJdbcTemplate) {
		this.igmsJdbcTemplate = igmsJdbcTemplate;
	}

	private static final String countQuery = "SELECT count(userProfile.LOGIN_ID) "
		+ "FROM U_USER_PROFILE userProfile "
		+ "LEFT JOIN G_GROUP_USER_MAPPING groupUserMap ON groupUserMap.USER_PROFILE_UID = userProfile.USER_PROFILE_UID "
		+ "LEFT JOIN SYS_GROUPS userRole ON groupUserMap.GROUP_UID = userRole.GROUP_UID ";

	//private static final String sqlQueryOne = "SELECT userProfile.* "
	//	+ "FROM U_USER_PROFILE userProfile ";

	private static final String sqlQuery = "SELECT userProfile.*, groupUserMap.*, userRole.* "
		+ "FROM U_USER_PROFILE userProfile "
		+ "LEFT JOIN G_GROUP_USER_MAPPING groupUserMap ON groupUserMap.USER_PROFILE_UID = userProfile.USER_PROFILE_UID "
		+ "LEFT JOIN SYS_GROUPS userRole ON groupUserMap.GROUP_UID = userRole.GROUP_UID ";

	private static final String updateQuery = "UPDATE U_USER_PROFILE userProfile "
		+ "SET userProfile.STATUS = ? "
		+ ", userProfile.USER_ACTIVE_FLAG = ? "
		+ ", userProfile.LAST_UPDATED_DATE_TIME = CURRENT_DATE ";
	//+ ", userProfile.LAST_UPDATED_BY = 'CAM' "; // TODO

	private static final String updateClauseSystemDeactivateDate = ", userProfile.SYSTEM_DEACTIVATE_DATE = CURRENT_DATE ";

	private static final String whereClauseEqUserId = "WHERE UPPER(userProfile.LOGIN_ID) = ? ";

	private static final String whereClauseEqRoleName = "WHERE UPPER(userRole.GROUP_NAME) = ? ";
	private static final String whereClauseEqRoleDesc = "WHERE UPPER(userRole.GROUP_DESCRIPTION) = ? "; // TODO Ignore Case

	private static final String whereClauseLikeRoleName = "WHERE UPPER(userRole.GROUP_NAME) LIKE ? ";
	private static final String whereClauseLikeRoleDesc = "WHERE UPPER(userRole.GROUP_DESCRIPTION) LIKE ? ";

	private static final String whereClauseUserActiveFlag = "WHERE userProfile.USER_ACTIVE_FLAG = 1 ";
	private static final String andClauseUserActiveFlag = "AND userProfile.USER_ACTIVE_FLAG = 1 ";

	//private static final String andClauseActiveStatus = "AND userProfile.STATUS = 'Active' ";

	private static final String andClauseGroupUidNotNull = "AND userRole.GROUP_UID IS NOT NULL AND userRole.GROUP_UID > 0 ";

	private static final String pageClause = "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";

	@Override
	public UserProfile findByUserId(String userId){
		UserProfile userProfile  = new UserProfile();
		try {
			String sql = sqlQuery + whereClauseEqUserId;
			logger.info("findByUserId sql: " + sql);
			//Map<String, Object> mapRow = igmsJdbcTemplate.queryForMap(
				//sql, new Object[]{userId.toUpperCase()});
			//userProfile = igmsUserProfileHelper.getUserProfile(mapRow);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{userId.toUpperCase()});
			userProfile = igmsUserProfileHelper.getUserProfile(results.get(0));
		} catch (Exception e){
			e.printStackTrace();
		}
		return userProfile;
	}

	/** Count **/

	@Override
	public Long countAll() {
		String sql = countQuery + whereClauseUserActiveFlag + andClauseGroupUidNotNull;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class);
	}

	@Override
	public Long countByRoleNameEquals(String roleName) {
		String sql = countQuery + whereClauseEqRoleName + andClauseUserActiveFlag + andClauseGroupUidNotNull;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{roleName.toUpperCase()});
	}

	@Override
	public Long countByRoleDescEquals(String roleDesc) {
		String sql = countQuery + whereClauseEqRoleDesc + andClauseUserActiveFlag + andClauseGroupUidNotNull;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{roleDesc.toUpperCase()});
	}

	@Override
	public Long countByRoleNameContaining(String roleName) {
		String sql = countQuery + whereClauseLikeRoleName + andClauseUserActiveFlag + andClauseGroupUidNotNull;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{"%" + roleName.toUpperCase() + "%"});
	}

	@Override
	public Long countByRoleDescContaining(String roleDesc) {
		String sql = countQuery + whereClauseLikeRoleDesc + andClauseUserActiveFlag + andClauseGroupUidNotNull;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{"%" + roleDesc.toUpperCase() + "%"});
	}

	/** List **/

	@Override
	public Page<UserProfile> findAll(Pageable pageable){
		List<UserProfile> userProfiles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseUserActiveFlag
					+ andClauseGroupUidNotNull + orderByAndDirectionClause + pageClause;
			logger.info("findAll sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
					sql, new Object[] {pageNum - 1, pageable.getPageSize()});
			logger.info("findAll results.size(): " + results.size());
			userProfiles = getUserProfiles(results);
			logger.info("findAll userProfiles.size(): " + userProfiles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserProfile>(userProfiles, pageable, userProfiles.size());
	}

	@Override
	public Page<UserProfile> findByRoleNameEquals(String roleName, Pageable pageable){
		List<UserProfile> userProfiles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseEqRoleName + andClauseUserActiveFlag
					+ andClauseGroupUidNotNull + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleNameEquals sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
					sql, new Object[]{roleName.toUpperCase(), pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleNameEquals results.size(): " + results.size());
			userProfiles = getUserProfiles(results);
			logger.info("findByRoleNameEquals userProfiles.size(): " + userProfiles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserProfile>(userProfiles, pageable, userProfiles.size());
	}

	@Override
	public Page<UserProfile> findByRoleNameContaining(String roleName, Pageable pageable){
		List<UserProfile> userProfiles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseLikeRoleName + andClauseUserActiveFlag
					+ andClauseGroupUidNotNull + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleNameContaining sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
					sql, new Object[]{"%" + roleName.toUpperCase() + "%", pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleNameContaining results.size(): " + results.size());
			userProfiles = getUserProfiles(results);
			logger.info("findByRoleNameContaining userProfiles.size(): " + userProfiles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserProfile>(userProfiles, pageable, userProfiles.size());
	}

	@Override
	public Page<UserProfile> findByRoleDescEquals(String roleDesc, Pageable pageable){
		List<UserProfile> userProfiles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseEqRoleDesc + andClauseUserActiveFlag
					+ andClauseGroupUidNotNull + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleDescEquals sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
					sql, new Object[]{roleDesc.toUpperCase(), pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleDescEquals results.size(): " + results.size());
			userProfiles = getUserProfiles(results);
			logger.info("findByRoleDescEquals userProfiles.size(): " + userProfiles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserProfile>(userProfiles, pageable, userProfiles.size());
	}

	@Override
	public Page<UserProfile> findByRoleDescContaining(String roleDesc, Pageable pageable){
		List<UserProfile> userProfiles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseLikeRoleDesc + andClauseUserActiveFlag
					+ andClauseGroupUidNotNull + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleDescContaining sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
					sql, new Object[]{"%" + roleDesc.toUpperCase() + "%", pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleDescContaining results.size(): " + results.size());
			userProfiles = getUserProfiles(results);
			logger.info("findByRoleDescContaining userProfiles.size(): " + userProfiles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserProfile>(userProfiles, pageable, userProfiles.size());
	}

	@Transactional
	@Override
	public UserProfile updateUser(UserProfile userProfile) {
		logger.info("updateUser userId: " + userProfile.getUserId());
		logger.info("updateUser status: " + userProfile.getStatus());
		String sql = null;
		if (userProfile.getStatus().equalsIgnoreCase("InActive")) {
			sql = updateQuery + updateClauseSystemDeactivateDate + whereClauseEqUserId;
		}else {
			sql = updateQuery + whereClauseEqUserId;
		}
		logger.info("updateUser sql: " + sql);
		Object[] params = {userProfile.getStatus(),
			userProfile.getUserActiveFlag(),
			userProfile.getUserId()};
		//int[] types = {Types.VARCHAR, Types.VARCHAR};
		int updCount = igmsJdbcTemplate.update(sql, params);
		//sql, params, types);
		logger.info("updateUser updCount: " + updCount);
		if (updCount > 0) {
			userProfile = findByUserId(userProfile.getUserId());
		}
		return userProfile;
	}

	private List<UserProfile> getUserProfiles(List<Map<String, Object>> results){
		List<UserProfile> userProfiles = new ArrayList<>();
		try {
			for (Map<String, Object> mapRow: results) {
				UserProfile userProfile = igmsUserProfileHelper.getUserProfile(mapRow);
				userProfiles.add(userProfile);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return userProfiles;
	}
}
