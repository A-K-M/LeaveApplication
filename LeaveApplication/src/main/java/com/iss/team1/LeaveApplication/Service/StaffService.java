package com.iss.team1.LeaveApplication.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.model.PublicHoliday;
import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.LeaveBalanceRepository;
import com.iss.team1.LeaveApplication.repo.LeaveTypeRepository;
import com.iss.team1.LeaveApplication.repo.PublicHolidayRepository;
import com.iss.team1.LeaveApplication.repo.RoleLeaveTypeRepository;

@Service
public class StaffService {

	@Autowired
	private LeaveTypeRepository ltRepo;
	@Autowired
	private RoleLeaveTypeRepository rltRepo;
	@Autowired
	private LeaveBalanceRepository lbRepo;
	@Autowired
	private PublicHolidayRepository phRepo;

//<<<<<<< HEAD
	@Autowired
	private StaffService staffService;
	
//	@Autowired
//	public StaffService(LeaveTypeRepository ltRepo, RoleLeaveTypeRepository rltRepo, LeaveBalanceRepository lbRepo) {
//		super();
//		this.ltRepo = ltRepo;
//		this.rltRepo = rltRepo;
//		this.lbRepo = lbRepo;
//	}
	
//=======
//>>>>>>> branch 'master' of https://github.com/A-K-M/LeaveApplication.git
	@Autowired
	public void setltRepo(LeaveTypeRepository ltRepo) {
		this.ltRepo = ltRepo;
	}

	@Autowired
	public void setrltRepo(RoleLeaveTypeRepository rltRepo) {
		this.rltRepo = rltRepo;
	}

	@Autowired
	public void setlbRepo(LeaveBalanceRepository lbRepo) {
		this.lbRepo = lbRepo;
	}

	@Autowired
	public void setPhRepo(PublicHolidayRepository phRepo) {
		this.phRepo = phRepo;
	}

	public void setStaffLeaveBalance(Staff s) {

		double days;
		Role role = s.getRole();

		List<LeaveType> leaveTypes = ltRepo.findAllLeaveType();
		for (LeaveType leaveType : leaveTypes) {
			if (leaveType.getId() == 3)
				days = 0;
			else {
				days = rltRepo.findByRoleAndLeaveType(role.getId(), leaveType.getId()).getNoOfDays();
			}
			double d = calculateLeaveBalance(s.getJoinDate());
			days = ((int)days*d);
			int a = (int) days;
			days = a;
			LeaveBalance leaveBalance = new LeaveBalance();
			leaveBalance.setStaff(s);
			leaveBalance.setLeaveType(leaveType);
			leaveBalance.setBalanceLeave(days);
			lbRepo.save(leaveBalance);
		}
	}

	public double calculateLeaveBalance(LocalDate joinDate) {
		System.out.println();
			return (double) numOfWorkingDays(joinDate)/ (double) numOfWorkingDays(LocalDate.of(joinDate.getYear(), 1, 1));
	}

	public int numOfWorkingDays(LocalDate joinDate) {

		List<PublicHoliday> ph = phRepo.findAll();
		LocalDate startDate = joinDate;
		LocalDate endDate = LocalDate.of(joinDate.getYear(), 12, 31);

		long days = ChronoUnit.DAYS.between(startDate, endDate);
		int a = 0;

		for (int i = 0; i <= days; i++) {
			if (startDate.plusDays(i).getDayOfWeek() != DayOfWeek.SATURDAY
					&& startDate.plusDays(i).getDayOfWeek() != DayOfWeek.SUNDAY
					&& isHoliday(startDate.plusDays(i)) == false)
				a++;
		}
		System.out.println(a);
		return a;

	}

	public boolean isHoliday(LocalDate date) {
		List<PublicHoliday> ph = phRepo.findAll();
		for (PublicHoliday publicHoliday : ph) {
			if(publicHoliday.getDate() == date)
			return true;
		}
		return false;
	}

}
