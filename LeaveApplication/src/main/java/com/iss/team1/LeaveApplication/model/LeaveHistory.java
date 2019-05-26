package com.iss.team1.LeaveApplication.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;


@Entity
public class LeaveHistory {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne
    @JoinColumn(name="staffId")
	private Staff staff;
	
	@ManyToOne
    @JoinColumn(name="LeaveTypeID")
	private LeaveType leaveType;

	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "FromDate")
	private LocalDate fromDate;
	
	//@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "ToDate")
	private LocalDate toDate;

	
	@Length(min=8, max=200)
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Status")
	private LeaveStatus status;
	
	@Column(name = "NoOfDays")
	private double noOfDays;
	
	@Length(max=200)
	@Column(name = "Comment")
	private String managerComment;
	
	@Length(max=200)
	@Column(name = "AdditionalReason")
	private String additionalReason;
	
	@Length(max=100)
	@Column(name = "WorkDissemination")
	private String workDissemination;
	
	@Length(max=200)
	@Column(name = "ContactDetails")
	private String contactDetails;
	
	public enum LeaveStatus {
		PENDING,
		APPROVED,
		REJECTED,
		CANCELLED,
		DELETED;
	}
	
	public LeaveHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveHistory(Staff staff, LeaveType leaveType, LocalDate fromDate, LocalDate toDate, String description,
			LeaveStatus status, Integer noOfDays, String managerComment, String additionalReason, String workDissemination,
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
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
	
	public LeaveStatus getStatus() {
		return status;
	}
	
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	
	public double getNoOfDays() {
		return noOfDays;
	}
	
	public void setNoOfDays(double noOfDays) {
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

