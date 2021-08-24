package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "department_id")
	private int departmentId;
	
	@NotEmpty(message = "Please enter Department Name.")
	@Size( max = 25, message = "Department Name must not be greater than 25 characters.")
	@Column(name = "department_nm")
	private String departmentName;
	
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public static String departmentNameValid(String departmentName) {
		if(departmentName == null || departmentName.trim().equals("")) {
			return "Department Name is empty";
		}
		else if(departmentName.length()>25) {
			return "Department Name should be not more than 25 characters.";
		}
		return "valid";
	}
	
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}
	
	
	
}
