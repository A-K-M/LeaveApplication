package com.iss.team1.LeaveApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.repo.LeaveDetailsRepository;

@Service
public class RegisterMovementService {
	
	@Autowired
	private LeaveDetailsRepository ldRepo;
	
	@Autowired
	public void setldRepo(LeaveDetailsRepository ldRepo) {
		this.ldRepo = ldRepo;
	}
	
	public List<LeaveHistory> findAllAnnualByMonth(int month, int year) {
		return ldRepo.findAllAnnualByMonth(month, year);
	}
	
	public List<LeaveHistory> findAllMedicalByMonth(int month, int year) {
		return ldRepo.findAllMedicalByMonth(month, year);
	}
	
	public List<LeaveHistory> findAllCompensationByMonth(int month, int year) {
		return ldRepo.findAllCompensationByMonth(month, year);
	}
	

}
