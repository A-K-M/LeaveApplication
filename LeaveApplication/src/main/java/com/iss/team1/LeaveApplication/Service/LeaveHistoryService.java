package com.iss.team1.LeaveApplication.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.repo.LeaveDetailsRepository;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;


@Service
public class LeaveHistoryService {
		
	@Autowired
	private PublicHolidayRepository phRepo;
	
	@Autowired
	private LeaveDetailsRepository ldRepo;
	
	@Autowired
	public void setphRepo(PublicHolidayRepository phRepo) {
		this.phRepo=phRepo;
	}
	
	@Autowired
	public void setldRepo(LeaveDetailsRepository ldRepo) {
		this.ldRepo=ldRepo;
	}
	
	public Boolean isPublicHoliday(LocalDate dateToCheck) {
		List<PublicHoliday> holiday=phRepo.findPublicHolidaysByDate(dateToCheck);
		if (holiday.size()>0) {
			return true;
		}
		return false;
	}
	
	public List<LeaveHistory> findByStaff(Integer staffid){
		return ldRepo.findByStaff(staffid);
	}
	
	public List<LeaveHistory> findExistingByStaffAndDateRangeAndStatus(Integer staffid,LocalDate fromDate,LocalDate toDate){
		return ldRepo.findExistingByStaffAndDateRangeAndStatus(staffid, fromDate, toDate);
	}
	
	public List<LeaveHistory> findAllByStaffAndDateRange(Integer staffid,LocalDate fromDate,LocalDate toDate){
		return ldRepo.findAllByStaffAndDateRange(staffid, fromDate, toDate);
	}
}
