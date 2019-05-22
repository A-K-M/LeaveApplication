package com.iss.team1.LeaveApplication.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;



@Entity
public class Staff {
	private int staffId;
	private String staffName;
	@Id
	@Length(max=60)
	@Column(name = "UserName")
	private String userName;
	private String password;
	private Date joiningDate;
	private String emailId;
	private String roleId;
	private int reportsTo;
	
	@OneToMany(targetEntity = LeaveHistory.class, mappedBy = "staff")
	private Collection<LeaveHistory> leaveHistories;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(int staffId, String staffName, String userName, String password, Date joiningDate, String emailId,
			String roleId, int reportsTo) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
		this.joiningDate = joiningDate;
		this.emailId = emailId;
		this.roleId = roleId;
		this.reportsTo = reportsTo;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public int getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	public Collection<LeaveHistory> getLeaveHistories() {
		return leaveHistories;
	}
	public void setLeaveHistories(Collection<LeaveHistory> leaveHistories) {
		this.leaveHistories = leaveHistories;
	}
	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + ", userName=" + userName + ", password="
				+ password + ", joiningDate=" + joiningDate + ", emailId=" + emailId + ", roleId=" + roleId
				+ ", reportsTo=" + reportsTo + "]";
	}
}
