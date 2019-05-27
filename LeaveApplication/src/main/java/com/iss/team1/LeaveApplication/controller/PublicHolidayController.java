package com.iss.team1.LeaveApplication.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;
import com.iss.team1.LeaveApplication.validator.PublicHolidayValidator;

@Controller

public class PublicHolidayController {

	private PublicHolidayRepository publicHolidayRepo;

	@Autowired
	public void setPublicHolidayRepo(PublicHolidayRepository publicHolidayRepo) {
		this.publicHolidayRepo = publicHolidayRepo;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new PublicHolidayValidator(publicHolidayRepo));
	}
	
	@RequestMapping(path = "/admin/holidays", method = RequestMethod.GET)
	public String listHoliday(Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		//int currentsession = (int) session.getAttribute("admin");
		model.addAttribute("PublicHoliday", publicHolidayRepo.findAll());
		return "/admin/holidays";
	}

	@RequestMapping(path = "/admin/holidays/add", method = RequestMethod.GET)
	public String createHoliday(Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("PublicHoliday", new PublicHoliday());
		return "/admin/holiday_form";
	}

	@RequestMapping(path = "/admin/holidays/add", method = RequestMethod.POST)
	public String createholiday(@ModelAttribute("PublicHoliday") @Valid PublicHoliday PublicHoliday,
			BindingResult bindingResult, Model model) {
		System.out.println("asfasdf");
		if (bindingResult.hasErrors()) {
			System.out.println("hrere");
			model.addAttribute("PublicHoliday", PublicHoliday);
			return "/admin/holiday_form";
		}
		publicHolidayRepo.save(PublicHoliday);
		return "redirect:/admin/holidays";
	}

	@RequestMapping(path = "/admin/holidays/delete/{Id}", method = RequestMethod.GET)
	public String deleteHoliday(@PathVariable(value = "Id") Integer Id, Model model, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/login";
		}
		model.addAttribute("PublicHoliday", publicHolidayRepo.findById(Id).orElse(null));
		publicHolidayRepo.deleteById(Id);
		return "redirect:/admin/holidays";
	}
}
