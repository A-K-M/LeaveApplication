package com.iss.team1.LeaveApplication.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames="userName")})
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String staffName;
	@Length(max=60)
	private String userName;
	private String password;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate joinDate;
	private String emailId;
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;	
	@ManyToOne
	@JoinColumn(name="reportsTo")
	private Staff manager;
	@OneToMany(targetEntity = Staff.class, mappedBy="manager")
	private Collection<Staff> subordinates;	
	@OneToMany(targetEntity = LeaveHistory.class, mappedBy = "staff")
	private Collection<LeaveHistory> leaveHistories;
	@OneToMany(cascade = CascadeType.ALL, targetEntity = LeaveBalance.class, mappedBy = "staff")
	private Collection<LeaveBalance> leaveBalances;
	@OneToMany(targetEntity = CompensationLeaveClaim.class, mappedBy = "staff")
	private Collection<CompensationLeaveClaim> compensationLeaveClaims;
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Staff(String staffName, String userName, String password, LocalDate joiningDate, String emailId,
			Role role, Staff manager) {
		super();
		this.staffName = staffName;
		this.userName = userName;
		this.password = password;
		this.joinDate = joiningDate;
		this.emailId = emailId;
		this.role = role;
		this.manager = manager;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	@DateTimeFormat(pattern="dd-MMM-YYYY")
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
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Staff getManager() {
		return manager;
	}
	
	public void setManager(Staff manager) {
		this.manager = manager;
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
				+ "]";
	}
}
