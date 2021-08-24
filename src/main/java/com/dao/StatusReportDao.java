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
import com.bean.StatusReport;


@Repository
public class StatusReportDao {
	
	@Autowired
	EntityManagerFactory emf;
	
	public int addStatusReport(StatusReport statusReport,Compliance compliance) {
		try {
			EntityManager manager = emf.createEntityManager();
			EntityTransaction tran = manager.getTransaction();
			tran.begin();
				manager.persist(statusReport);
				
				String query = "SELECT count(empid) FROM employees e, login_master l WHERE e.department_id = :departmentId and e.empid = l.userid and l.role = 'employee'";
				Query qry = manager.createNativeQuery(query);
				qry.setParameter("departmentId", statusReport.getDepartment().getDepartmentId());
				String totalEmployeeCount = String.valueOf(qry.getSingleResult());
				
				String query1 = "Select count(distinct empid) from statusreport where complianceid = :complianceId";
				Query qry1 = manager.createNativeQuery(query1);
				qry1.setParameter("complianceId", statusReport.getCompliance().getComplianceId());
				String employeeCount = String.valueOf(qry1.getSingleResult());
				
				if(totalEmployeeCount.equals(employeeCount)) {
					compliance = manager.find(Compliance.class, statusReport.getCompliance().getComplianceId());
					compliance.setStatus(0);
					
					//System.out.println(compliance.toString());
					manager.persist(compliance);
				}
				
			tran.commit();
			
			StatusReport sR = manager.find(StatusReport.class, statusReport.getStatusReportId());
			if(sR != null) {
				return sR.getStatusReportId();
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
	public List<StatusReport> getStatusReport(int complianceId, Employee employee) {
		EntityManager manager = emf.createEntityManager();
		String query = "SELECT * FROM StatusReport s where s.complianceid = :complianceId and s.empid = :empId order by createdate desc";
		Query qry = manager.createNativeQuery(query,StatusReport.class);
		qry.setParameter("empId", employee.getEmpId());
		qry.setParameter("complianceId", complianceId);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<StatusReport> getStatusReport(int complianceId) {
		EntityManager manager = emf.createEntityManager();
		String query = "SELECT * FROM StatusReport s where s.complianceid = :complianceId order by createdate desc";
		Query qry = manager.createNativeQuery(query,StatusReport.class);
		qry.setParameter("complianceId", complianceId);
		return qry.getResultList();
	}

}
