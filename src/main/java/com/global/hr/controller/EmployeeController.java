package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/filter")
	public List<Employee> findByName(@RequestParam String name){
		return employeeService.filter(name);
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
}
