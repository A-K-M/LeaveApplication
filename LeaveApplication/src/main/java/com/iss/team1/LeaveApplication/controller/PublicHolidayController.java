package com.iss.team1.LeaveApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;

@Controller

public class PublicHolidayController {
	
	private PublicHolidayRepository publicHolidayRepo;
    @Autowired
	public void setPublicHolidayRepo(PublicHolidayRepository publicHolidayRepo) {
		this.publicHolidayRepo = publicHolidayRepo;
	}
	
    @RequestMapping(path="/createpublicholiday", method = RequestMethod.GET)
    public String createHoliday (Model model)
        {
 	    model.addAttribute("PublicHoliday", new PublicHoliday());
          return "createHolidayForm";
        }
    
    @PostMapping(path="/createpublicholiday") 
    public String createholiday(PublicHoliday PublicHoliday,
    		  Model model) 
    		  { 
    			  publicHolidayRepo.save(PublicHoliday);
    		      return "redirect:/listpublicholiday";
    		  }
	
    @RequestMapping(path="/listpublicholiday", method = RequestMethod.GET)
    public String saveStaff (Model model)
        {
 	   	  model.addAttribute("PublicHoliday",publicHolidayRepo.findAll() );
 	   	 
          return "publicHolidayList";
        }
    
	  @RequestMapping(path="/DeleteHoliday/{Id}",method=RequestMethod.GET)
		public String deleteHoliday(@PathVariable(value="Id") Integer Id,Model model)
		{
			model.addAttribute("PublicHoliday", publicHolidayRepo.findById(Id).orElse(null));
			publicHolidayRepo.deleteById(Id);
			return "redirect:/listpublicholiday";
		}
}
