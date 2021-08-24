package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Department;

@Repository
public class DepartmentDao {

	@Autowired
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	public List<Department> getAllDepartments() {
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("from Department");
		return qry.getResultList();
	}
	
	public Department getDepartmentById(int departmentId) {
		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				Department d = manager.find(Department.class, departmentId);
			tran.commit();
			return d;
		}
		catch(Exception e) {
			return null;
		}
	}

	public int addDepartment(Department department) {

		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				manager.persist(department);
			tran.commit();
			
			Department d = manager.find(Department.class, department.getDepartmentId());
			if(d!=null) {
				return d.getDepartmentId();
			}else {
				return 0;
			}
		}
		catch(Exception e) {
			return -1;
		}
	}
	
}
