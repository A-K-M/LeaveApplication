package com.iss.team1.LeaveApplication.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.LeaveHistory.LeaveStatus;
import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.LeaveDetailsRepository;
import com.iss.team1.LeaveApplication.repo.LeaveTypeRepository;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;


@Controller
public class LeaveDetailsController {

	private static final String leaveList = "leavelist";
	private static final String leaveDetails = "leavedetails";
	
	private LeaveDetailsRepository ldRepo;
	private LeaveTypeRepository ltRepo;
	private StaffRepository sRepo;
	private RoleRepository rRepo;
	
	@Autowired
	public void setldRepo(LeaveDetailsRepository ldRepo) {
		this.ldRepo=ldRepo;
	}
	@Autowired
	public void setltRepo(LeaveTypeRepository ltRepo) {
		this.ltRepo=ltRepo;
	}
	@Autowired
	public void setsRepo(StaffRepository sRepo) {
		this.sRepo=sRepo;
	}
	@Autowired
	public void setrRepo(RoleRepository rRepo) {
		this.rRepo=rRepo;
	}
	
//	@RequestMapping(path="/leavelist")
//	public String listMethod(Model model) {
//		LeaveType lt=new LeaveType(1, "Annual", null, null, null);
//		ltRepo.save(lt);
//		lt=new LeaveType(2, "Medical", null, null, null);
//		ltRepo.save(lt);
//		lt=new LeaveType(3, "Compensation", null, null, null);
//		ltRepo.save(lt);
//		
//		Role r=new Role("Employee");
//		rRepo.save(r);
//		Staff s=new Staff(1, "aye", "aye", "aye", LocalDate.now(), "athin@gamil.com", r, 1);
//		sRepo.save(s);
//		LeaveHistory l=new LeaveHistory(s,lt,LocalDate.now(), LocalDate.now().plusDays(1L), "leave", LeaveStatus.PENDING, 1, null, null , null, null);
//		ldRepo.save(l);
//		
//		List<LeaveHistory> leaveHistories=ldRepo.findAll();
//		model.addAttribute("leaves", leaveHistories);
//		
//		System.out.println("saved");
//		return "redirect:/leavedetails/"+l.getid();
//		//return "list";
//	}
	
	@GetMapping(path = "/leavedetails/{id}")
	public String viewLeaveDetailsMethod(Model model,@PathVariable(value = "id") String id) {
		
		LeaveHistory l=ldRepo.findById(Integer.valueOf(id)).orElseGet(null);
		model.addAttribute("ldetails", l);
		//model.addAttribute("leaveList", ldRepo.findLeaveHistoriesByStaff(l.getStaff().getId()));
		System.out.println("found");
		
		return "leavedetails";
	}
	
	@PostMapping(path = "/leavedetails")
	public String viewLeaveDetailsMethodForm(@Valid LeaveHistory l, BindingResult bindingResult, Model model) {
		System.out.println(l.getmanagerComment());
//		System.out.println(l.getStaff());
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("ldetails", l);
//			return "leavedetails";
//		}else {
		if (l.getmanagerComment().isEmpty()) {
			model.addAttribute("ldetails", l);
			model.addAttribute("errMsg","Please type in the comment box to reject!");
			return "leavedetails";
		}
		else {
			LeaveHistory leave=ldRepo.findById(Integer.valueOf(l.getId())).get();
			leave.setStatus(LeaveStatus.REJECTED);
			leave.setmanagerComment(l.getmanagerComment());
			this.ldRepo.save(leave);
			System.out.println("rejected");
			return "redirect:/"+leaveList;
		}
//		}
	}
	
	@GetMapping(path = "/leavehistory/{id}/{status}")
	public String changeLeaveStatus(Model model ,@PathVariable(value = "id") String id, @PathVariable(value="status") String status) {
		System.out.println("entered");
//		Integer leaveStatus = Integer.valueOf(status);
//		System.out.println("got status");
		LeaveHistory l=ldRepo.findById(Integer.valueOf(id)).get();
		System.out.println("got leave details");
		
		Integer leaveleft=0;
		//-------check status change to update leave left
		if (LeaveStatus.valueOf(status).equals(LeaveStatus.APPROVED)) {
				//.equals(LeaveStatus.APPROVED)) {
			leaveleft=-l.getNoOfDays();
		}
		else if (LeaveStatus.valueOf(status).equals(LeaveStatus.CANCELLED) 
				&& l.getStatus().equals(LeaveStatus.APPROVED) ) {
			leaveleft=l.getNoOfDays();
		}
		
		System.out.println("No of Days left = 10 + ("+leaveleft.toString()+") = (("+ (10+leaveleft) +"))" );
		l.setStatus(LeaveStatus.valueOf(status.toString()));
		
		System.out.println("updated");
		
		
		ldRepo.save(l);
		System.out.println("saved as status = "+l.getStatus());
		return "redirect:/"+leaveList;
		
	}
}
