package com.iss.team1.LeaveApplication.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

import com.iss.team1.LeaveApplication.Service.LeaveHistoryService;
import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.LeaveHistory.LeaveStatus;
import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.LeaveBalanceRepository;
import com.iss.team1.LeaveApplication.repo.LeaveDetailsRepository;
import com.iss.team1.LeaveApplication.repo.LeaveTypeRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.validator.LeaveHistoryValidator;

@Controller
public class LeaveDetailsController {

	private static final String leaveList = "leavelist";
	//private static final String leaveDetails = "leavedetails";
	
	private LeaveDetailsRepository ldRepo;
	private LeaveTypeRepository ltRepo;
	private StaffRepository sRepo;
	private LeaveBalanceRepository lbRepo;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LeaveHistoryValidator());
	}
	
	@Autowired
	private LeaveHistoryService leaveHistoryService;
	
	@Autowired
	public void setRepo(LeaveDetailsRepository ldRepo,LeaveTypeRepository ltRepo, StaffRepository sRepo,LeaveBalanceRepository lbRepo) {
		this.ldRepo=ldRepo;
		this.ltRepo=ltRepo;
		this.sRepo=sRepo;
		this.lbRepo=lbRepo;
	}
	
	@ModelAttribute("leavetypes")
    public List<LeaveType> getLeaveTypes() {
        return ltRepo.findAll();
    }
	
	//both
	@GetMapping(path = "/leavedetails/{id}")
	public String viewLeaveDetailsMethod(Model model,@PathVariable(value = "id") String id) {
		
		LeaveHistory l=ldRepo.findById(Integer.valueOf(id)).orElseGet(null);
		model.addAttribute("leavedetails", l);
		model.addAttribute("leaveList", leaveHistoryService.findAllByStaffAndDateRange(l.getStaff().getId(),l.getFromDate(),l.getToDate()).stream().sorted(Comparator.comparing(LeaveHistory::getFromDate).reversed()).collect(Collectors.toList()));
		
		return "leavedetails";
	}
	
	//emp
	@GetMapping(path = "/emphome")
	public String viewLeaveHistory(Model model, HttpSession session) {
		
		int staffId = (int)session.getAttribute("staff");
		if(staffId == 0)
			return "redirect:loginform";
		List<LeaveHistory> leaves = leaveHistoryService.findByStaff(staffId);
		model.addAttribute("leaves", leaves);
		
		return "emplandingpage";
	}
	
	//both 
	@PostMapping(path = "/leavedetails")
	public String viewLeaveDetailsMethodForm(@Valid LeaveHistory leavedetails, BindingResult bindingResult, Model model) {
		if (leavedetails.getmanagerComment().isEmpty()) {
			model.addAttribute("leavedetails", leavedetails);
			model.addAttribute("errMsg","Please type in the comment box to reject!");
			return "leavedetails";
		}
		else {
			LeaveHistory leave=ldRepo.findById(Integer.valueOf(leavedetails.getId())).get();
			leave.setStatus(LeaveStatus.REJECTED);
			leave.setmanagerComment(leavedetails.getmanagerComment());
			this.ldRepo.save(leave);
			return "redirect:/"+leaveList;
		}
//		
	}
	
	//both
	@GetMapping(path = "/leavehistory/{id}/{status}")
	public String changeLeaveStatus(Model model ,@PathVariable(value = "id") String id, @PathVariable(value="status") String status) {
		LeaveHistory l=ldRepo.findById(Integer.valueOf(id)).get();
		
		LeaveBalance lb=lbRepo.findLeaveBalanceByStaffAndLeaveType(l.getStaff().getId(), l.getLeaveType().getId());
		double leaveleft=0;
		//-------check status change to update leave left
		if (LeaveStatus.valueOf(status).equals(LeaveStatus.APPROVED)) {
			leaveleft=-l.getNoOfDays();
		}
		else if (LeaveStatus.valueOf(status).equals(LeaveStatus.CANCELLED) //new status
				&& l.getStatus().equals(LeaveStatus.APPROVED) ) {//old status
			leaveleft=l.getNoOfDays();
		}
		l.setStatus(LeaveStatus.valueOf(status.toString()));
		ldRepo.save(l);
		
		if ((l.getStatus().equals(LeaveStatus.APPROVED) || l.getStatus().equals(LeaveStatus.CANCELLED))
				&& !(leaveleft == 0)) {
			
			lb.setBalanceLeave(lb.getBalanceLeave()+leaveleft);
			lbRepo.save(lb);
		}
		return "redirect:/"+leaveList;
		
	}
	//emp
	@GetMapping(path = "/leaveapply")
	public String leaveApplyMethod(Model model, HttpSession session) {
//		if (session.getAttribute("staff")==null) {
//			return "redirect:loginform";
//		}
		//int staffId = (int)session.getAttribute("staff");
		LeaveHistory l=new LeaveHistory();
		l.setFromDate(LocalDate.now());
		l.setToDate(LocalDate.now().plusDays(1));
		//l.setId(1);
		Staff s=sRepo.findById(1).get();

		System.out.println(s.toString());
		l.setStaff(s);
		l.setStatus(LeaveStatus.PENDING);
		
		model.addAttribute("leave", l);
		model.addAttribute("leavetypes");
		return "leave";
	}
	
	//emp
	@GetMapping(path = "/leaveupdate/{id}")
	public String leaveUpdateMethod(Model model ,@PathVariable(value = "id") String id) {
		Integer lID=Integer.valueOf(id);
		LeaveHistory l=ldRepo.findById(lID).get();
		if (l!=null) {			
			model.addAttribute("leave", l);
			return "leave";
		}
		return "redirect:/"+leaveList;
	}
	
	//emp
	@PostMapping(path = "/leaveapply")
	public String leaveApplyMethod(@ModelAttribute("leave") @Valid LeaveHistory leave, BindingResult bindingResult, Model model) {

		LeaveHistory l=leave;
		if (bindingResult.hasErrors()) {
			model.addAttribute("leave", l);
			model.addAttribute("leavetypes");
			return "leave";
		}
		else{
			System.out.println("no error");
			LeaveBalance lb=lbRepo.findLeaveBalanceByStaffAndLeaveType(l.getStaff().getId(), l.getLeaveType().getId());
			if (lb == null || lb.getBalanceLeave()<=0) {	
				model.addAttribute("leave", l);
				model.addAttribute("leavetypes");
				model.addAttribute("errMsg", "There is no enough leave balance.");
				return "leave";
			}
			if (leaveHistoryService.isPublicHoliday(leave.getFromDate())==true
				|| leaveHistoryService.isPublicHoliday(leave.getToDate())==true) {
				model.addAttribute("leave", l);
				model.addAttribute("leavetypes");
				model.addAttribute("errMsg", "From Date or To Date cannot be Public Holiday");
				return "leave";
			}

			//check if there is other leaves within this date range
			List<LeaveHistory> existingLeaves=leaveHistoryService.findExistingByStaffAndDateRangeAndStatus(l.getStaff().getId(),l.getFromDate(),l.getToDate());
						
			if (existingLeaves.size()>0) {
				Long isexisting=0L;
				if (l.getId()!=null && l.getId()>0) {// check if the existing records includes current on (for update leave)
					isexisting= existingLeaves.stream().filter(x->x.getId()== l.getId()).count();
				}
				if (existingLeaves.size()-isexisting >0) {
					model.addAttribute("leave", l);
					model.addAttribute("leavetypes");
					model.addAttribute("errMsg", "There is other leave during this date range.");
					return "leave";
				}
			}
			Double noOfDays=new Double(0);
			if (l.getLeaveType().getId()==3) {
				noOfDays=l.getNoOfDays();
				l.setNoOfDays(noOfDays);
			}
			else {
				//noOfDays=Double.valueOf((l.getToDate().getDayOfYear()-l.getFromDate().getDayOfYear())+1);
				noOfDays =Double.valueOf(ChronoUnit.DAYS.between(l.getFromDate(), l.getToDate()))+1;
				l.setNoOfDays(noOfDays);
				Integer totalweekends=0;
				if (l.getLeaveType().getId()==1) {
					//calculate no of leave days for annual
					if (l.getNoOfDays()<=14) {//exclude weenkends
						totalweekends=this.getNoOfHolidays(l.getFromDate(), l.getNoOfDays());
					}
				}
				else {
					totalweekends=this.getNoOfHolidays(l.getFromDate(), l.getNoOfDays());
				}
				l.setNoOfDays(l.getNoOfDays()-totalweekends);
			}
			
			//check leave balance
			if (lb == null || lb.getBalanceLeave()<l.getNoOfDays()) {
				model.addAttribute("leave", l);
				model.addAttribute("leavetypes");
				model.addAttribute("errMsg", "There is no enough leave balance.");
				return "leave";
			}
			ldRepo.save(l);
			
			
			return "redirect:emphome";
		}
	}
	
	public Integer getNoOfHolidays(LocalDate startDate, Double noOfDays) {
		Integer totalHolidays=0;
		for (int i = 0; i < noOfDays; i++) {
			//check if weekends
			if(startDate.plusDays(i).getDayOfWeek()== DayOfWeek.SATURDAY
			|| startDate.plusDays(i).getDayOfWeek()== DayOfWeek.SUNDAY
			|| leaveHistoryService.isPublicHoliday(startDate.plusDays(i))== true // check public holiday
			) {
				totalHolidays+=1;
			}
		}
		return totalHolidays;
	}
}
