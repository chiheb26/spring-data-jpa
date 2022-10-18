package com.global.hr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.entity.HRStatisticProjection;
import com.global.hr.repository.DepartmentRepo;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private DepartmentService departmentService;
	
	public Employee findById(Long id) {
		return employeeRepo.findById(id).orElseThrow();
	}
	public List<Employee> filterByNameAndSalary(String name,Double salary){
		return employeeRepo.filterByNameAndSalary(name,salary);
	}
	public List<Employee> filterByName( String name){
		return employeeRepo.filterByName(name);

	}
	public List<Employee> filterByNameSorted(String name,String sortCol,Boolean isAsc){
		return employeeRepo.filterByNameSorted(name,Sort.by(isAsc?Direction.ASC:Direction.DESC,sortCol));

	}
	public Page<Employee> filterByNameSortedPageable(String name, int pageNum,int pageSize, String sortCol,Boolean isAsc){
		
		Pageable page = PageRequest.of(pageNum, pageSize,Sort.by(isAsc?Direction.ASC:Direction.DESC,sortCol));
		
		return employeeRepo.filterByNameSortedPageable(name,page);

	}
	public Page<Employee> filterByNameSortedPageableWithOrder(String name, int pageNum,int pageSize, String sortCol,Boolean isAsc){
		
		// sort objects with List of Order objects
		List<Order> orders = new ArrayList<>();
		Order order1 = new Order(isAsc?Direction.ASC:Direction.DESC,sortCol);
		orders.add(order1);
		
		Pageable page = PageRequest.of(pageNum, pageSize,Sort.by(order1)); // or orders array if more than order
		
		return employeeRepo.filterByNameSortedPageable(name,page);

	}
	

	public List<Employee> findByEmpAndDept(String empName,String deptName){
		return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	public Long countByEmpAndDept(String empName,String deptName){
		return employeeRepo.countByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	public Long deleteByEmpAndDept(String empName,String deptName){
		return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName, deptName);
	}
	
	public Employee insert(Employee emp) {
		if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			System.out.println("DEPT ID = " + emp.getDepartment().getId());
			Department dept = departmentService.findById(emp.getDepartment().getId());
			dept.setName(emp.getDepartment().getName());
			
			emp.setDepartment(dept);
		}
		return employeeRepo.save(emp);
	}
	public Employee update(Employee emp) {
		
		Employee current = employeeRepo.findById(emp.getId()).get();
		current.setName(emp.getName());
		current.setSalary(emp.getSalary());
		current.setDepartment(emp.getDepartment());
		return employeeRepo.save(current);
	}
	public List<Employee> findAll(){
		return employeeRepo.findAll();
	}
	public List<Employee> findByDepartmentId(Long deptId){
		return employeeRepo.findByDepartmentId(deptId);
	}
	public List<Employee> findByDepartment(Long deptId){
		return employeeRepo.findByDepartment(deptId);
	}
	
	public List<Employee> findBySalary(Double salary){
		return employeeRepo.findBySalary(salary);
	}
	public List<Employee> findBySalaryAndName(Double salary,String name){
		return employeeRepo.findBySalaryAndName(salary,name);
	}
	
	public HRStatisticProjection getHRStatistic() {
		return employeeRepo.getHRStatistic();
	}
}
