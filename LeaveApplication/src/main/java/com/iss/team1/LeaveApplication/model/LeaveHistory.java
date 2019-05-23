package com.iss.team1.LeaveApplication.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;


@Entity
public class LeaveHistory {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@ManyToOne
    @JoinColumn(name="staffId")
	@NotEmpty
	private Staff staff;
	@ManyToOne
    @JoinColumn(name="leaveId")
	@NotEmpty
	private LeaveType leaveType;
	@NotEmpty
	private LocalDate fromDate;
	private LocalDate toDate;
	private String description;
	@NotEmpty
	private Integer status;
	@NotEmpty
	private Integer noOfDays;
	private String managerComment;
	private String additionalReason;
	private String workDissemination;
	private String contactDetails;
	
	public LeaveHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveHistory(Staff staff, LeaveType leaveType, LocalDate fromDate, LocalDate toDate, String description,
			Integer status, Integer noOfDays, String managerComment, String additionalReason, String workDissemination,
			String contactDetails) {
		super();
		this.staff = staff;
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.description = description;
		this.status = status;
		this.noOfDays = noOfDays;
		this.managerComment = managerComment;
		this.additionalReason = additionalReason;
		this.workDissemination = workDissemination;
		this.contactDetails = contactDetails;
	}

	public Long getid() {
		return id;
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
	
	public LocalDate getFromDate() {
		return fromDate;
	}
	
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	
	public LocalDate getToDate() {
		return toDate;
	}
	
	public void setToDate(LocalDate toDate) {
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
	
	public String getmanagerComment() {
		return managerComment;
	}
	
	public void setmanagerComment(String managerComment) {
		this.managerComment = managerComment;
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
	public String toString() {
		return "LeaveHistory [id=" + id + ", staff=" + staff.getStaffName() + ", leaveType=" + leaveType.getLeaveTypeName() + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", description=" + description + ", status=" + status
				+ ", noOfDays=" + noOfDays + ", managerComment=" + managerComment + ", additionalReason=" + additionalReason
				+ ", workDissemination=" + workDissemination + ", contactDetails=" + contactDetails + "]";
	}
	
	
}
