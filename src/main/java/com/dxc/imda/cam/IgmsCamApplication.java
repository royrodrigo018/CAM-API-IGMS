package com.dxc.imda.cam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.model.UserInfo;
import com.dxc.imda.cam.igms.dao.impl.IgmsUserProfileDaoImpl;
import com.dxc.imda.cam.igms.dao.impl.IgmsUserRoleDaoImpl;
import com.dxc.imda.cam.igms.model.UserProfile;
import com.dxc.imda.cam.igms.model.UserRole;
import com.dxc.imda.cam.igms.service.IgmsUserProfileService;
import com.dxc.imda.cam.igms.service.IgmsUserRoleService;

@SpringBootApplication
@Component
@EnableTransactionManagement
public class IgmsCamApplication extends SpringBootServletInitializer implements CommandLineRunner{
	
	private static Logger logger = LoggerFactory.getLogger(IgmsCamApplication.class);

	public static void main(String[] args) {
		logger.info("********** STARTING THE APPLICATION ********** ");
		SpringApplication.run(IgmsCamApplication.class, args);
		logger.info("********** APPLICATION END ********** ");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IgmsCamApplication.class);
	}
	
	@Autowired
	IgmsUserProfileDaoImpl igmsUserProfileDaoImpl;	
	@Autowired
	IgmsUserRoleDaoImpl igmsUserRoleDaoImpl;

	@Autowired
	IgmsUserProfileService igmsUserProfileService;
	
	@Autowired
	IgmsUserRoleService igmsUserRoleService;
	
//	@Autowired
//	UUserProfileDao uUserProfileDao;

	@Override
	public void run(String... args) throws Exception {
				
		String userId = "UAT_MEMBER1";
		String roleName1 = "Group (IAU) - Audit";
		String roleDesc1 = "SUPER ADMIN";
		String roleName2 = "Group";
		String roleDesc2 = "Finance";
		long uid = 26L;
		
		Long count = 0L;
		
//		List<UserProfile> userProfiles = new ArrayList<>();
//		List<UserInfo> userInfos = new ArrayList<>();	
		UserProfile userProfile = new UserProfile();	
		UserInfo userInfo = new UserInfo();
		UpdateInfo updateInfo = new UpdateInfo();

				
		//userInfo = igmsUserProfileService.findByUserId("S7404346A");
		
//		updateInfo = igmsUserProfileService.removeUser("S7404346A", "InActive");
//
//		userInfo = igmsUserProfileService.updateUser("S7404346A", "Active");

//		count = igmsUserProfileService.countAll();		
//		logger.info("findAll count: " + count);

//		count = igmsUserProfileService.countByRoleNameEquals(roleName1);
//		logger.info("findByRoleNameEquals count: " + count);
//
//		count = igmsUserProfileService.countByRoleNameContaining(roleName2);
//		logger.info("findByRoleNameContaining count: " + count);
//		
//		count = igmsUserProfileService.countByRoleDescEquals(roleDesc1);
//		logger.info("findByRoleDescEquals count: " + count);
//
//		count = igmsUserProfileService.countByRoleDescContaining(roleDesc2);		
//		logger.info("findByRoleDescContaining count: " + count);
		

//		
//		userInfos = igmsUserProfileService.findAll();		
//		logger.info("findAll userInfos.size(): " + userInfos.size());
//
//		userInfos = igmsUserProfileService.findByRoleNameEquals(roleName1);
//		logger.info("findByRoleNameEquals userInfos.size(): " + userInfos.size());
//
//		userInfos = igmsUserProfileService.findByRoleNameContaining(roleName2);
//		logger.info("findByRoleNameContaining userInfos.size(): " + userInfos.size());
//		
//		userInfos = igmsUserProfileService.findByRoleDescEquals(roleDesc1);
//		logger.info("findByRoleDescEquals userInfos.size(): " + userInfos.size());
//
//		userInfos = igmsUserProfileService.findByRoleDescContaining(roleDesc2);		
//		logger.info("findByRoleDescContaining userInfos.size(): " + userInfos.size());
						
				
		List<UserRole> userRoles = new ArrayList<>();
		List<GroupInfo> groupInfos = new ArrayList<>();
		
		GroupInfo groupInfo = new GroupInfo();
		
//		groupInfo = igmsUserRoleService.findByRoleName(roleName1);
		
//		count = igmsUserRoleService.countAll();		
//		logger.info("findAll count: " + count);
//
//		count = igmsUserRoleService.countByRoleNameEquals(roleName1);
//		logger.info("findByRoleNameEquals count: " + count);
//
//		count = igmsUserRoleService.countByRoleNameContaining(roleName2);
//		logger.info("findByRoleNameContaining count: " + count);
//		
//		count = igmsUserRoleService.countByRoleDescEquals(roleDesc1);
//		logger.info("findByRoleDescEquals count: " + count);
//
//		count = igmsUserRoleService.countByRoleDescContaining(roleDesc2);		
//		logger.info("findByRoleDescContaining count: " + count);
//		
//		groupInfos = igmsUserRoleService.findAll();
//
//		groupInfos = igmsUserRoleService.findByRoleNameEquals(roleName1);
//		//logger.info("groupInfos: " + groupInfos.toString());	
//		
//		groupInfos = igmsUserRoleService.findByRoleNameContaining(roleName2);
//		//logger.info("userRoles: " + userRoles.toString());	
//		
//		groupInfos = igmsUserRoleService.findByRoleDescEquals(roleDesc1);
//		//logger.info("groupInfos: " + groupInfos.toString());			
//		
//		groupInfos = igmsUserRoleService.findByRoleDescContaining(roleDesc2);
//		//logger.info("userRoles4: " + userRoles.toString());		
	}
}
