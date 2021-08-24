package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Compliance;
import com.bean.Employee;
import com.dao.ComplianceDao;

@Service
public class ComplianceService {
	
	@Autowired
	ComplianceDao complianceDao;
	
	public List<Compliance> getAllCompliances(){
		return complianceDao.getAllCompliances();
	}

	public int addCompliance(Compliance compliance) {
		return complianceDao.addCompliance(compliance);
	}
	
	public List<Compliance> getNewComplianceForEmployee(Employee employee){
		return complianceDao.getNewComplianceForEmployee(employee);
	}
	
	public List<Compliance> getOldComplianceForEmployee(Employee employee){
		return complianceDao.getOldComplianceForEmployee(employee);
	}

	public Compliance getComplianceById(int complianceId) {
		return complianceDao.getCompliancrById(complianceId);
	}
	
	

}
