package com.iss.team1.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "LeaveType")
public class LeaveType {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "LeaveTypeID")
	private Integer leaveTypeId;
	@Column(name = "LeaveType")
	private String leaveType;
	
	@OneToMany(targetEntity = LeaveHistory.class, mappedBy = "leaveType")
	private Collection<LeaveHistory> leaveHistories;
	
	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveType(String leaveType) {
		super();
		this.leaveType = leaveType;
	}

	public Integer getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Integer leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Collection<LeaveHistory> getLeaveHistories() {
		return leaveHistories;
	}

	public void setLeaveHistories(Collection<LeaveHistory> leaveHistories) {
		this.leaveHistories = leaveHistories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leaveHistories == null) ? 0 : leaveHistories.hashCode());
		result = prime * result + ((leaveType == null) ? 0 : leaveType.hashCode());
		result = prime * result + ((leaveTypeId == null) ? 0 : leaveTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeaveType other = (LeaveType) obj;
		if (leaveHistories == null) {
			if (other.leaveHistories != null)
				return false;
		} else if (!leaveHistories.equals(other.leaveHistories))
			return false;
		if (leaveType == null) {
			if (other.leaveType != null)
				return false;
		} else if (!leaveType.equals(other.leaveType))
			return false;
		if (leaveTypeId == null) {
			if (other.leaveTypeId != null)
				return false;
		} else if (!leaveTypeId.equals(other.leaveTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LeaveType [leaveTypeId=" + leaveTypeId + ", leaveType=" + leaveType + ", leaveHistories="
				+ leaveHistories + "]";
	}
}
