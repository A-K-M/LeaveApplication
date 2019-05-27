package com.iss.team1.LeaveApplication.controller;

import java.lang.Thread.State;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iss.team1.LeaveApplication.Service.CompensationClaimService;
import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim;
import com.iss.team1.LeaveApplication.model.CompensationLeaveClaim.Status;
import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.repo.CompensationClaimRepository;
import com.iss.team1.LeaveApplication.repo.LeaveBalanceRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.validator.CompensationClaimValidator;


@Controller
public class CompensationClainController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new CompensationClaimValidator());
	}
	
	@Autowired
	private CompensationClaimRepository ccRepo;
	
	@Autowired
	private StaffRepository sRepo;
	
	@Autowired
	private LeaveBalanceRepository lbRepo;
	
	@Autowired
	public void setRepo(CompensationClaimRepository ccRepo,StaffRepository sRepo, LeaveBalanceRepository lbRepo) {
		this.ccRepo=ccRepo;
		this.sRepo=sRepo;
		this.lbRepo=lbRepo;
	}

	@Autowired
	private CompensationClaimService compensationClaimService;
	
	@GetMapping(path = "/compensationclaimlist")
	public String viewCompensationClaimList(Model model) {
		List<CompensationLeaveClaim> claims= compensationClaimService.findByStaff(1);
		model.addAttribute("compensationclaim", claims);
		return "compensationclaimlist";
	}
	
	@GetMapping(path = "/manager/compensationclaimlist")
	public String viewCompensationClaimApproval(Model model) {
		List<CompensationLeaveClaim> claims= compensationClaimService.findByStatus(Status.APPLIED);
		model.addAttribute("compensationclaim", claims);
		return "compensationclaimlist";
	}
	
	@GetMapping(path = "/compensationclaim")
	public String claimCompensation(Model model) {
		CompensationLeaveClaim claim=new CompensationLeaveClaim();
		claim.setStaff(sRepo.findById(1).get());
		claim.setDate(LocalDate.now());
		claim.setStatus(Status.APPLIED);
		model.addAttribute("compensationclaim", claim);
		return "compensationclaim";
	}
	
	@GetMapping(path = "/compensationclaim/update/{id}")
	public String claimCompensationUpdate(Model model,@PathVariable(value = "id") String id) {
		
		CompensationLeaveClaim claim=ccRepo.findById(Integer.valueOf(id)).get();
		model.addAttribute("compensationclaim", claim);
		return "compensationclaim";
	}
	
	@PostMapping(path = "/compensationclaim")
	public String claimCompensation(@ModelAttribute("compensationclaim") @Valid CompensationLeaveClaim compensationclaim, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("Error :: "+compensationclaim.toString());
			model.addAttribute("compensationclaim", compensationclaim);
			return "compensationclaim";
		}
		else{
			System.out.println("No Error :: "+compensationclaim.toString());
			Boolean duplicate=false;
			List<CompensationLeaveClaim> existing=compensationClaimService.findByStaffAndDate(compensationclaim.getStaff().getId(), compensationclaim.getDate());
			if (existing.size()>0) {
				
				if (compensationclaim.getId()>0) {
					Long existingisSame=existing.size()-existing.stream().filter(x->x.getId()==compensationclaim.getId()).count();
					if (existingisSame>0) {
						duplicate=true;		
					}
				}
				else {
				duplicate=true;
				}
			}
			if (duplicate==true) {
				model.addAttribute("compensationclaim", compensationclaim);
				model.addAttribute("errMsg", "There is other claim already for this date.");
				return "compensationclaim";
			}
			ccRepo.save(compensationclaim);
			return "redirect:/compensationclaimlist";
		}
	}
	@GetMapping(path = "/compensationclaim/{id}/{status}")
	public String claimCompensationUpdateStatus(Model model,@PathVariable(value = "id") String id,@PathVariable(value = "status") String status) {
		
		CompensationLeaveClaim claim=ccRepo.findById(Integer.valueOf(id)).get();
		claim.setStatus(Status.valueOf(status));
		ccRepo.save(claim);
		
		if (claim.getStatus()== Status.APPROVED) {
			LeaveBalance balance=lbRepo.findLeaveBalanceByStaffAndLeaveType(claim.getStaff().getId(), 3);
			if (balance != null) {
				balance.setBalanceLeave(balance.getBalanceLeave()+claim.getNoOfHours());
				lbRepo.save(balance);
			}
		}
		return "redirect:/compensationclaimlist";
	}
	
	
}
