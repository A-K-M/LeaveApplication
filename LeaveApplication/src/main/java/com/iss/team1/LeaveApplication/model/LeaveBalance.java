package com.iss.team1.LeaveApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class LeaveBalance {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="staffId")
	private Staff staff;
	@ManyToOne
	@JoinColumn(name="leaveTypeId")
	private LeaveType leaveType;
	private int balanceLeave;
	
	public LeaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveBalance(Integer id, Staff staff, LeaveType leaveType, int balanceLeave) {
		super();
		this.id = id;
		this.staff = staff;
		this.leaveType = leaveType;
		this.balanceLeave = balanceLeave;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public int getBalanceLeave() {
		return balanceLeave;
	}

	public void setBalanceLeave(int balanceLeave) {
		this.balanceLeave = balanceLeave;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "LeaveBalance [id=" + id + ", staff=" + staff.getStaffName() + ", leaveType=" + leaveType.getLeaveTypeName() + ", balanceLeave="
				+ balanceLeave + "]";
	}
	
	
}
