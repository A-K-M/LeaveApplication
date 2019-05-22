package com.iss.team1.LeaveApplication.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iss.team1.LeaveApplication.model.Roles;

public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return Roles.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Roles r = (Roles) target;
		if (r.getRoleName().isEmpty()) {
			errors.rejectValue("roleName", "RoleName", "Role Name cannot be empty.");
		}
	}
}
