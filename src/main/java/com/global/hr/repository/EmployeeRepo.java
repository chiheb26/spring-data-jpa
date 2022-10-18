package com.global.hr.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;
import com.global.hr.entity.HRStatisticProjection;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	List<Employee> findByName(String name);
	List<Employee> findByNameContaining(String name);
	List<Employee> findByNameContainingAndDepartmentNameContaining(String empName,String deptName);
	Long countByNameContainingAndDepartmentNameContaining(String empName,String deptName);

	@Modifying(clearAutomatically=true , flushAutomatically=true)
	@Transactional
	Long deleteByNameContainingAndDepartmentNameContaining(String empName,String deptName);
	// Named Query
	List<Employee> findBySalary(Double salary);
	List<Employee> findBySalaryAndName(Double salary,String name);
	// Named Native Query
	List<Employee> findByDepartment(Long deptId);
	// JPQL
	@Query("select emp from Employee emp where emp.name like :empName and emp.salary >= :empSalary")
	List<Employee> filterByNameAndSalary(@Param("empName") String name, @Param("empSalary") Double salary);
	@Query("select emp from Employee emp where emp.name like %:empName%")
	List<Employee> filterByName(@Param("empName") String name);
	@Query("select emp from Employee emp where emp.name like %:empName%")
	List<Employee> filterByNameSorted(@Param("empName") String name,Sort sort);
	@Query("select emp from #{#entityName} emp where emp.name like %:empName%")
	Page<Employee> filterByNameSortedPageable(@Param("empName") String name,Pageable pageable);
	// SQL Native
	@Query(value="select * from hr_employees emp where emp.emp_name like :empName", nativeQuery=true)
	List<Employee> filterNative(@Param("empName") String name);
	
	List<Employee> findByDepartmentId(Long deptId);
	
	@Query(value="select (select count(*) from hr_department) deptCount,"
			+ "(select count(*) from hr_employees) empCount,"
			+ "(select count(*) from sec_users) userCount" , nativeQuery=true)
	HRStatisticProjection getHRStatistic();
	
	//@Query("select emp from Employee emp join Department dept on dept.id = emp.department.id where dept.id= :deptId")
	//@Query("select emp from Employee emp join emp.department dept where dept.id= :deptId")
	//List<Employee> findByDepartment(Long deptId);
}
