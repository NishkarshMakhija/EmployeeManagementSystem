package com.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "STATUSREPORT")
public class StatusReport {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "statusrptid")
	private int statusReportId;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "complianceid")
	private Compliance compliance;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "empid")
	private Employee employee;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "createdate")
	private Date createDate;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id")
	private Department department;

	public int getStatusReportId() {
		return statusReportId;
	}

	public void setStatusReportId(int statusReportId) {
		this.statusReportId = statusReportId;
	}

	public Compliance getCompliance() {
		return compliance;
	}

	public void setCompliance(Compliance compliance) {
		this.compliance = compliance;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
