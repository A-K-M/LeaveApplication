package com.iss.team1.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
public class LeaveType {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String leaveTypeName;	
	@OneToMany(targetEntity = LeaveHistory.class, mappedBy = "leaveType")
	private Collection<LeaveHistory> leaveHistories;
	@OneToMany(targetEntity = LeaveBalance.class, mappedBy = "leaveType")
	private Collection<LeaveBalance> leaveBalances;
	@OneToMany(targetEntity = RoleLeaveType.class, mappedBy = "leaveType")
	private Collection<RoleLeaveType> roleLeaveType;
	
	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveType(String leaveTypeName) {
		super();
		
		this.leaveTypeName = leaveTypeName;
		
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public Collection<LeaveHistory> getLeaveHistories() {
		return leaveHistories;
	}

	public void setLeaveHistories(Collection<LeaveHistory> leaveHistories) {
		this.leaveHistories = leaveHistories;
	}

	public Collection<LeaveBalance> getLeaveBalances() {
		return leaveBalances;
	}

	public void setLeaveBalances(Collection<LeaveBalance> leaveBalances) {
		this.leaveBalances = leaveBalances;
	}

	public Collection<RoleLeaveType> getRoleLeaveType() {
		return roleLeaveType;
	}

	public void setRoleLeaveType(Collection<RoleLeaveType> roleLeaveType) {
		this.roleLeaveType = roleLeaveType;
	}
	public void setId(int id) {
		this.id= id;
	}
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "LeaveType [id=" + id + ", leaveTypeName=" + leaveTypeName + "]";
	}
	
	
	
	
}
