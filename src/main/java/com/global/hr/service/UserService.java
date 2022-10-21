package com.global.hr.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;
import com.global.hr.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;

	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow();
	}

	public User insert(User user) {
		return userRepo.save(user);
	}
	public User update(User user) {
		
		User current = userRepo.findById(user.getId()).get();
		current.setUserName(user.getUserName());
		current.setPassword(user.getPassword());
		return userRepo.save(current);
	}
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	@Transactional(rollbackFor = {SQLException.class})
	public void addRoleForAllUsers(String roleName) {
		// begin transaction
		Role role = roleService.findByName(roleName);
		this.findAll().stream().forEach(user->{user.addRole(role);this.update(user);});
		// commit
		// close transaction
	}
}
