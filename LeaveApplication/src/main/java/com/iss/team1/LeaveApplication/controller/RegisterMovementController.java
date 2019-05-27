package com.iss.team1.LeaveApplication.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.iss.team1.LeaveApplication.Service.RegisterMovementService;
import com.iss.team1.LeaveApplication.model.LeaveHistory;

@Controller
public class RegisterMovementController {

	@Autowired
	private RegisterMovementService rmService;
	
	@GetMapping(path = "/monthlyLeave")
	public String getAllStaff(Model model, HttpSession session) {
//		if (session.getAttribute("admin") == null) {
//			return "redirect:/login";
//		}
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		int year = today.getYear();
		List<LeaveHistory> aLeave = rmService.findAllAnnualByMonth(month, year);
		List<LeaveHistory> mLeave = rmService.findAllMedicalByMonth(month, year);
		List<LeaveHistory> cLeave = rmService.findAllCompensationByMonth(month, year);
		model.addAttribute("aLeave", aLeave);	
		model.addAttribute("mLeave", mLeave);	
		model.addAttribute("cLeave", cLeave);	
		return "monthlyLeave";
	}
}
