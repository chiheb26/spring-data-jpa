package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeResponse;
import com.global.hr.entity.HRStatisticProjection;
import com.global.hr.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	@GetMapping("/{id}")
	public EmployeeResponse findById(@PathVariable Long id) {
		
		Employee emp = employeeService.findById(id);
		EmployeeResponse res = new EmployeeResponse();
		res.setName(emp.getName());
		res.setId(emp.getId());
		res.setSalary(emp.getSalary());
		res.setUser(emp.getUser());
		res.setDepartment(emp.getDepartment());
		
		return res;
	}
	
	@GetMapping("/emp-dept")
	public List<Employee> findByEmpAndDept(@RequestParam String empName ,@RequestParam String deptName){
		return employeeService.findByEmpAndDept(empName,deptName);
	}
	@GetMapping("/count-emp-dept")
	public ResponseEntity<Long> countByEmpAndDept(@RequestParam String empName ,@RequestParam String deptName){
		return ResponseEntity.ok(employeeService.countByEmpAndDept(empName,deptName));
	}
	@DeleteMapping("/emp-dept")
	public ResponseEntity<Long> deleteByEmpAndDept(@RequestParam String empName ,@RequestParam String deptName){
		return ResponseEntity.ok(employeeService.deleteByEmpAndDept(empName,deptName));
	}
	
	@GetMapping("/filter")
	public List<Employee> filterByNameAndSalary(@RequestParam String name,@RequestParam Double salary){
		return employeeService.filterByNameAndSalary(name,salary);
	}
	
	@GetMapping("/filters")
	public List<Employee> filterByName(@Param("empName") String name){
		return employeeService.filterByName(name);

	}
	
	@GetMapping("/filter-sorted")
	
	public List<Employee> filterByNameSorted(@Param("empName") String name,
			@Param("sortCol") String sortCol,@Param("direction") Boolean isAsc){
		return employeeService.filterByNameSorted(name,sortCol,isAsc);

	}
	
	@GetMapping("/filter-sorted-paged")
	public ResponseEntity<Page<Employee>> filterByNameSortedPageable(@Param("empName") String name,
			@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,
			@Param("sortCol") String sortCol,@Param("direction")Boolean isAsc){
		
		
		return ResponseEntity.ok(employeeService.filterByNameSortedPageable(name,pageNum,pageSize,sortCol,isAsc));

	}
	@GetMapping("/filter-sorted-paged-order")
	public ResponseEntity<Page<Employee>> filterByNameSortedPageableWithOrder(@Param("empName") String name,
			@Param("pageNum") int pageNum, @Param("pageSize") int pageSize,
			@Param("sortCol") String sortCol,@Param("direction")Boolean isAsc){
		
		
		return ResponseEntity.ok(employeeService.filterByNameSortedPageableWithOrder(name,pageNum,pageSize,sortCol,isAsc));

	}
	
	@PostMapping("")
	public Employee insert(@RequestBody Employee emp) {
		return employeeService.insert(emp);
	}
	@PutMapping("")
	public Employee update(@RequestBody Employee emp) {
		
		return employeeService.update(emp);
	}
	
	@GetMapping("/department/{deptId}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptId){
		return employeeService.findByDepartmentId(deptId);
	}
	@GetMapping("/departmentt/{deptId}")
	public List<Employee> findByDepartment(@PathVariable Long deptId){
		return employeeService.findByDepartment(deptId);
	}
	@GetMapping("/salary/{salary}")
	public ResponseEntity<List<Employee>> findBySalary(@PathVariable Double salary){
		return ResponseEntity.ok(employeeService.findBySalary(salary));
	}
	@GetMapping("/salary-name")
	public ResponseEntity<List<Employee>> findBySalaryAndName(@RequestParam Double salary,@RequestParam String name){
		return ResponseEntity.ok(employeeService.findBySalaryAndName(salary,name));
	}
	
	@GetMapping("/statistic")
	public ResponseEntity<HRStatisticProjection> getHRStatistic() {
		return ResponseEntity.ok(employeeService.getHRStatistic());
	}
}
