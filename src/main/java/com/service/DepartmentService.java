package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Department;
import com.dao.DepartmentDao;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentDao departmentDao;
	
	public List<Department> getAllDepartments(){
		return departmentDao.getAllDepartments();
	}
	
	public Department getDepartmentById(int departmentId) {
		return departmentDao.getDepartmentById(departmentId);
	}
	
	public int addDepartment(Department department) {
		return departmentDao.addDepartment(department);
	}

}
