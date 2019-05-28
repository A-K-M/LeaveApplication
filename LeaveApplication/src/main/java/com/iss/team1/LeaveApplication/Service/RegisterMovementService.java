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
	
	public List<LeaveHistory> findAllByMonth(int month, int year) {
		return ldRepo.findAllByMonth(month, year);
	}	

}
