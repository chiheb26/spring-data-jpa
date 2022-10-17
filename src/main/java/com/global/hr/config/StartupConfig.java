package com.global.hr.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;
import com.global.hr.service.RoleService;
import com.global.hr.service.UserService;

@Configuration
//@Component
public class StartupConfig implements CommandLineRunner{
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Override
	public void run(String... args) throws Exception {
		
		if(userService.findAll().isEmpty()) {
			// create roles
			Role role1 = new Role();
			role1.setName("Admin");
			Role role2 = new Role();
			role2.setName("User");
			
			roleService.insert(role1);
			roleService.insert(role2);
			
			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(role1);
			Set<Role> userRoles = new HashSet<>();
			userRoles.add(role2);
			
			// create users
			User user1 = new User();
			user1.setUserName("admin");
			user1.setPassword("123");
			user1.setRoles(adminRoles);
			User user2 = new User();
			user2.setUserName("user");
			user2.setPassword("123");
			user2.setRoles(userRoles);
			userService.insert(user1);
			userService.insert(user2);
			
			
		}
		
		
	}

}
