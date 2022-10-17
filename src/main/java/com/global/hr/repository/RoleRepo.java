package com.global.hr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	
	
}
