package com.iss.team1.LeaveApplication.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim;
import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.repo.CompensationClaimRepository;

@Controller
public class CompensationClainController {
	
	private CompensationClaimRepository ccRepo;
	
	@Autowired
	public void setRepo(CompensationClaimRepository ccRepo) {
		this.ccRepo=ccRepo;
	}

	@GetMapping(path = "/compensationclaim")
	public String viewCompensationClainList(Model model) {
		List<CompensationLeaveClaim> claims= ccRepo.findByStaff(1);
		model.addAttribute("compensationclaim", claims);
		return "compensationclaim";
	}
}
