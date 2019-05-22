package com.iss.team1.LeaveApplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "LeaveHistory")
public class LeaveHistory {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "LeaveID")
	private Long leaveId;
	
	@OneToOne
    @JoinColumn(name="UserName")
	private Staff staff;
	
	@OneToOne
    @JoinColumn(name="LeaveTypeID")
	private LeaveType leaveType;
	
	@Column(name = "FromDate")
	private Date fromDate;
	@Column(name = "ToDate")
	private Date toDate;
	@Column(name = "Description")
	private String description;
	@Column(name = "Status")
	private Integer status;
	@Column(name = "NoOfDays")
	private Integer noOfDays;
	@Column(name = "Comment")
	private String comment;
	@Column(name = "AdditionalReason")
	private String additionalReason;
	@Column(name = "WorkDissemination")
	private String workDissemination;
	@Column(name = "ContactDetails")
	private String contactDetails;
	public LeaveHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveHistory(Staff staff, LeaveType leaveType, Date fromDate, Date toDate, String description,
			Integer status, Integer noOfDays, String comment, String additionalReason, String workDissemination,
			String contactDetails) {
		super();
		this.staff = staff;
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
		this.status = status;
		this.noOfDays = noOfDays;
		this.comment = comment;
		this.additionalReason = additionalReason;
		this.workDissemination = workDissemination;
		this.contactDetails = contactDetails;
	}

	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
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
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(Integer noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAdditionalReason() {
		return additionalReason;
	}
	public void setAdditionalReason(String additionalReason) {
		this.additionalReason = additionalReason;
	}
	public String getWorkDissemination() {
		return workDissemination;
	}
	public void setWorkDissemination(String workDissemination) {
		this.workDissemination = workDissemination;
	}
	public String getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalReason == null) ? 0 : additionalReason.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((contactDetails == null) ? 0 : contactDetails.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fromDate == null) ? 0 : fromDate.hashCode());
		result = prime * result + ((leaveId == null) ? 0 : leaveId.hashCode());
		result = prime * result + ((leaveType == null) ? 0 : leaveType.hashCode());
		result = prime * result + ((noOfDays == null) ? 0 : noOfDays.hashCode());
		result = prime * result + ((staff == null) ? 0 : staff.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toDate == null) ? 0 : toDate.hashCode());
		result = prime * result + ((workDissemination == null) ? 0 : workDissemination.hashCode());
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
		LeaveHistory other = (LeaveHistory) obj;
		if (additionalReason == null) {
			if (other.additionalReason != null)
				return false;
		} else if (!additionalReason.equals(other.additionalReason))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (contactDetails == null) {
			if (other.contactDetails != null)
				return false;
		} else if (!contactDetails.equals(other.contactDetails))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fromDate == null) {
			if (other.fromDate != null)
				return false;
		} else if (!fromDate.equals(other.fromDate))
			return false;
		if (leaveId == null) {
			if (other.leaveId != null)
				return false;
		} else if (!leaveId.equals(other.leaveId))
			return false;
		if (leaveType == null) {
			if (other.leaveType != null)
				return false;
		} else if (!leaveType.equals(other.leaveType))
			return false;
		if (noOfDays == null) {
			if (other.noOfDays != null)
				return false;
		} else if (!noOfDays.equals(other.noOfDays))
			return false;
		if (staff == null) {
			if (other.staff != null)
				return false;
		} else if (!staff.equals(other.staff))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (toDate == null) {
			if (other.toDate != null)
				return false;
		} else if (!toDate.equals(other.toDate))
			return false;
		if (workDissemination == null) {
			if (other.workDissemination != null)
				return false;
		} else if (!workDissemination.equals(other.workDissemination))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LeaveHistory [leaveId=" + leaveId + ", staff=" + staff.getStaffName() + ", leaveType=" + leaveType.getLeaveType() + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", description=" + description + ", status=" + status
				+ ", noOfDays=" + noOfDays + ", comment=" + comment + ", additionalReason=" + additionalReason
				+ ", workDissemination=" + workDissemination + ", contactDetails=" + contactDetails + "]";
	}
	
	
}
