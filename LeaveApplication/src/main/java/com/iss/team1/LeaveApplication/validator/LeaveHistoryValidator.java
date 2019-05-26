package com.iss.team1.LeaveApplication.validator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.Service.LeaveHistoryService;
import com.iss.team1.LeaveApplication.Service.StaffService;
import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;


public class LeaveHistoryValidator implements Validator {

//	@Autowired
//	private LeaveHistoryService leaveHistoryService;
	
	
	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return LeaveHistory.class.equals(clazz);
	}

//	@Autowired
//	private PublicHolidayRepository phRepo;
//	
//	@Autowired
//	public void setphRepo(PublicHolidayRepository phRepo) {
//		this.phRepo=phRepo;
//	}
	
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LeaveHistory lh = (LeaveHistory) target;
		
		if (lh.getDescription().isEmpty()) {
			errors.rejectValue("description", "description", "description cannot be null");
		}
		if (this.isWeekend(lh.getFromDate())) {
			errors.rejectValue("fromDate", "fromDate", "From Date must be a working day");
		}
		if (this.isWeekend(lh.getToDate())) {
			errors.rejectValue("toDate", "toDate", "To Date must be a working day");
		}
		if (lh.getFromDate().isAfter(lh.getToDate())) {
			errors.rejectValue("fromDate", "fromDate", "From Date must be earlier than To Date");
		}
		if(lh.getFromDate().isBefore(LocalDate.now()))
		{
			errors.rejectValue("fromDate", "fromDate", "From Date cannot be earlier than today");
		}
		if(lh.getToDate().isBefore(LocalDate.now()))
		{
			errors.rejectValue("toDate", "toDate", "To Date cannot be earlier than today");
		}
		
	}
	
	private Boolean isWeekend(LocalDate dateToCheck)
	{
		if (dateToCheck.getDayOfWeek()== DayOfWeek.SATURDAY || dateToCheck.getDayOfWeek()==DayOfWeek.SUNDAY ) {
			return true;
		}
		return false;
	}

}
