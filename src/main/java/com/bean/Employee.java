package com.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.validator.constraints.Email;

import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "empid")
	private int empId;
	
	@NotEmpty(message = "Please enter First Name.")
	@Size( max = 45, message = "First Name must not be greater than 45 characters.")
	@Column(name = "firstname")
	private String firstName;
	
	@NotEmpty(message = "Please enter Last Name.")
	@Size( max = 45, message = "Last Name must not be greater than 45 characters.")
	@Column(name = "lastname")
	private String lastName;
	
	@NotNull(message = "Please enter DOB.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob")
	private Date dob;
	
	@NotEmpty(message = "Please enter Email id.")
	@Size( max = 100, message = "Email must not be greater than 100 characters.")
	@Email(message = "Email format is wrong.")
	@Column(name = "email")
	private String email;
	
	@ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "department_id")
	private Department department;
	
	@Transient
	@Size(max = 30, message = "Password must not be greater than 30 characters.")
	private String password;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		firstName = firstName.trim();
		firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		lastName = lastName.trim();
		lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		email = email.trim();
		email = email.toLowerCase();
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + "]";
	}
	
	
	
}
