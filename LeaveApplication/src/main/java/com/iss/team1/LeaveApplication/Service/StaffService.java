package com.iss.team1.LeaveApplication.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iss.team1.LeaveApplication.model.LeaveBalance;
import com.iss.team1.LeaveApplication.model.LeaveType;
import com.iss.team1.LeaveApplication.model.Role;
import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.LeaveBalanceRepository;
import com.iss.team1.LeaveApplication.repo.LeaveTypeRepository;
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
	public void setltRepo(LeaveTypeRepository ltRepo) {
		this.ltRepo=ltRepo;
	}
	
	@Autowired
	public void setrltRepo(RoleLeaveTypeRepository rltRepo) {
		this.rltRepo=rltRepo;
	}
	
	@Autowired
	public void setlbRepo(LeaveBalanceRepository lbRepo) {
		this.lbRepo=lbRepo;
	}

	public void setStaffLeaveBalance(Staff s) {
		
		double days;
		Role role = s.getRole();
		
		List<LeaveType> leaveTypes = ltRepo.findAllLeaveType();
		for (LeaveType leaveType : leaveTypes) {
			if(leaveType.getId()==3)
				days = 0;
			else {
				days = rltRepo.findByRoleAndLeaveType(role.getId(), leaveType.getId()).getNoOfDays();
			}		
			LeaveBalance leaveBalance = new LeaveBalance();
			leaveBalance.setStaff(s);
			leaveBalance.setLeaveType(leaveType);
			leaveBalance.setBalanceLeave(days);
			lbRepo.save(leaveBalance);
		}
	}
	
	public double calculateLeaveBalance(LocalDate joinDate) {
		return 0;
	}

}
