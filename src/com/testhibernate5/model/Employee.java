package com.testhibernate5.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
		@Id
		@Column(name="employee_id")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long employeeId;
		
		@Column(name="firstname")
		private String firstname;
		
		@Column(name="lastname")
		private String lastname;
		
		@ManyToOne(cascade={CascadeType.ALL})
		@JoinColumn(name="manager_id")
		private Employee manager;
		
		@OneToMany(mappedBy="manager")
		private Set<Employee> subordinates = new HashSet<Employee>();
		
		public Employee() {
			
		}

		public Employee(String firstName, String lastName) {
			this.firstname= firstName;
			this.lastname = lastName;
		}

		public long getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(long employeeId) {
			this.employeeId = employeeId;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public Employee getManager() {
			return manager;
		}

		public void setManager(Employee manager) {
			this.manager = manager;
		}

		public Set<Employee> getSubordinates() {
			return subordinates;
		}

		public void setSubordinates(Set<Employee> subordinates) {
			this.subordinates = subordinates;
		}
		
		
}
