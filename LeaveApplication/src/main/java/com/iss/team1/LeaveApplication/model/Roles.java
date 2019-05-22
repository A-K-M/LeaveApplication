package com.iss.team1.LeaveApplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String roleName;


	public int getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + "]";
	}

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Roles(String roleName) {
		super();

		this.roleName = roleName;
	}


	
	
	

}
