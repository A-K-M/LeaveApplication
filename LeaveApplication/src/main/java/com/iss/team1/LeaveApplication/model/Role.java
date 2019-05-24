package com.iss.team1.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String roleName;
	@OneToMany(targetEntity = Staff.class, mappedBy = "role")
	private Collection<Staff> staff;	
	@OneToMany(targetEntity = RoleLeaveType.class, mappedBy = "role")
	private Collection<RoleLeaveType> RoleLeaveTypes;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Role(String roleName) {
		super();

		this.roleName = roleName;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}	

	public Collection<Staff> getStaff() {
		return staff;
	}
	
	public void setStaff(Collection<Staff> staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleName=" + roleName + "]";
	}	

}
