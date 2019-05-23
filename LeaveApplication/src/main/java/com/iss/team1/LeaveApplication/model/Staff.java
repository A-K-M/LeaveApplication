package com.iss.team1.LeaveApplication.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String staffName;
	@Length(max=60)
	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;
	@NotEmpty
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate joinDate;
	@NotEmpty
	private String emailId;
	@NotEmpty
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	private int reportsTo;	
	@OneToMany(targetEntity = LeaveHistory.class, mappedBy = "staff")
	private Collection<LeaveHistory> leaveHistories;
	@OneToMany(targetEntity = LeaveBalance.class, mappedBy = "staff")
	private Collection<LeaveBalance> leaveBalances;
	@OneToMany(targetEntity = CompensationLeaveClaim.class, mappedBy = "staff")
	private Collection<CompensationLeaveClaim> compensationLeaveClaims;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Staff(int id, String staffName, String userName, String password, LocalDate joiningDate, String emailId,
			Role role, int reportsTo) {
		super();
		this.id = id;
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
		this.joinDate = joiningDate;
		this.emailId = emailId;
		this.role = role;
		this.reportsTo = reportsTo;
	}
	
	public int getId() {
		return id;
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
	
	public LocalDate getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(LocalDate joiningDate) {
		this.joinDate = joiningDate;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Role getrole() {
		return role;
	}
	
	public void setrole(Role role) {
		this.role = role;
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
		return "Staff [id=" + id + ", staffName=" + staffName + ", userName=" + userName + ", password="
				+ password + ", joiningDate=" + joinDate + ", emailId=" + emailId + ", role=" + role.getId()
				+ ", reportsTo=" + reportsTo + "]";
	}
}
