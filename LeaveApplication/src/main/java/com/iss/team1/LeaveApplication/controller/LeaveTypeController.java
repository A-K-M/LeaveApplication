package com.iss.team1.LeaveApplication.controller;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.repo.LeaveTypeRepository;
import com.iss.team1.LeaveApplication.validator.LeaveTypeValidator;


@Controller
public class LeaveTypeController {
	
	private LeaveTypeRepository repo;
	private static final String leaveType_list = "leaveType";
	private static final String leaveType_form = "LeaveTypeForm";

	@Autowired
	public void setLeaveTypeRepo(LeaveTypeRepository repo) {
		this.repo = repo;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LeaveTypeValidator());
	}
	
	@RequestMapping(path = "/index")
	public String index(Model model) {
		model.addAttribute("leaveTypes", repo.findAll());
		return leaveType_list;
	}
	
	@GetMapping(path = "/leaveTypes")
	public String leaveTypeList(Model model) {
		model.addAttribute("leaveTypes", repo.findAll());
		return  leaveType_list;
	}
	
	@GetMapping (path = "/leaveTypes/new")
	public String createLeaveType (Model model) {
		model.addAttribute("leaveType", new LeaveType());
		return leaveType_form;
	}
	
	@PostMapping(path = "/leaveTypes/new")
	public String createLeaveTypeForm(@Valid LeaveType m, BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
			model.addAttribute("leaveType", m);
			return leaveType_form;
			
		}else {
			this.repo.save(m);
			return "redirect:/leaveTypes";
		}
	}
	
	@GetMapping (path = "/leaveTypes/{id}/edit" )
	public String editLeaveTypeForm (Model model ,@PathVariable(value = "id") String id) {
		Integer lId = Integer.valueOf(id); 
		Optional<LeaveType> l = repo.findById(lId);
		System.out.println(l);
		model.addAttribute("leaveType", l);
		return leaveType_form;
	}
	
	@PostMapping(path = "/leaveTypes/{id}/edit")
	public String editLeaveTypeForm(@Valid LeaveType updatedLeaveType, BindingResult bilnding, Model model) {
		if (bilnding.hasErrors()) {
			model.addAttribute("leaveType", updatedLeaveType);
			return leaveType_form;
		}else {
			this.repo.save(updatedLeaveType);
			return "redirect:/leaveTypes";
		}
	}
}
