package com.dxc.imda.cam.igms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dxc.imda.cam.igms.model.UserRole;

public interface IgmsUserRoleDao {
	
	public UserRole findByRoleName(String roleName);
	
	public Long countAll();
	
	public Long countByRoleNameEquals(String roleName);
	
	public Long countByRoleNameContaining(String roleName);
	
	public Long countByRoleDescEquals(String roleDesc);
	
	public Long countByRoleDescContaining(String roleDesc);
	
	/** Page **/
	
	public Page<UserRole> findAll(Pageable pageable);
	
	public Page<UserRole> findByRoleNameEquals(String roleName, Pageable pageable);

	public Page<UserRole> findByRoleNameContaining(String roleName, Pageable pageable);
	
	public Page<UserRole> findByRoleDescEquals(String roleDesc, Pageable pageable);
	
	public Page<UserRole> findByRoleDescContaining(String roleDesc, Pageable pageable);
	
	/** Update **/
}
