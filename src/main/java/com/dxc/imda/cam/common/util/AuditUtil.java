package com.dxc.imda.cam.common.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.igms.entity.IgmsCamApiAudit;

public class AuditUtil {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public IgmsCamApiAudit logIgmsAudit(HttpServletRequest request, 
		HttpServletResponse response,
		ResourceType resourceType, Object object) {
		
		IgmsCamApiAudit igmsCamApiAudit = new IgmsCamApiAudit();
		if (resourceType.equals(ResourceType.USER)) {
			igmsCamApiAudit.setResource(ResourceType.USER.toString());
		}else {
			igmsCamApiAudit.setResource(ResourceType.GROUP.toString());
		}
		igmsCamApiAudit.setRequestUri(request.getRequestURI());		
		igmsCamApiAudit.setRequestDate(new Date());
		
		JSONUtil jsonUtil = new JSONUtil();
		String jsonString = jsonUtil.convertObjectToJsonString(object);
		igmsCamApiAudit.setData(jsonString);	
		
		//logger.info("logIgmsAudit response.getStatus(): " + response.getStatus());
		igmsCamApiAudit.setResponseStatus(response.getStatus());
		igmsCamApiAudit.setStatus(Constants.SUCCESS);
		if (response.getStatus() >= 400) {
			igmsCamApiAudit.setStatus(Constants.ERROR);	
		}		
		return igmsCamApiAudit;
	}
}
