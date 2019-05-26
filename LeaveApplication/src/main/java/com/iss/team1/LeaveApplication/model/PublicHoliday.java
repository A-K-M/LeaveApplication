package com.iss.team1.LeaveApplication.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PublicHoliday {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	public PublicHoliday() {
		// TODO Auto-generated constructor stub
	}

	public PublicHoliday(int id, String name, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PublicHoliday [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
}
