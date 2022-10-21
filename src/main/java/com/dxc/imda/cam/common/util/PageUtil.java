package com.dxc.imda.cam.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.AppName;
import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.common.model.UserInfo;

@Component
public class PageUtil {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> getUserInfoMap(Page<UserInfo> pageInfo){
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.SCHEMAS, "urn:ietf:params:scim:api:messages:2.0:ListResponse");
		//map.put("totalResults", pageInfo.getTotalElements());
		map.put(Constants.RESOURCES, pageInfo.getContent());
		map.put(Constants.START_INDEX, pageInfo.getNumber() + 1);
		map.put(Constants.ITEMS_PER_PAGE, pageInfo.getNumberOfElements());
		return map;	
	}
	
	public Map<String, Object> getGroupInfoMap(Page<GroupInfo> pageInfo){
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.SCHEMAS, "urn:ietf:params:scim:api:messages:2.0:ListResponse");
		//map.put("totalResults", pageInfo.getTotalElements());
		map.put(Constants.RESOURCES, pageInfo.getContent());
		map.put(Constants.START_INDEX, pageInfo.getNumber() + 1);
		map.put(Constants.ITEMS_PER_PAGE, pageInfo.getNumberOfElements());
		return map;	
	}
	
	// check ParamUtil
	public Pageable getPageable(int page, int size, AppName appName,
		ResourceType resourceType, String[] sortByArray) {
		
		ParamUtil paramUtil = new ParamUtil();		
		String sortedBy = paramUtil.getSortedBy(sortByArray);
		List<String> orders = new ArrayList<>();
		// Check for NORS, NEUPC, DAS or IGMS
		orders = paramUtil.getSortOrders(resourceType, sortedBy);
		
		Direction direction = paramUtil.getDirection(sortByArray);
		logger.info("getPageable sortedBy, orders, direction:: {0} {1} {2} " + " --> "+ sortedBy +", "+ orders +", "+ direction);
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, orders.get(0)));
		logger.info("getPageable pageable: " + pageable.toString() +" --> "+ pageable.getSort());
		return pageable;		
	}
	
	public int getPageNum(Pageable pageable) {
		int pageNum = pageable.getPageNumber() + 1;
		int pageSize = pageable.getPageSize();
		logger.info("getPageNum pageNum: " + pageNum);
		if (pageNum == 1) { 
			// do nothing
		}else {
			pageNum = (pageNum-1) * pageSize+1;	
		}						
		logger.info("getPageNum pageSize: " + pageSize);
		return pageNum;		
	}
	
	public String getOrderByAndDirectionClause(AppName appName, Pageable pageable) {
		String property = null;
		Direction direction = null;
		String orderBy = null;
		
		Sort sort = pageable.getSort();		
		for (Sort.Order order : sort){
			property = order.getProperty();
			direction = order.getDirection();			    
		}
		logger.info("getOrderByAndDirectionClause property: " + property);
		logger.info("getOrderByAndDirectionClause direction: " + direction);
		
		if (property.equalsIgnoreCase("userId")) {
			orderBy = "userProfile.LOGIN_ID";
		}else if (property.equalsIgnoreCase("userName")) {
			orderBy = "userProfile.USERNAME";
		}else if (property.equalsIgnoreCase("userRole.roleName")) {
			orderBy = "userRole.GROUP_NAME";
		}else if (property.equalsIgnoreCase("userRole.roleDesc")) {
			orderBy = "userRole.GROUP_DESCRIPTION";
		}		
		logger.info("getOrderByAndDirectionClause orderBy: " + orderBy);
		StringBuilder sbOrderBy = new StringBuilder();
		sbOrderBy.append("ORDER BY ");
		sbOrderBy.append(orderBy).append(" ");
		sbOrderBy.append(direction).append(" ");
		return sbOrderBy.toString();		
	}
}
