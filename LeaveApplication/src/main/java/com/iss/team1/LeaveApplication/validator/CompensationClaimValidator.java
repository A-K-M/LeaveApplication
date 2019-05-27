package com.iss.team1.LeaveApplication.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim;

public class CompensationClaimValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return CompensationLeaveClaim.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CompensationLeaveClaim c=(CompensationLeaveClaim) target;
		if (c.getDate().isAfter(LocalDate.now())) {
			errors.rejectValue("date", "date", "Date cannot be after today");
			
		}
		if (c.getReasons().isEmpty()) {
			errors.rejectValue("reasons", "reasons", "Reason cannot be null");
		}
		if (Double.compare(c.getNoOfHours(), 8) > 0 || Double.compare(c.getNoOfHours(), 1) < 0) {
			errors.rejectValue("noOfHours", "noOfHours", "No of hours can only be between 1 and 8.");
		}
	}

}
