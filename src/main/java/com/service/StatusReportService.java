package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Employee;
import com.bean.StatusReport;
import com.dao.StatusReportDao;
import com.bean.Compliance;

@Service
public class StatusReportService {

	@Autowired
	StatusReportDao statusReportDao;
	
	public int addStatusReport(StatusReport statusReport,Compliance compliance) {
		return statusReportDao.addStatusReport(statusReport,compliance);
	}

	public List<StatusReport> getStatusReport(int complianceId, Employee employee) {
		return statusReportDao.getStatusReport(complianceId,employee);
	}

	public List<StatusReport> getStatusReport(int complianceId) {
		return statusReportDao.getStatusReport(complianceId);
	}
}
