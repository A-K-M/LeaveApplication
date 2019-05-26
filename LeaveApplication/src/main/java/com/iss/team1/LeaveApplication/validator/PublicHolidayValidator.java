package com.iss.team1.LeaveApplication.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.PublicHoliday;

public class PublicHolidayValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return PublicHoliday.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		PublicHoliday P = (PublicHoliday) target;
		if (P.getName().isEmpty()) {
			errors.rejectValue("name", "errors.name", "Holiday Name cannot be empty.");
		}
		
		if(P.getDate()==null) {
			errors.rejectValue("date", "errors.date" , "date cannot be empty");
		} else {
			LocalDate date = P.getDate();
			System.out.println(date.toString());
		}

	}
}
