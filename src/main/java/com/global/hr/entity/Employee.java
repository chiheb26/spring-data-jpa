package com.global.hr.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="hr_employees")
@NamedQuery(name="Employee.findBySalary" , query="select emp from Employee emp where emp.salary >= :salary")
@NamedQuery(name="Employee.findBySalaryAndName" , query="select emp from Employee emp where emp.salary >= :salary and emp.name like :name")

//@SqlResultSetMapping(name="empMapping",entities=@EntityResult(entityClass=Employee.class,
//fields= {@FieldResult(name="id",column="emp_id"),
//		@FieldResult(name="name",column="emp_name"),
//		@FieldResult(name="salary",column="emp_salary")}))
//@NamedNativeQuery(name="findByDepartment",
//query="select emp_id,emp_name,emp_salary from hr_employees where department_id= :deptId",resultSetMapping="empMapping")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id")
	private Long id;
	//@Version
	//private Long version;
	@Column(name="emp_name")
	//@JsonProperty("empName")
	private String name;
	
	@Column(name="emp_salary")
	private Double salary;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE } , fetch=FetchType.LAZY)
	//@JoinColumn(name="department_name",referencedColumnName = "name")
	@JoinColumn(name="department_id")
	//@JsonIgnore
	private Department department;
	
	@OneToOne(cascade= CascadeType.ALL , fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public Employee() {
	
	}
	
	public Employee(Long id, String name, Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
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
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
