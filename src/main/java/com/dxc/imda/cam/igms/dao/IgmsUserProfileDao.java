package com.dxc.imda.cam.igms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dxc.imda.cam.igms.model.UserProfile;

public interface IgmsUserProfileDao {
	
	public UserProfile findByUserId(String userId);
	
	/** List **/
	
	public Long countAll();
	
	public Long countByRoleNameEquals(String roleName);
	
	public Long countByRoleNameContaining(String roleName);	
	
	public Long countByRoleDescEquals(String roleDesc);
	
	public Long countByRoleDescContaining(String roleDesc);	
		
	/** Page **/
	
	public Page<UserProfile> findAll(Pageable pageable);
	
	public Page<UserProfile> findByRoleNameEquals(String roleName, Pageable pageable);

	public Page<UserProfile> findByRoleNameContaining(String roleName, Pageable pageable);
	
	public Page<UserProfile> findByRoleDescEquals(String roleDesc, Pageable pageable);
	
	public Page<UserProfile> findByRoleDescContaining(String roleDesc, Pageable pageable);
	
	/** Update **/
	
	public UserProfile updateUser(UserProfile userProfile);
}
