package com.iss.team1.LeaveApplication.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.repo.LeaveDetailsRepository;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;


@Service
public class LeaveHistoryService {
		
	@Autowired
	private PublicHolidayRepository phRepo;
	
	@Autowired
	public void setphRepo(PublicHolidayRepository phRepo) {
		this.phRepo=phRepo;
	}
	
	public Boolean isPublicHoliday(LocalDate dateToCheck) {
		List<PublicHoliday> holiday=phRepo.findPublicHolidaysByDate(dateToCheck);
		if (holiday.size()>0) {
			return true;
		}
		return false;
	}
	
}
