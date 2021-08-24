package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.Compliance;
import com.bean.Employee;

@Repository
public class ComplianceDao {

	@Autowired
	EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	public List<Compliance> getAllCompliances() {
		EntityManager manager = emf.createEntityManager();
		Query qry = manager.createQuery("from Compliance");
		return qry.getResultList();
	}

	public int addCompliance(Compliance compliance) {
		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				manager.persist(compliance);
			tran.commit();
			
			Compliance c = manager.find(Compliance.class, compliance.getComplianceId());
			if(c!=null) {
				return c.getComplianceId();
			}else {
				return 0;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Compliance> getNewComplianceForEmployee(Employee employee) {
		EntityManager manager = emf.createEntityManager();
		String query = "SELECT * FROM COMPLIANCE c where department_id = :department_id and c.status =1 and NOT EXISTS (SELECT * from STATUSREPORT s where empid = :empId and c.complianceid = s.complianceid)";
		Query qry = manager.createNativeQuery(query,Compliance.class);
		qry.setParameter("empId", employee.getEmpId());
		qry.setParameter("department_id", employee.getDepartment().getDepartmentId());
		return qry.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Compliance> getOldComplianceForEmployee(Employee employee) {
		EntityManager manager = emf.createEntityManager();
		String query = "SELECT * FROM COMPLIANCE c where department_id = :department_id and EXISTS (SELECT * from STATUSREPORT s where empid = :empId and c.complianceid = s.complianceid)";
		Query qry = manager.createNativeQuery(query,Compliance.class);
		qry.setParameter("empId", employee.getEmpId());
		qry.setParameter("department_id", employee.getDepartment().getDepartmentId());
		return qry.getResultList();
	}

	public Compliance getCompliancrById(int complianceId) {
		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				Compliance compliance = manager.find(Compliance.class, complianceId);				
			tran.commit();
			return compliance;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
