package com.iss.team1.LeaveApplication.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.LeaveType;
public class LeaveTypeValidator implements Validator {
	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return LeaveType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LeaveType r = (LeaveType) target;
		if (r.getLeaveTypeName().isEmpty()) {
			errors.rejectValue("leaveTypeName", "LeaveTypeName", "Leave Type cannot be empty.");
		}
}
	}
