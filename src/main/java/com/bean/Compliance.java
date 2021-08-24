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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "compliance")
public class Compliance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "complianceid")
	private int complianceId;
	
	@NotEmpty(message = "Please enter RL Type.")
	@Size( max = 15, message = "RL Type must not be greater than 15 characters.")
	@Column(name = "rltype")
	private String rltype;
	
	@NotEmpty(message = "Please enter Regulation Details.")
	@Size( max = 250, message = "Regulation Details must not be greater than 250 characters.")
	@Column(name = "details")
	private String details;
	
	@Column(name = "createdate")
	private Date createDate;
	
	@ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "department_id")
	private Department department;
	
	@Column(name = "status")
	private int status = 1;

	public int getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(int complianceId) {
		this.complianceId = complianceId;
	}

	public String getRltype() {
		return rltype;
	}

	public void setRltype(String rltype) {
		rltype = rltype.trim();
		rltype = rltype.substring(0,1).toUpperCase() + rltype.substring(1);
		this.rltype = rltype;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Compliance [complianceId=" + complianceId + ", rltype=" + rltype + ", details=" + details
				+ ", createDate=" + createDate + ", department=" + department + ", status=" + status + "]";
	}
	
	
	
}
