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

import com.dxc.imda.cam.common.constant.Enums.AppName;
import com.dxc.imda.cam.common.util.PageUtil;
import com.dxc.imda.cam.igms.dao.IgmsUserRoleDao;
import com.dxc.imda.cam.igms.helper.IgmsUserRoleHelper;
import com.dxc.imda.cam.igms.model.UserRole;

@Repository
public class IgmsUserRoleDaoImpl implements IgmsUserRoleDao{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate igmsJdbcTemplate;

	@Autowired
	private IgmsUserRoleHelper igmsUserRoleHelper;

	@Autowired
	private PageUtil pageUtil;

	@Autowired
	public IgmsUserRoleDaoImpl(JdbcTemplate igmsJdbcTemplate) {
		this.igmsJdbcTemplate = igmsJdbcTemplate;
	}

	private static final String countQuery = "SELECT count(userRole.GROUP_UID) "
		+ "FROM SYS_GROUPS userRole ";

	private static final String sqlQuery = "SELECT userRole.* "
		+ "FROM SYS_GROUPS userRole ";

	private static final String whereClauseEqRoleName = "WHERE UPPER(userRole.GROUP_NAME) = ? ";
	private static final String whereClauseEqRoleDesc = "WHERE UPPER(userRole.GROUP_DESCRIPTION) = ? ";

	private static final String whereClauseLikeRoleName = "WHERE UPPER(userRole.GROUP_NAME) LIKE ? ";
	private static final String whereClauseLikeRoleDesc = "WHERE UPPER(userRole.GROUP_DESCRIPTION) LIKE ? ";

	private static final String pageClause = "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";

	@Override
	public UserRole findByRoleName(String roleName) {
		UserRole userRole = new UserRole();
		try {
			String sql = sqlQuery + whereClauseEqRoleName;
			logger.info("findByRoleName sql: " + sql);
			Map<String, Object> mapRow = igmsJdbcTemplate.queryForMap(
				sql, new Object[]{roleName.toUpperCase()});
			userRole = igmsUserRoleHelper.getUserRole(mapRow);
		} catch (Exception e){
			e.printStackTrace();
			userRole = null;
		}
		return userRole;
	}

	/** Count **/

	@Override
	public Long countAll() {
		String sql = countQuery;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class);
	}

	@Override
	public Long countByRoleNameEquals(String roleName) {
		String sql = countQuery + whereClauseEqRoleName;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{roleName.toUpperCase()});
	}

	@Override
	public Long countByRoleNameContaining(String roleName) {
		String sql = countQuery + whereClauseLikeRoleName;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{"%" + roleName.toUpperCase() + "%"});
	}

	@Override
	public Long countByRoleDescEquals(String roleDesc) {
		String sql = countQuery + whereClauseEqRoleDesc;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{roleDesc.toUpperCase()});
	}

	@Override
	public Long countByRoleDescContaining(String roleDesc) {
		String sql = countQuery + whereClauseLikeRoleDesc;
		return igmsJdbcTemplate.queryForObject(
				sql, Long.class, new Object[]{"%" + roleDesc.toUpperCase() + "%"});
	}

	/** List **/

	@Override
	public Page<UserRole> findAll(Pageable pageable){
		List<UserRole> userRoles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + orderByAndDirectionClause + pageClause;
			logger.info("findAll sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[] {pageNum - 1, pageable.getPageSize()});
			logger.info("findAll results: " + results.size());
			userRoles = getUserRoles(results);
			logger.info("findAll userRoles: " + userRoles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserRole>(userRoles, pageable, userRoles.size());
	}

	@Override
	public Page<UserRole> findByRoleNameEquals(String roleName, Pageable pageable){
		List<UserRole> userRoles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseEqRoleName + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleNameEquals sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{roleName.toUpperCase(), pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleNameEquals results.size(): " + results.size());
			userRoles = getUserRoles(results);
			logger.info("findByRoleNameEquals userRoles.size(): " + userRoles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserRole>(userRoles, pageable, userRoles.size());
	}

	@Override
	public Page<UserRole> findByRoleNameContaining(String roleName, Pageable pageable){
		List<UserRole> userRoles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseLikeRoleName + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleNameContaining sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{"%" + roleName.toUpperCase() + "%", pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleNameContaining results.size(): " + results.size());
			userRoles = getUserRoles(results);
			logger.info("findByRoleNameContaining userRoles.size(): " + userRoles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserRole>(userRoles, pageable, userRoles.size());
	}

	@Override
	public Page<UserRole> findByRoleDescEquals(String roleDesc, Pageable pageable){
		List<UserRole> userRoles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseEqRoleDesc + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleDescEquals sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{roleDesc.toUpperCase(), pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleDescEquals results.size(): " + results.size());
			userRoles = getUserRoles(results);
			logger.info("findByRoleDescEquals userRoles.size(): " + userRoles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserRole>(userRoles, pageable, userRoles.size());
	}

	@Override
	public Page<UserRole> findByRoleDescContaining(String roleDesc, Pageable pageable){
		List<UserRole> userRoles = new ArrayList<>();
		try {
			String orderByAndDirectionClause = pageUtil.getOrderByAndDirectionClause(AppName.IGMS, pageable);
			String sql = sqlQuery + whereClauseLikeRoleDesc + orderByAndDirectionClause + pageClause;
			logger.info("findByRoleDescContaining sql: " + sql);
			int pageNum = pageUtil.getPageNum(pageable);
			List<Map<String, Object>> results = igmsJdbcTemplate.queryForList(
				sql, new Object[]{"%" + roleDesc.toUpperCase() + "%", pageNum - 1, pageable.getPageSize()});
			logger.info("findByRoleDescContaining results.size(): " + results.size());
			userRoles = getUserRoles(results);
			logger.info("findByRoleDescContaining userRoles.size(): " + userRoles.size());
		} catch (Exception e){
			e.printStackTrace();
		}
		return new PageImpl<UserRole>(userRoles, pageable, userRoles.size());
	}

	private List<UserRole> getUserRoles(List<Map<String, Object>> results){
		List<UserRole> userRoles = new ArrayList<>();
		try {
			for (Map<String, Object> mapRow: results) {
				UserRole userRole = igmsUserRoleHelper.getUserRole(mapRow);
				userRoles.add(userRole);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		logger.info("getUserRoles userRoles.size(): " + userRoles.size());
		return userRoles;
	}
}
