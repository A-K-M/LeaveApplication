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

import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.validator.RoleValidator;

@Controller
public class RolesController {
	
	private RoleRepository rRepo;
	private static final String roles_list = "roles";
	private static final String role_form = "role_form";

	@Autowired
	public void setRoleRepo(RoleRepository rRepo) {
		this.rRepo = rRepo;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new RoleValidator());
	}
	
	@RequestMapping(path = "/")
	public String index(Model model) {
		model.addAttribute("roles", rRepo.findAll());
		return roles_list;
	}
	
	@GetMapping(path = "/roles")
	public String roleList(Model model) {
		model.addAttribute("roles", rRepo.findAll());
		return roles_list;
	}
	
	@GetMapping (path = "/roles/new")
	public String createRole (Model model) {
		model.addAttribute("roles", new Role());
		return role_form;
	}
	
	@PostMapping(path = "/roles/new")
	public String createRoleForm(@Valid Role m, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", m);
			return role_form;
		}else {
			this.rRepo.save(m);
			return "redirect:/"+roles_list;
		}
	}
	
	@GetMapping (path = "/roles/{id}/edit" )
	public String editRoleFrom (Model model ,@PathVariable(value = "id") String id) {
		Integer rid = Integer.valueOf(id); 
		Optional<Role> r = rRepo.findById(rid);
		System.out.println(r);
		model.addAttribute("roles", r);
		return role_form;
	}
	
	@PostMapping(path = "/roles/{id}/edit")
	public String editRoleForm(@Valid Role updatedRole, BindingResult bilnding, Model model) {
		if (bilnding.hasErrors()) {
			model.addAttribute("roles", updatedRole);
			return role_form;
		}else {
			this.rRepo.save(updatedRole);
			return "redirect:/"+roles_list;
		}
	}
	
}
