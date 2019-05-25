package com.iss.team1.LeaveApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;



@Controller
public class LoginController {
	
	private StaffRepository staffrepo;
	@Autowired
	public void setStaffrepo(StaffRepository staffrepo) {
		this.staffrepo = staffrepo;
	}
	private RoleRepository rolerepo;
	@Autowired
   public void setRolerepo(RoleRepository rolerepo) {
		this.rolerepo = rolerepo;
	}

@RequestMapping(path="/loginForm", method = RequestMethod.GET)
   public String Login (Model model)
       {
        // model.containsAttribute("password");
	    model.addAttribute("login", new Staff());
         return "login";
       }
   
   @RequestMapping(path="/loginForm", method = RequestMethod.POST)
   public String postLogin (@ModelAttribute("login")  Staff login, BindingResult bindingResult,Model model)
       {
	   System.out.println("entered");
	   if(bindingResult.hasErrors()) {
	   		System.out.println("HELLO THERE2");
			model.addAttribute("login", login);
		   return "login";
	   }
	   System.out.println("no error"+login.getUserName());
   		Staff getStaff = staffrepo.findByuserName(login.getUserName());     
	  // List<Staff> getStaff = staffrepo.findByuserName(login.getUserName());         
	   	if(getStaff != null)	{
	   		System.out.println("no error1");
	   		if(login.getPassword().equals(getStaff.getPassword()))
	   		{
				 model.addAttribute("staff", new Staff());
	   			 Role role = rolerepo.findByRoleName(login.getRole().getRoleName());

				  if(role.getRoleName().equals("staff")) { 

					  return "staffLandingPage";
				  
				  } else if(role.getRoleName().equals("admin")) { 

					  return "adminview";
				  
				  } else if(role.getRoleName().equals("manager")) { 

					  return "managerLandingPage";
				  }

	   		}
	   		else {
	   			System.out.println("no staff");
			}

	   	}
	   	
	   	model.addAttribute("login", login);
         return "login";
       }



}
