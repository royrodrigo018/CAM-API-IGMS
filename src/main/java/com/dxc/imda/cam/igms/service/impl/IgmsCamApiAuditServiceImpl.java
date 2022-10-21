package com.dxc.imda.cam.igms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.igms.dao.IgmsCamApiAuditDao;
import com.dxc.imda.cam.igms.entity.IgmsCamApiAudit;
import com.dxc.imda.cam.igms.service.IgmsCamApiAuditService;

@Service
public class IgmsCamApiAuditServiceImpl implements IgmsCamApiAuditService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IgmsCamApiAuditDao igmsCamApiAuditDao;
	
	@Override
	public int saveOrUpdate(IgmsCamApiAudit tempIgmsCamApiAudit) {
		int result = 0;
		try {
			IgmsCamApiAudit igmsCamApiAudit = igmsCamApiAuditDao.save(tempIgmsCamApiAudit);
			result = igmsCamApiAudit != null ? 1 : 0;
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}		
		return result;
	}
}
