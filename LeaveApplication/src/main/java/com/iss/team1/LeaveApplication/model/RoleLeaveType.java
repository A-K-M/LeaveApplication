package com.iss.team1.LeaveApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class RoleLeaveType {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;
	@ManyToOne
	@JoinColumn(name="leaveTypeId")
	private LeaveType leaveType;
	private double noOfDays;
	

	public RoleLeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleLeaveType(Integer id, Role role, LeaveType leaveType, double noOfDays) {
		super();
		this.id = id;
		this.role = role;
		this.leaveType = leaveType;
		this.noOfDays = noOfDays;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(double noOfDays) {
		this.noOfDays = noOfDays;
	}

	@Override
	public String toString() {
		return "RoleLeaveType [id=" + id + ", role=" + role + ", leaveType=" + leaveType + ", noOfDays=" + noOfDays
				+ "]";
	}	
	
}
