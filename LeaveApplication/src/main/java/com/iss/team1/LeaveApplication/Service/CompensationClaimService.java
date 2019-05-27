package com.iss.team1.LeaveApplication.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim;
import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim.Status;
import com.iss.team1.LeaveApplication.repo.CompensationClaimRepository;

@Service
public class CompensationClaimService {

	@Autowired
	private CompensationClaimRepository ccRepo;
	
	@Autowired
	public void setccRepo(CompensationClaimRepository ccRepo) {
		this.ccRepo=ccRepo;
	}
	
	public List<CompensationLeaveClaim> findByStaff(Integer staffid){
		return ccRepo.findByStaff(staffid);
	}
	
	public List<CompensationLeaveClaim> findByStatus(Status status){
		return ccRepo.findByStatus(status);
	}
	
	public List<CompensationLeaveClaim> findByStaffAndDate(Integer staffid,LocalDate date){
		return ccRepo.findByStaffAndDate(staffid, date);
	}
	
}
