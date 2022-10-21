package com.dxc.imda.cam.igms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.igms.entity.IgmsCamApiAudit;


@Repository
public interface IgmsCamApiAuditDao extends JpaRepository<IgmsCamApiAudit, Long> {
	
}
