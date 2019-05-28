package com.iss.team1.LeaveApplication.model;

public class Date {
	
	private int month;
	private int year;
	
	public Date() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
