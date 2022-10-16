package com.global.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.service.DepartmentService;
import com.global.hr.service.EmployeeService;

//@Configuration
public class StartupConfig implements CommandLineRunner{
	
	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;
	@Override
	public void run(String... args) throws Exception {
		Department dept = new Department();
		dept.setName("IT");
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setSalary(1200D);
		emp.setDepartment(dept);
		departmentService.insert(dept);
		employeeService.insert(emp);
		
		
	}

}
