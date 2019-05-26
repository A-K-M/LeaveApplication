package com.iss.team1.LeaveApplication.controller;

import java.time.LocalDate;
import java.util.Collection;

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

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.validator.StaffValidator;

@Controller
public class StaffController {
	
	private StaffRepository staffRepo;
	private RoleRepository roleRepo;
	
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
		binder.addValidators(new StaffValidator());
	}	

	@GetMapping(path = "/staff")
	public String getAllStaff(Model model) {
		model.addAttribute("staff", staffRepo.findAll());	
		return "staffList";
	}

	@GetMapping(path = "/staff/add")
	public String createStaff(Model model) {
		model.addAttribute("staff", new Staff());
		return "editStaff";
	}
	
	@GetMapping(path = "/staff/edit/{id}")
	public String editStaff(Model model, @PathVariable(value = "id") int id) {
		Staff s = staffRepo.findById(id).orElse(null);
		model.addAttribute("staff", s);
		return "editStaff";
	}

	@PostMapping(path = "staff")
	public String saveStaff(@Valid Staff staff, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("staff", staff);
            return "editStaff";
        }
		staffRepo.save(staff);
		return "redirect:/staff";
	}

	@GetMapping(path = "/staff/delete/{id}")
	public String deleteStaff(@PathVariable(name = "id") int id) {
		staffRepo.delete(staffRepo.findById(id).orElse(null));
		return "redirect:/staff";
	}
	
	public int calculateLeaveBalance(LocalDate joinDate) {
		return 0;
	}
}
