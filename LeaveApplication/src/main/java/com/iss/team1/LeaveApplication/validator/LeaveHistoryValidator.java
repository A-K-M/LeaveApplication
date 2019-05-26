package com.iss.team1.LeaveApplication.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.LeaveHistory;

public class LeaveHistoryValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return LeaveHistory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LeaveHistory lh = (LeaveHistory) target;
		
		if (lh.getDescription().isEmpty()) {
			errors.rejectValue("description", "description", "description cannot be null");
		}
		
	}

}
