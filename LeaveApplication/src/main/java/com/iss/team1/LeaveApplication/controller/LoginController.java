package com.iss.team1.LeaveApplication.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
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

	@RequestMapping(path = "/loginForm", method = RequestMethod.GET)
	public String Login(Model model) {
		model.addAttribute("login", new Staff());
		return "login";
	}

	@RequestMapping(path = "/loginForm", method = RequestMethod.POST)
	public String postLogin(@ModelAttribute("login") @Valid Staff login, BindingResult bindingResult, Model model, HttpServletRequest request) {
		System.out.println("entered");
		if (bindingResult.hasErrors()) {
			model.addAttribute("login", login);
			return "login";
		}
		Staff staff = staffrepo.findByuserName(login.getUserName());
		if (staff != null) {
			if (login.getPassword().equals(staff.getPassword())) {
				model.addAttribute("staff", new Staff());
				Role role = staff.getRole();
				
				request.getSession().setAttribute("staff", staff.getId());
				System.out.println(staff.getId());
				System.out.println(staff.getUserName());

				if (role.getRoleName().equals("Staff")) {
					return "redirect:/emphome";
				} else if (role.getRoleName().equals("Admin")) {
					return "adminview";
				} else if (role.getRoleName().equals("Manager")) {
					return "managerLandingPage";
				}
			} 
		}
		model.addAttribute("login", login);
		model.addAttribute("Error", "error");
		return "login";
	}

}
