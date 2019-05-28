package com.iss.team1.LeaveApplication.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iss.team1.LeaveApplication.Service.RegisterMovementService;
import com.iss.team1.LeaveApplication.model.Date;
import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.util.SecurityUtil;

@Controller
public class RegisterMovementController {

	@Autowired
	private RegisterMovementService rmService;
	
	@GetMapping(path = "/monthlyLeave")
	public String getAllMonthlyLeave(Model model, HttpSession session) {
//		if (session.getAttribute("admin") == null) {
//			return "redirect:/login";
//		}
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		int year = today.getYear();
		Date date = new Date(month, year);
		List<LeaveHistory> aLeave = rmService.findAllByMonth(month, year);
		model.addAttribute("aLeave", aLeave);
		model.addAttribute("date", date);
		return "monthlyLeave";
	}
	
	@PostMapping(path = "/monthlyLeave")
	public String postAllMonthlyLeave1(Date date1, BindingResult bindingResult, ModelMap model) {
		Date date = new Date(date1.getMonth(), date1.getYear());
		List<LeaveHistory> aLeave = rmService.findAllByMonth(date1.getMonth(), date1.getYear());
		model.addAttribute("aLeave", aLeave);	
		model.addAttribute("date", date);
		return "redirect:/monthlyLeave/"+date1.getYear()+"/"+date1.getMonth();
	}
	
	@GetMapping(path = "/monthlyLeave/{year}/{month}")
	public String getAllMonthlyLeave2(Model model, @PathVariable(value = "year") int year, @PathVariable(value = "month") int month, HttpSession session) {
//		if (session.getAttribute("admin") == null) {
//			return "redirect:/login";
//		}
		Date date = new Date(month, year);
		List<LeaveHistory> aLeave = rmService.findAllByMonth(month, year);
		model.addAttribute("aLeave", aLeave);	
		model.addAttribute("date", date);
		return "monthlyLeave";
	}
	
	@PostMapping(path = "/monthlyLeave/{year}/{month}")
	public String postAllMonthlyLeave2(Date date1, BindingResult bindingResult, ModelMap model) {
		Date date = new Date(date1.getMonth(), date1.getYear());
		List<LeaveHistory> aLeave = rmService.findAllByMonth(date1.getMonth(), date1.getYear());
		model.addAttribute("aLeave", aLeave);	
		model.addAttribute("date", date);
		return "redirect:/monthlyLeave/"+date1.getYear()+"/"+date1.getMonth();
	}
}
