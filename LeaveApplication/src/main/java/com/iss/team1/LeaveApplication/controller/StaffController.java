package com.iss.team1.LeaveApplication.controller;

import java.time.LocalDate;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iss.team1.LeaveApplication.Service.StaffService;
import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.util.SecurityUtil;
import com.iss.team1.LeaveApplication.validator.StaffValidator;

@Controller
public class StaffController {
	
	private StaffRepository staffRepo;
	private RoleRepository roleRepo;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	public StaffController(StaffRepository staffRepo, RoleRepository roleRepo) {
		this.staffRepo = staffRepo;
		this.roleRepo = roleRepo;
    }
	
//	@Autowired
//	public void setStaffRepo(StaffRepo staffRepo) {
//		this.staffRepo = staffRepo;
//	}
//	
//	@Autowired
//	public void setRoleRepo(RoleRepo roleRepo) {
//		this.roleRepo = roleRepo;
//	}
	
	@ModelAttribute("roles")
    public Collection<Role> getRoles() {
        return roleRepo.findAll();
    }
	
	@ModelAttribute("managers")
    public Collection<Staff> getManagers() {
        return staffRepo.findManagers();
    }

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new StaffValidator(staffRepo));
	}	

	@GetMapping(path = "/admin/employees")
	public String getAllStaff(Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("staff", staffRepo.findAll());	
		return "admin/employees";
	}

	@GetMapping(path = "/admin/employees/add")
	public String createStaff(Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("staff", new Staff());
		return "admin/employee_form";
	}
		
	@PostMapping(path = "/admin/employees/add")
	public String saveStaff(@Valid Staff staff, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("staff", staff);
            return "editStaff";
        }
		if(staff.getId()==0) {
			staff.setPassword(SecurityUtil.hashPassword("123"));
		}
		staffRepo.save(staff);
		staffService.setStaffLeaveBalance(staff);
		
		return "redirect:/staff";
	}
	
	@GetMapping(path = "/admin/employees/edit/{id}")
	public String employee_form(Model model, @PathVariable(value = "id") int id, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		Staff s = staffRepo.findById(id).orElse(null);
		model.addAttribute("staff", s);
		
		return "redirect:/admin/employees";
	}


	@PostMapping(path = "/admin/employees/edit/{id}")
	public String saveEditStaff(@Valid Staff staff, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("staff", staff);
            return "editStaff";
        }
		if(staff.getId()==0) {
			staff.setPassword(SecurityUtil.hashPassword("123"));
		}
		staffRepo.save(staff);
		staffService.setStaffLeaveBalance(staff);
		
		return "redirect:/staff";
	}



	@GetMapping(path = "/admin/employees/delete/{id}")
	public String deleteStaff(@PathVariable(name = "id") int id, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		staffRepo.delete(staffRepo.findById(id).orElse(null));
		return "redirect:/admin/employees";
	}
	
}
