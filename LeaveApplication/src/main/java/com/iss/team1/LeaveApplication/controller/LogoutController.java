package com.iss.team1.LeaveApplication.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.iss.team1.LeaveApplication.model.Staff;

public class LogoutController {
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String Login(Model model , SessionStatus status) {
		status.setComplete();
		model.addAttribute("login", new Staff());
		return "login";
	}
}
