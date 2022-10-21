package com.global.hr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
	
	Long getId();
	String getName();
	Double getSalary();
	
	// Open projection
	@Value("#{target.name + ' ' + target.salary}")
	String getNameAndSalary();
}
