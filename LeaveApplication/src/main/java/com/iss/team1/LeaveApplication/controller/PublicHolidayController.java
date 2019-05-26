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

	@RequestMapping(path = "/createpublicholiday", method = RequestMethod.GET)
	public String createHoliday(Model model) {
		model.addAttribute("PublicHoliday", new PublicHoliday());
		return "createHolidayForm";
	}

	@RequestMapping(path = "/createpublicholiday", method = RequestMethod.POST)
	public String createholiday(@ModelAttribute("PublicHoliday") @Valid PublicHoliday PublicHoliday,
			BindingResult bindingResult, Model model) {
		{
			System.out.println("entered");
			if (bindingResult.hasErrors()) {
				System.out.println("HELLO THERE2");
				model.addAttribute("PublicHoliday", PublicHoliday);
				return "createHolidayForm";
			}
			publicHolidayRepo.save(PublicHoliday);
			return "redirect:/listpublicholiday";
		}
	}

	@RequestMapping(path = "/listpublicholiday", method = RequestMethod.GET)
	public String listHoliday(Model model , HttpSession session) {
		if (session.getAttribute("staff")== null)
		{
			return "redirect:/loginForm";
		}
		int currentsession = (int)session.getAttribute("staff");
		  System.out.println("session passed"  + " " + currentsession);
		model.addAttribute("PublicHoliday", publicHolidayRepo.findAll());
		return "publicHolidayList";
	}

	@RequestMapping(path = "/DeleteHoliday/{Id}", method = RequestMethod.GET)
	public String deleteHoliday(@PathVariable(value = "Id") Integer Id, Model model) {
		model.addAttribute("PublicHoliday", publicHolidayRepo.findById(Id).orElse(null));
		publicHolidayRepo.deleteById(Id);
		return "redirect:/listpublicholiday";
	}
}
