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
public class CompensationLeaveClaim {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="staffId")
	private Staff staff;
	private LocalDate date;
	private double noOfHours;
	private String reasons;
	private Integer status;
	public CompensationLeaveClaim() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getNoOfHours() {
		return noOfHours;
	}
	public void setNoOfHours(double noOfHours) {
		this.noOfHours = noOfHours;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public CompensationLeaveClaim( Staff staff, LocalDate date, double noOfHours, String reasons,
			Integer status) {
		super();
		this.staff = staff;
		this.date = date;
		this.noOfHours = noOfHours;
		this.reasons = reasons;
		this.status = status;
	}
	@Override
	public String toString() {
		return "CompensationLeaveClaim [id=" + id + ", staff=" + staff + ", date=" + date + ", noOfHours=" + noOfHours
				+ ", reasons=" + reasons + ", status=" + status + "]";
	}

	

}
