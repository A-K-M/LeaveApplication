package com.iss.team1.LeaveApplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.util.SecurityUtil;
import com.iss.team1.LeaveApplication.validator.LoginValidator;

@Controller
public class LoginController {

	private StaffRepository staffrepo;

	@Autowired
	public void setStaffrepo(StaffRepository staffrepo) {
		this.staffrepo = staffrepo;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LoginValidator());
	}
	
	
	@RequestMapping(path = "/")
	public String index(Model model) {
		return "redirect:/login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String Login(Model model) {
		model.addAttribute("login", new Staff());
		return "login";
	}
	
	

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String postLogin(@ModelAttribute("login") @Valid Staff login, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("login", login);
			return "login";
		}

		Staff staff = staffrepo.findByuserName(login.getUserName());
		
		if (staff != null) 
		{
			if (SecurityUtil.isValidPassword(login.getPassword(), staff.getPassword())) {
				model.addAttribute("staff", new Staff());
				Role role = staff.getRole();
				if (role.getRoleName().equals("Employee")) {
					request.getSession().setAttribute("staff", staff.getId());
					return "redirect:/employee/leavehistory";
				} else if (role.getRoleName().equals("Admin")) {
					request.getSession().setAttribute("admin", staff.getId());
					return "redirect:/admin/index";
				} else if (role.getRoleName().equals("Manager")) {
					request.getSession().setAttribute("manager", staff.getId());
					return "redirect:/manager/pending";
				}
			}
		}
				model.addAttribute("login", login);
				model.addAttribute("Error", "error");
				return "login";
		
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String Login(Model model , SessionStatus status) {
		status.setComplete();
		model.addAttribute("login", new Staff());
		return "login";
	}
	
	@RequestMapping(path = "/admin/index", method = RequestMethod.GET)
	public String adminIndex(Model model, HttpSession session) {
		if (session.getAttribute("admin")==null) {
			return "redirect:/login";
		}
		return "/admin/index";
	}
	

}