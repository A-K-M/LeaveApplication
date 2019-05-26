package com.iss.team1.LeaveApplication.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.iss.team1.LeaveApplication.model.Staff;

public class StaffValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Staff s = (Staff) target;
		if (s.getUserName().isEmpty()) {
			errors.rejectValue("userName", "userName", "Username cannot be empty");
		}
		if (s.getPassword().isEmpty()) {
			errors.rejectValue("password", "password", "Password cannot be empty");
		}
		if (s.getJoinDate().equals(null)) {
			errors.rejectValue("joinDate", "joinDate", "Date cannot be empty");
		}
		if (s.getStaffName().isEmpty()) {
			errors.rejectValue("name", "name", "Name cannot be empty");
		}
		if (s.getEmailId().isEmpty()) {
			errors.rejectValue("emailId", "emailId", "Email Address cannot be empty");
		}
	}
}
