package com.iss.team1.LeaveApplication.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.Role;

public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Role r = (Role) target;
		if (r.getRoleName().isEmpty()) {
			errors.rejectValue("roleName", "RoleName", "Role Name cannot be empty.");
		}
		
		
	}
}
