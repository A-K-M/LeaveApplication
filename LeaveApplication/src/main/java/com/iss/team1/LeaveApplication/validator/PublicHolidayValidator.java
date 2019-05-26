package com.iss.team1.LeaveApplication.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;

public class PublicHolidayValidator implements Validator{
	
	private PublicHolidayRepository publicHolidayRepo;

	public PublicHolidayValidator(PublicHolidayRepository publicHolidayRepo) {
		this.publicHolidayRepo = publicHolidayRepo;
	}

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
		}
		
		List<PublicHoliday> holidays = publicHolidayRepo.findAll();
		if(!holidays.isEmpty()) {
			PublicHoliday holiday = holidays.stream().filter(h -> h.getDate().isEqual(P.getDate())).findFirst().orElse(null);
			if(holiday != null) {
				errors.rejectValue("date", "errors.date" , "Public Holiday Already Exists");
			}
		}

	}
}
