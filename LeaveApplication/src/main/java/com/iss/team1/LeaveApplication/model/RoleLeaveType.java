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
	@NotEmpty
	private Role role;
	@ManyToOne
	@JoinColumn(name="leaveTypeId")
	@NotEmpty
	private LeaveType leaveType;
	
	public RoleLeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleLeaveType(Integer id, Role role, LeaveType leaveType) {
		super();
		this.id = id;
		this.role = role;
		this.leaveType = leaveType;
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

	@Override
	public String toString() {
		return "RoleLeaveType [id=" + id + ", role=" + role.getRoleName() + ", leaveType=" + leaveType + "]";
	}
	
	
	
}
