package com.iss.team1.LeaveApplication.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.validator.StaffValidator;



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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new StaffValidator());
	}
	
	@RequestMapping(path="/loginForm", method = RequestMethod.GET)
   public String Login (  Model model)
       {
//         model.containsAttribute("password");
//		model.addAttribute("error", message);
	   	model.addAttribute("login", new Staff());
         return "login";
       }
   
   @RequestMapping(path="/loginForm", method = RequestMethod.POST)
   public String postLogin (@ModelAttribute("login") @Valid Staff login, BindingResult bindingResult,Model model)
       {
	   System.out.println("entered");
	   if(bindingResult.hasErrors()) {
	   		System.out.println("HELLO THERE2");
			model.addAttribute("login", login);
		   return "login";
	   }
	   System.out.println("no error"+login.getUserName());
   		Staff getStaff = staffrepo.findByuserName(login.getUserName());    
   		System.out.println("inside");
	  // List<Staff> getStaff = staffrepo.findByuserName(login.getUserName());         
	   	if(getStaff != null)	{
	   		System.out.println("no error1");
	   		if(login.getPassword().equals(getStaff.getPassword()))
	   		{
				 model.addAttribute("staff", new Staff());
	   			 
	   			// Role role = rolerepo.findById(login..getId()).orElse(null);
             Role role = getStaff.getRole();
         
             System.out.println("no error" + role.getId());
				  if(role.getRoleName().equals("Staff")) { 

					  return "stafflandingpage";
				  
				  } else if(role.getRoleName().equals("Admin")) { 

					  return "adminview";
				  
				  } else if(role.getRoleName().equals("Manager")) { 

					  return "managerlandingpage";
				  }

	   		}
	   		else {
	   			System.out.println("no staff");
//	   			redirectAttributes.addFlashAttribute("message", "invalid password");
	   			// error =invalid password
	   			return "redirect:/loginForm";
	   			
			}

	   	}
	   	
	   	model.addAttribute("login", login);
//	   	redirectAttributes.addFlashAttribute("message", "ivalid username");
	   	//error = invalid user
	   	return "redirect:/loginForm";
       }



}
