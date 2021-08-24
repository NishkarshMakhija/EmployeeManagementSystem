package com.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Employee;
import com.bean.Login;
import com.dao.EmployeeDao;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDao employeeDao;
	
	public boolean checkDateOfBirth(LocalDate birthDate) {
		LocalDate currentDate = LocalDate.now();
		int age = Period.between(birthDate, currentDate).getYears();
		if(age>=24) {
			return true;
		}
		return false;
	}
	
	public List<Employee> getAllEmployees(){
		return employeeDao.getAllEmployees();
	}

	public int addEmployee(Employee employee,Login login) {
		return employeeDao.addEmployee(employee,login);
	}

	public Employee getEmployeeById(int employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}
	
	public 	int updateEmployeeDetails(Employee e,Login login) {
		return employeeDao.updateEmployeeDetails(e,login);
	}
	
}