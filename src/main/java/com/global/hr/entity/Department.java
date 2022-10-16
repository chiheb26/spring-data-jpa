package com.global.hr.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="hr_department")
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE , generator="department_gen")
	//@SequenceGenerator(name="department_gen", sequenceName="department_seq" , initialValue=100)
	//@GeneratedValue(strategy=GenerationType.TABLE, generator="department_gen")
	//@TableGenerator(name="department_gen" , table="department_seq" , allocationSize=1)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="department")
	private List<Employee> employees;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
