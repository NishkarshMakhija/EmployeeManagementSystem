package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Employee;
import com.bean.Login;

@Repository
public class EmployeeDao {
	
	@Autowired
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("from Employee");
		return qry.getResultList();
	}

	public int addEmployee(Employee employee,Login login) {
		
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
		try {
			
			tran.begin();
				manager.persist(employee);
				Employee e = manager.find(Employee.class, employee.getEmpId());
				if(e!=null) {
					login.setUserid(e.getEmpId());
					manager.persist(login);
				}
			tran.commit();
			if(e!=null) {
				System.out.println("Returning employee Id");
				return e.getEmpId();
			}else {
				return 0;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			tran.rollback();
			return -1;
		}
	}

	public Employee getEmployeeById(int employeeId) {
		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				Employee e = manager.find(Employee.class, employeeId);			
			tran.commit();
			return e;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public 	int updateEmployeeDetails(Employee e,Login login) {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction tran = manager.getTransaction();
				
		Employee e1 = manager.find(Employee.class, e.getEmpId());
		Login l1 = manager.find(Login.class, e.getEmpId());
		if(e1!=null) {
			tran.begin();
				e1.setFirstName(e.getFirstName());
				e1.setLastName(e.getLastName());
				e1.setDob(e.getDob());
				e1.setEmail(e.getEmail());
				e1.setDepartment(e.getDepartment());
				l1.setPassword(login.getPassword());
				//l1.setRole(login.getRole());
				manager.merge(e1);
				manager.merge(l1);
			tran.commit();
			return 1;
		}else {
			return 0;
		}	
	}

}
