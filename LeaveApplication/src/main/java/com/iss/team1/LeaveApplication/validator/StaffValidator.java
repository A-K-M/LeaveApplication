package com.iss.team1.LeaveApplication.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;

public class StaffValidator implements Validator{
	
	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Staff s = (Staff) target;
		if (s.getStaffName().isEmpty()) {
			errors.rejectValue("staffName", "StaffName", "Staff Name cannot be empty.");
		}
	}

}
