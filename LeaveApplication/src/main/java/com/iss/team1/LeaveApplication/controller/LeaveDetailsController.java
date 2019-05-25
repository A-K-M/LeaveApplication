package com.iss.team1.LeaveApplication.controller;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.convert.JodaTimeConverters.LocalDateTimeToDateConverter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.LeaveHistory;
import com.iss.team1.LeaveApplication.model.LeaveHistory.LeaveStatus;
import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.LeaveBalanceRepository;
import com.iss.team1.LeaveApplication.repo.LeaveDetailsRepository;
import com.iss.team1.LeaveApplication.repo.LeaveTypeRepository;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;
import com.iss.team1.LeaveApplication.repo.RoleRepository;
import com.iss.team1.LeaveApplication.repo.StaffRepository;
import com.iss.team1.LeaveApplication.validator.LeaveHistoryValidator;



@Controller
public class LeaveDetailsController {

	private static final String leaveList = "leavelist";
	private static final String leaveDetails = "leavedetails";
	
	private LeaveDetailsRepository ldRepo;
	private LeaveTypeRepository ltRepo;
	private StaffRepository sRepo;
	private RoleRepository rRepo;
	private LeaveBalanceRepository lbRepo;
	private PublicHolidayRepository phRepo;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LeaveHistoryValidator());
		
	}
	
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
	@Autowired
	public void setlbRepo(LeaveBalanceRepository lbRepo) {
		this.lbRepo=lbRepo;
	}
	@Autowired
	public void setphRepo(PublicHolidayRepository phRepo) {
		this.phRepo=phRepo;
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
		model.addAttribute("leaveList", ldRepo.findAllByStaffAndDateRange(l.getStaff().getId(),l.getFromDate(),l.getToDate()).stream().sorted(Comparator.comparing(LeaveHistory::getFromDate).reversed()).collect(Collectors.toList()));
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
		
		LeaveBalance lb=lbRepo.findLeaveBalanceByStaffAndLeaveType(l.getStaff().getId(), l.getLeaveType().getId());
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
		
		//System.out.println("No of Days left = 10 + ("+leaveleft.toString()+") = (("+ (10+leaveleft) +"))" );
		
		l.setStatus(LeaveStatus.valueOf(status.toString()));
		System.out.println("updated");
		ldRepo.save(l);
		
		if ((l.getStatus().equals(LeaveStatus.APPROVED) || l.getStatus().equals(LeaveStatus.CANCELLED))
				&& !leaveleft.equals(0)) {
			
			lb.setBalanceLeave(lb.getBalanceLeave()+leaveleft);
			System.out.println("updated2");
			lbRepo.save(lb);
		}
		
		
		System.out.println("saved as status = "+l.getStatus() +" and leave status = "+lb.getBalanceLeave());
		return "redirect:/"+leaveList;
		
	}
	
	@GetMapping(path = "/leaveapply")
	public String leaveApplyMethod(Model model) {
		LeaveHistory l=new LeaveHistory();
		l.setFromDate(LocalDate.now());
		l.setToDate(LocalDate.now().plusDays(1));
		//l.setId(1);
		Staff s=sRepo.findById(1).get();
		System.out.println(s.toString());
		l.setStaff(s);
		l.setStatus(LeaveStatus.PENDING);
		List<LeaveType> leaveTypes=ltRepo.findAll();
		
		model.addAttribute("leave", l);
		model.addAttribute("leavetypes", leaveTypes);
		return "leave";
	}
	
	@GetMapping(path = "/leaveupdate/{id}")
	public String leaveUpdateMethod(Model model ,@PathVariable(value = "id") String id) {
		Integer lID=Integer.valueOf(id);
		LeaveHistory l=ldRepo.findById(lID).get();
		if (l!=null) {
			List<LeaveType> leaveTypes=ltRepo.findAll();
			
			model.addAttribute("leave", l);
			model.addAttribute("leavetypes", leaveTypes);
			return "leave";
		}
		return "redirect:/"+leaveList;
	}
	
	@PostMapping(path = "/leavesubmit")
	public String leaveApplyMethod(@Valid LeaveHistory l, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("got error");
			System.out.println(l.getStaff().toString());
			System.out.println(l.getLeaveType().toString());
			System.out.println(bindingResult.toString());
			List<LeaveType> leaveTypes=ltRepo.findAll();			
			model.addAttribute("leave", l);
			model.addAttribute("leavetypes", leaveTypes);
			return "leave";
		}else {
			System.out.println("no error");
			LeaveBalance lb=lbRepo.findLeaveBalanceByStaffAndLeaveType(l.getStaff().getId(), l.getLeaveType().getId());
			if (lb == null || lb.getBalanceLeave()<=0) {
				System.out.println("not enough leave balance");
				List<LeaveType> leaveTypes=ltRepo.findAll();	
				model.addAttribute("leave", l);
				model.addAttribute("leavetypes", leaveTypes);
				model.addAttribute("errMsg", "There is no enough leave balance.");
				return "leave";
			}
			//check if there is other leaves within this date range
			List<LeaveHistory> existingLeaves=ldRepo.findExistingByStaffAndDateRangeAndStatus(l.getStaff().getId(),l.getFromDate(),l.getToDate());
						
			if (existingLeaves.size()>0) {
				Long isexisting=0L;
				if (l.getId()!=null && l.getId()>0) {// check if the existing records includes current on (for update leave)
					isexisting= existingLeaves.stream().filter(x->x.getId()== l.getId()).count();
					//System.out.println("existing after minus :"+ (existingLeaves.size()-isexisting));
				}
				if (existingLeaves.size()-isexisting >0) {
					System.out.println("other leave exist");
					List<LeaveType> leaveTypes=ltRepo.findAll();	
					model.addAttribute("leave", l);
					model.addAttribute("leavetypes", leaveTypes);
					model.addAttribute("errMsg", "There is another leave during this date range.");
					return "leave";
				}
				
			}
			
			Integer noOfDays=(l.getToDate().getDayOfYear()-l.getFromDate().getDayOfYear())+1;
			l.setNoOfDays(noOfDays);
			//System.out.println("original days="+noOfDays);
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
			System.out.println("no of day="+l.getNoOfDays());
			
			//check leave balance
			
			if (lb == null || lb.getBalanceLeave()<l.getNoOfDays()) {
				System.out.println("not enough leave balance");
				List<LeaveType> leaveTypes=ltRepo.findAll();	
				model.addAttribute("leave", l);
				model.addAttribute("leavetypes", leaveTypes);
				model.addAttribute("errMsg", "There is no enough leave balance.");
				return "leave";
			}

			ldRepo.save(l);
			model.addAttribute("ldetails", l);
			return "redirect:/"+leaveDetails+"/"+l.getId();
			
		}
	}
	
	public Integer getNoOfHolidays(LocalDate startDate, Integer noOfDays) {
		Integer totalHolidays=0;
		for (int i = 0; i < noOfDays; i++) {
			//check if weekends
			
			//System.out.println("public holidays ="+phRepo.findPublicHolidaysByDate(l.getFromDate().plusDays(i)).size());
			if(startDate.plusDays(i).getDayOfWeek()== DayOfWeek.SATURDAY
			|| startDate.plusDays(i).getDayOfWeek()== DayOfWeek.SUNDAY
			//|| phRepo.findPublicHolidaysByDate(l.getFromDate().plusDays(i)).size()>0 // check public holiday
			) {
				totalHolidays+=1;
				
			}
		}
		return totalHolidays;
	}
}
