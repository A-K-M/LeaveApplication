package com.iss.team1.LeaveApplication.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.Staff;
import com.iss.team1.LeaveApplication.repo.StaffRepository;

public class StaffValidator implements Validator {

	private StaffRepository sRepo;
	
	public StaffValidator(StaffRepository sRepo) {
		this.sRepo = sRepo;
	}

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Staff.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Staff s = (Staff) target;
		if (s.getUserName().isEmpty()) {
			errors.rejectValue("userName", "userName", "Username cannot be empty");
		}
		if (s.getJoinDate() == null) {
			errors.rejectValue("joinDate", "joinDate", "Date cannot be empty");
		}
		if (s.getStaffName().isEmpty()) {
			errors.rejectValue("staffName", "name", "Name cannot be empty");
		}
		if (s.getEmailId().isEmpty()) {
			errors.rejectValue("emailId", "emailId", "Email Address cannot be empty");
		}
		List<Staff> staff = sRepo.findAll();
		String name1 = s.getUserName();
		s = sRepo.getOne(s.getId());
		staff.remove(s);
		String name2;
		for (Staff staff2 : staff) {
			name2 = staff2.getUserName();
			if (name1.equals(name2)) {
				errors.rejectValue("userName", "userName", "Username is already used");
			}
		}
	}
}
