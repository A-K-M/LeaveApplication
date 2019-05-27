package com.iss.team1.LeaveApplication.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.LeaveBalanceRepository;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.util.SecurityUtil;
import com.iss.team1.LeaveApplication.validator.StaffValidator;

@Controller
public class StaffController {
	
	private StaffRepository staffRepo;
	private RoleRepository roleRepo;
	private LeaveBalanceRepository lbRepo;
	
	@Autowired
	private StaffService staffService;
	
	
	public StaffController(StaffRepository staffRepo, RoleRepository roleRepo, LeaveBalanceRepository lbRepo) {
		super();
		this.staffRepo = staffRepo;
		this.roleRepo = roleRepo;
		this.lbRepo = lbRepo;
	}

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

	@GetMapping(path = "/staff")
	public String getAllStaff(Model model) {
		model.addAttribute("staff", staffRepo.findAll());	
		return "staffList";
	}

	@GetMapping(path = "/staff/add")
	public String createStaff(Model model) {
		Staff s = new Staff();
		s.setJoinDate(LocalDate.now());
		model.addAttribute("staff", s);
		return "editStaff";
	}
	
	@GetMapping(path = "/staff/edit/{id}")
	public String editStaff(Model model, @PathVariable(value = "id") int id) {
		Staff s = staffRepo.findById(id).orElse(null);
		
		LeaveBalance bal = lbRepo.findAnnualLeaveBalanceByStaffId(id);
				
		model.addAttribute("staff", s);
		model.addAttribute("balance", bal);

		return "editStaff";
	}

	@PostMapping(path = "staff")
	public String saveStaff(@Valid Staff staff, LeaveBalance bal, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("staff", staff);
            return "editStaff";
        }
		if(staff.getId()==0) {
			staff.setPassword(SecurityUtil.hashPassword("123"));
			staffRepo.save(staff);
			staffService.setStaffLeaveBalance(staff);
		}
		else {
			staffRepo.save(staff);
			double b = bal.getBalanceLeave();
			LeaveBalance bal2 = lbRepo.findAnnualLeaveBalanceByStaffId(staff.getId());
			bal2.setBalanceLeave(b);
			lbRepo.save(bal2);
		}

		return "redirect:/staff";
	}
	

	@GetMapping(path = "/staff/delete/{id}")
	public String deleteStaff(@PathVariable(name = "id") int id) {
		staffRepo.delete(staffRepo.findById(id).orElse(null));
		return "redirect:/staff";
	}
	
}
