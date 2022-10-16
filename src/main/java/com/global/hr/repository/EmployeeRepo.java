package com.global.hr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	List<Employee> findByName(String name);
	
	// JPQL
	@Query("select emp from Employee emp where emp.name like :empName")
	List<Employee> filter(@Param("empName") String name);
	// SQL Native
	@Query(value="select * from hr_employees emp where emp.emp_name like :empName", nativeQuery=true)
	List<Employee> filterNative(@Param("empName") String name);
	
	List<Employee> findByDepartmentId(Long deptId);
	
	//@Query("select emp from Employee emp join Department dept on dept.id = emp.department.id where dept.id= :deptId")
	@Query("select emp from Employee emp join emp.department dept where dept.id= :deptId")
	List<Employee> findByDepartment(Long deptId);
}
